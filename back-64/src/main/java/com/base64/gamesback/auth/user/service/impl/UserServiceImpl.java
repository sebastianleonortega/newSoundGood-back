package com.base64.gamesback.auth.user.service.impl;

import com.base64.gamesback.auth.auth.exception.AuthenticationFailedException;
import com.base64.gamesback.auth.user.dto.*;
import com.base64.gamesback.auth.user.dto.projection.CountUser;
import com.base64.gamesback.auth.user.entity.User;
import com.base64.gamesback.auth.user.repository.UserRepository;
import com.base64.gamesback.auth.user.service.DoctorService;
import com.base64.gamesback.auth.user.service.PersonService;
import com.base64.gamesback.auth.user.service.UserService;
import com.base64.gamesback.auth.user.service.UserServiceShared;
import com.base64.gamesback.common.criteria.Criteria;
import com.base64.gamesback.common.criteria.Filter;
import com.base64.gamesback.common.criteria.Filters;
import com.base64.gamesback.common.criteria.Order;
import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import com.base64.gamesback.common.object.SearchByCriteria;
import com.base64.gamesback.common.parse.ParseFilters;
import com.base64.gamesback.common.util.UtilString;
import com.base64.gamesback.email.service.EmailSendService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PersonService personService;
    private final DoctorService doctorService;
    private final PasswordEncoder passwordEncoder;
    private final EmailSendService emailSendService;
    private final UserServiceShared userServiceShared;

    @Value("${settings.url.front}")
    private String urlFrontEnd;

    public UserServiceImpl(UserRepository userRepository, PersonService personService, DoctorService doctorService, PasswordEncoder passwordEncoder, EmailSendService emailSendService, UserServiceShared userServiceShared) {
        this.userRepository = userRepository;
        this.personService = personService;
        this.doctorService = doctorService;
        this.passwordEncoder = passwordEncoder;
        this.emailSendService = emailSendService;
        this.userServiceShared = userServiceShared;
    }

    @Override
    public UserPatientResponse getUserPatientById(UUID userId) {
        return userRepository.getUserPatientById(userId);
    }

    @Override
    public UserDoctorResponse getUserDoctorById(UUID userId) {
        return userRepository.getUserDoctorById(userId);
    }

    @Override
    public Boolean existUserByName(String userName) {
        return userRepository.existsUserByUserName(userName.toLowerCase(Locale.ROOT));
    }

    @Override
    @Transactional
    public UUID registerUserPatient(UserDto request) {
        User user = User.create(
                request.getName().toLowerCase(Locale.ROOT),
                passwordEncoder.encode(request.getPassword()),
                null,
                false
        );
        userRepository.save(user);
        personService.registerPerson(user, request.getPerson());
        return user.getUserId();
    }

    @Override
    @Transactional
    public UUID registerUserDoctor(UserDoctorDto request) {
        User user = User.create(
                request.getName().toLowerCase(Locale.ROOT),
                passwordEncoder.encode(request.getDoctor().getDocument().toLowerCase(Locale.ROOT) + ".$"),
                null,
                true
        );
        userRepository.save(user);
        doctorService.registerPersonDoctor(user, request.getDoctor());
        return user.getUserId();
    }

    @Override
    public void updateUserPatient(UserUpdateRequest request, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No existe el usuario que desea actualizar"));

        user.update(
                request.getUserName().toLowerCase(Locale.ROOT),
                request.getProfileImage()
        );
        personService.updatePerson(request.getPerson(), userRepository.save(user));
    }

    @Override
    public void updateUserDoctor(UserDoctorUpdateRequest request, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No existe el usuario que desea actualizar"));

        user.update(
                request.getUserName().toLowerCase(Locale.ROOT),
                ""
        );
        doctorService.updatePersonDoctor(request.getDoctor(), userRepository.save(user));
    }

    @Override
    public List<UserPatientResponse> getAllUsersPatient() {
        return userRepository.getAllUserPatients();
    }

    @Override
    public List<UserDoctorResponse> getAllUsersDoctor() {
        return userRepository.getAllUserDoctors();
    }

    @Override
    public Page<UserDoctorResponse> getAllUsersDoctorPage(SearchByCriteria search) {
        List<Filter> filters = ParseFilters.getFilters(search.filters());

        Order order = Order.fromValues(search.orderBy(), search.orderType());
        if (!order.hasOrder()) {
            order = Order.desc("created_at");
        }

        Criteria criteria = new Criteria(
                new Filters(filters),
                order,
                search.limit(),
                search.offset()
        );

        Criteria criteriaCount = new Criteria(new Filters(ParseFilters.getFilters(search.filters())), Order.none());
        return userRepository.getAllUserDoctorsPage(criteria, userRepository.countUserDoctorsPage(criteriaCount));
    }

    @Override
    public Page<UserResponseDto> getAllUsersPage(SearchByCriteria search) {
        List<Filter> filters = ParseFilters.getFilters(search.filters());

        Order order = Order.fromValues(search.orderBy(), search.orderType());
        if (!order.hasOrder()) {
            order = Order.desc("created_at");
        }

        Criteria criteria = new Criteria(
                new Filters(filters),
                order,
                search.limit(),
                search.offset()
        );

        Criteria criteriaCount = new Criteria(new Filters(ParseFilters.getFilters(search.filters())), Order.none());
        return userRepository.getAllUsersPage(criteria, userRepository.countUsersPage(criteriaCount));
    }

    @Override
    public void updatePassword(UpdatePasswordRequest request) {
        User user = userServiceShared.getUserById(request.getUserId());
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("La contraseña actual es incorrecta.");
        }
        user.updatePassword(passwordEncoder.encode(request.getPassword()));
        user.updateHavePasswordByAdmin();
        userRepository.save(user);
        emailSendService.changePassword(user);
    }

    @Override
    public void updatePasswordByAdmin(UUID userId, UUID userAdminId) {
        User userAdmin = userServiceShared.getUserById(userAdminId);
        if (userAdmin == null || !userAdmin.isAdministrator()) {
            throw new AuthenticationFailedException("Solo el administrador tiene estos permisos");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String password = UtilString.generateRandom(12);
        this.userResetPassword(user, password);
        emailSendService.resetUserPassword(user, password);
    }

    @Override
    public void forgotPassword(ResetPasswordRequest request) {
        User user = userServiceShared.getUserNameAndEmail(request.getUserName().toLowerCase(Locale.ROOT).replace(" ", ""), request.getEmail());
        if (user == null) {
            throw new AuthenticationFailedException("usuario no encontrado");
        }
        user.updateResetPasswordUpdateAt(null);
        userServiceShared.saveUser(user);

        String json = "{'user_id':'" + user.getUserId() + "','user_name':'" + user.getUserName() + "', 'timestamp':'" + LocalDateTime.now().plusMinutes(60) + "'}";
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        String urlToken = urlFrontEnd + "/reset_password/" + jsonObject.toString();
        emailSendService.recoveryPasswordEmailSent(user, urlToken);
    }

    @Override
    public void verifyTokenResetPassword(TokenResentPasswordRequest request) {
        if (!request.getPassword().equals(request.getPasswordConfirmed())) {
            throw new AuthenticationFailedException("tu contraseña no coincide.");
        }

        JsonObject token = new Gson().fromJson(request.getToken(), JsonObject.class);

        UUID userId = UUID.fromString(token.get("user_id").getAsString());
        String userName = token.get("user_name").getAsString();
        LocalDateTime expirationDate = LocalDateTime.parse(token.get("timestamp").getAsString());

        User user = userServiceShared.getUserOfResetPassword(userId, userName);

        if (user.getResetPasswordUpdateAt() != null) {
            throw new AuthenticationFailedException("el token ya fue utilizado");
        }

        if (LocalDateTime.now().isAfter(expirationDate)) {
            throw new AuthenticationFailedException("El token ya ha expirado");
        }

        user.updatePassword(passwordEncoder.encode(request.getPassword()));
        user.updateResetPasswordUpdateAt(LocalDateTime.now());
        user.updateLoginAttempts(0);
        user.updateLoginAttemptsMfa(0);
        user.updateQuantityResentEmail(0);
        user.updateStatus("Activo");
        userServiceShared.saveUser(user);
        emailSendService.changePassword(user);
    }

    @Override
    public void updateStatusUser(UpdateStatusUserRequest updateStatusUserRequest) {
        User userAdmin = userServiceShared.getUserById(updateStatusUserRequest.getAdminUserId());
        if(!userAdmin.isAdministrator()){
            throw new AuthenticationFailedException("Solo el administrador tiene estos permisos");
        }
        User user = userServiceShared.getUserById(updateStatusUserRequest.getUserId());
        user.updateStatus(updateStatusUserRequest.getStatus());
        userRepository.save(user);
    }

    @Override
    public CountUser getCountUsers() {
        return userRepository.getCountUsers();
    }

    private void userResetPassword(User user, String password) {
        user.resetPassword(
                passwordEncoder.encode(password),
                LocalDateTime.now(),
                0,
                0
        );
        userRepository.save(user);
    }
}
