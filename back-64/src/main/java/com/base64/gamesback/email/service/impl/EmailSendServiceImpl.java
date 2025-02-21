package com.base64.gamesback.email.service.impl;

import com.base64.gamesback.auth.user.entity.User;
import com.base64.gamesback.auth.user.service.UserServiceShared;
import com.base64.gamesback.email.dto.EmailRequest;
import com.base64.gamesback.email.service.EmailSendService;
import com.base64.gamesback.email.service.EmailService;
import com.base64.gamesback.email.service.EmailTemplateService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.UUID;

@Service
public class EmailSendServiceImpl implements EmailSendService {

    @Value("${settings.url.front}")
    private String urlFront;

    private final EmailService emailService;
    private final EmailTemplateService emailTemplateService;
    private final UserServiceShared userServiceShared;

    public EmailSendServiceImpl(EmailService emailService, EmailTemplateService emailTemplateService, UserServiceShared userServiceShared) {
        this.emailService = emailService;
        this.emailTemplateService = emailTemplateService;
        this.userServiceShared = userServiceShared;
    }

    @Override
    public void emailCodeVerification(User user, String codeVerification) {
        String body = emailTemplateService.getEmailTemplateByName("two_factor_authentication");
        body = body.replace("person_name", user.getPerson() != null ? user.getPerson().getPersonName() : user.getDoctor().getName());
        body = body.replace("{codigo}", codeVerification);
        body = body.replace("current_year", String.valueOf(Year.now().getValue()));
        body = body.replace("redirect", urlFront);

        emailSend(user.getPerson() != null ? user.getPerson().getPersonEmail() : user.getDoctor().getEmail(), "Tu código de verificación", body);
    }

    @Override
    public void recoveryPasswordEmailSent(User user, String urlToken) {
        String body = emailTemplateService.getEmailTemplateByName("recovery_password");
        body = body.replace("person_name", user.getPerson() != null ? user.getPerson().getPersonName() : user.getDoctor().getName());
        body = body.replace("link_recovery_password", urlToken);
        body = body.replace("current_year", String.valueOf(Year.now().getValue()));
        body = body.replace("redirect", urlFront);

        emailSend(user.getPerson() != null ? user.getPerson().getPersonEmail() : user.getDoctor().getEmail(), "Recuperación de contraseña", body);
    }

    @Override
    public void changePassword(User user) {
        String body = emailTemplateService.getEmailTemplateByName("change_password");
        body = body.replace("person_name", user.getPerson() != null ? user.getPerson().getPersonName() : user.getDoctor().getName());
        body = body.replace("current_year", String.valueOf(Year.now().getValue()));
        body = body.replace("redirect", urlFront);

        emailSend(user.getPerson() != null ? user.getPerson().getPersonEmail() : user.getDoctor().getEmail(), "Cambio de contraseña exitoso", body);
    }

    @Override
    public void resetUserPassword(User user, String password) {
        String body = emailTemplateService.getEmailTemplateByName("reset_user_password_admin");
        body = getDataPersonOrDoctor(user, password, body);

        emailSend(user.getPerson() != null ? user.getPerson().getPersonEmail() : user.getDoctor().getEmail(), "Cambio de contraseña exitoso por el Administrador", body);
    }

    @Override
    @Transactional
    public void sendUserCredentials(UUID userAuth) {
        User user = userServiceShared.getUserById(userAuth);
        String body = emailTemplateService.getEmailTemplateByName("new_user");
         body = body.replace("{charge}", user.getDoctor() != null ? "Doctor" : "Paciente");
        body = getDataPersonOrDoctor(user, user.getDoctor() != null ? user.getDoctor().getDocument().toLowerCase()+".$" : null, body);

        emailSend(user.getPerson() != null ? user.getPerson().getPersonEmail() : user.getDoctor().getEmail(), "Detalles de tu nueva cuenta de usuario", body);
    }

    private String getDataPersonOrDoctor(User user, String randomPassword, String body) {
        body = body.replace("person_name", user.getPerson() != null ? user.getPerson().getPersonName() : user.getDoctor().getName());
        body = body.replace("user_name", user.getUserName());
        if(randomPassword != null){
            body = body.replace("<!--", "");
            body = body.replace("-->", "");
            body = body.replace("user_password", randomPassword);
        }
        body = body.replace("current_year", String.valueOf(Year.now().getValue()));
        body = body.replace("redirect", urlFront);
        return body;
    }

    private void emailSend(String email, String subject, String body) {
        EmailRequest emailRequest = EmailRequest.create(
                email,
                subject,
                body
        );
        emailService.sendEmail(emailRequest);
    }
}
