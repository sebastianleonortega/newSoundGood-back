package com.base64.gamesback.email.repository;

import com.base64.gamesback.email.entity.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, String> {

    EmailTemplate getEmailTemplateByName(String templateName);
}
