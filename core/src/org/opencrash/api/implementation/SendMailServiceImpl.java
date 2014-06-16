package org.opencrash.api.implementation;

import org.opencrash.api.SendMailService;
import org.opencrash.util.mail.MailService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by Fong on 21.05.14.
 */
public class SendMailServiceImpl implements SendMailService {
    public void sendMail(String to,String subject,String body){
        ApplicationContext context = new FileSystemXmlApplicationContext("application-context.xml");
        MailService mailer = (MailService) context.getBean("MailService");
        mailer.sendMail(to,subject,body);
    }
}
