package com.base64.gamesback.email.service.impl;

import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import com.base64.gamesback.email.dto.EmailRequest;
import com.base64.gamesback.email.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(EmailRequest request){
        MimeMessage mimeMessage = mimeMessage(request);
        javaMailSender.send(mimeMessage);
    }

    private MimeMessage mimeMessage(EmailRequest request) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,true,"utf-8");
            mimeMessageHelper.setTo(request.getDestination());
            message.setSubject(request.getSubject());
            mimeMessageHelper.setText(request.getBody(),true);
            mimeMessageHelper.setFrom(sender);
            if (request.getCopy() != null){ // se verifica si el correo va con copia
                mimeMessageHelper.setCc(request.getCopy());
            }
            if(request.getAttachedName() != null && request.getAttached() != null){
                mimeMessageHelper.addAttachment(request.getAttachedName(), Objects.requireNonNull(this.generateFile(request.getAttached())));
            }
            return message;
        }catch (MessagingException ex) {
            log.error("Error generando el MimeMessage ({})", ex.getMessage());
            throw new IllegalArgumentException("Error generando el MimeMessage {}");
        }
    }

    private File generateFile(String fileBased64) {
        try {
            File file = new File("temp/"+ UUID.randomUUID());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] data  = Base64.decodeBase64(fileBased64);
            fileOutputStream.write(data);
            fileOutputStream.close();
            return file;
        }catch (IOException ex){
            log.error(ex.getMessage());
            throw new ResourceNotFoundException("error al generar el archivo a adjuntar");
        }
    }
}
