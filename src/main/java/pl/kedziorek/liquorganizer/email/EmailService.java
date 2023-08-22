package pl.kedziorek.liquorganizer.email;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendMail(SimpleMailMessage msg);
    SimpleMailMessage confirmationMail(String email, String token);
}
