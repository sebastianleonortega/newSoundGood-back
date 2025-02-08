package com.base64.gamesback.email.service;

import com.base64.gamesback.email.dto.EmailRequest;

public interface EmailService {

    void sendEmail(EmailRequest request);

}
