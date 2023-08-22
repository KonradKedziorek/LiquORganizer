package pl.kedziorek.liquorganizer.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${defaultEmailAddress}")
    private String defaultEmailAddress;

    @Value("${confirmationLink}")
    private String confirmationLink;

    @Override
    public void sendMail(SimpleMailMessage msg) {
        if (msg != null) {
            javaMailSender.send(msg);
            log.info("Sending an email...");
        } else {
            log.info("Failed to send mail!");
        }
    }

    @Override
    public SimpleMailMessage confirmationMail(String email, String token) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setFrom(defaultEmailAddress);
        msg.setSubject("Confirmation of account creating");
        msg.setText("Hello user! \n\n" +
                "Your account need confirmation\\n\\n" +
                "Click to confirm: " + confirmationLink + token + "\n\n" +
                "With regards, \n" +
                "MPKOperator");

        return msg;
    }
}
