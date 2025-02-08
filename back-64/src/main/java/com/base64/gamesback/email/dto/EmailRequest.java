package com.base64.gamesback.email.dto;

import lombok.Getter;

@Getter
public class EmailRequest {

    private final String destination;
    private String[] copy;
    private final String subject;
    private final String body;
    private String attached;
    private String attachedName;
    private Boolean queue;

    public EmailRequest(String destination, String subject, String body) {
        this.destination = destination;
        this.subject = subject;
        this.body = body;
        this.queue = false;
    }

    public static EmailRequest create(String destination, String subject, String body) {
        return new EmailRequest(destination, subject, body);
    }

}
