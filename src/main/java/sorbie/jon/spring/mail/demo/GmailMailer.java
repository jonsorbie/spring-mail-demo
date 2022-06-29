package sorbie.jon.spring.mail.demo;

import org.springframework.boot.*;
import org.springframework.mail.*;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.*;

@Component
public class GmailMailer implements CommandLineRunner {

    private final JavaMailSender mailSender;

    public GmailMailer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void run(String... args) {
        System.out.println("Starting spring mail demo...");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("<The_From_address>@<my_host>.com");
        message.setTo("<The_To_address>@<their_host>.com");
        message.setSubject("My subject line");
        message.setText("Body of the email");
        mailSender.send(message);
        System.out.println("Done.");
    }
}
