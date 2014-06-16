package org.opencrash.util.mail;

/**
 * Created by Fong on 21.05.14.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("MailService")
public class MailService {
    @Autowired
    private MailSender mailSender;

    public void sendMail( String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

}
