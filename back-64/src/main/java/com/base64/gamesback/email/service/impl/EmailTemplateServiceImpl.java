package com.base64.gamesback.email.service.impl;

import com.base64.gamesback.email.entity.EmailTemplate;
import com.base64.gamesback.email.repository.EmailTemplateRepository;
import com.base64.gamesback.email.service.EmailTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailTemplateServiceImpl implements EmailTemplateService {

    private final EmailTemplateRepository emailTemplateRepository;

    public EmailTemplateServiceImpl(EmailTemplateRepository emailTemplateRepository) {
        this.emailTemplateRepository = emailTemplateRepository;
    }

    @Override
    public String getEmailTemplateByName(String name) {
        try{
            EmailTemplate emailTemplate = emailTemplateRepository.getEmailTemplateByName(name);
            emailTemplate.replaceBody(emailTemplate.body());
            return emailTemplate.body();
        }catch (Exception e){
            log.error("Error en el servicio de correos, inténtelo luego; Error: [{}]", e.getMessage());
            return null;
        }
    }
}
