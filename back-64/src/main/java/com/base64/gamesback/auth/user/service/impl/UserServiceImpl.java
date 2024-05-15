package com.base64.gamesback.auth.user.service.impl;

import com.base64.gamesback.auth.user.dto.*;
import com.base64.gamesback.auth.user.entity.User;
import com.base64.gamesback.auth.user.repository.UserRepository;
import com.base64.gamesback.auth.user.service.DoctorService;
import com.base64.gamesback.auth.user.service.PersonService;
import com.base64.gamesback.auth.user.service.UserService;
import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PersonService personService;
    private final DoctorService doctorService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PersonService personService, DoctorService doctorService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.personService = personService;
        this.doctorService = doctorService;
        this.passwordEncoder = passwordEncoder;
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
    public void registerUserPatient(UserDto request) {
        User user = User.create(
                request.getName().toLowerCase(Locale.ROOT),
                passwordEncoder.encode(request.getPassword()),
                false,
                null
        );
        personService.registerPerson(userRepository.save(user), request.getPerson());
    }

    @Override
    public void registerUserDoctor(UserDoctorDto request) {
        User user = User.create(
                request.getName().toLowerCase(Locale.ROOT),
                passwordEncoder.encode(request.getPassword()),
                false,
                null
        );
        doctorService.registerPersonDoctor(userRepository.save(user), request.getDoctor());
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
}
