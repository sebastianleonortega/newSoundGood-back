package com.base64.gamesback.email.service;

import com.base64.gamesback.auth.user.entity.User;

public interface EmailSendService {

    void emailCodeVerification(User user, String codeVerification);

    void recoveryPasswordEmailSent(User user, String urlToken);

    void changePassword(User user);

    void resetUserPassword(User user, String password);

    void sendUserCredentials(User userAuth, String randomPassword);
}
