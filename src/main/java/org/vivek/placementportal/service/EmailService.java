package org.vivek.placementportal.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    public void sendEmail(SimpleMailMessage simpleMailMessage);
}
