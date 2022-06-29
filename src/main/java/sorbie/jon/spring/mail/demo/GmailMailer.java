package sorbie.jon.spring.mail.demo;

import org.springframework.boot.*;
import org.springframework.core.io.*;
import org.springframework.mail.*;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.*;

import javax.mail.internet.*;

@Component
public class GmailMailer implements CommandLineRunner {

    // Replace with real values
    private static final String FROM_ADDRESS = "<The_From_address>@<my_host>.com";
    private static final String TO_ADDRESS = "<The_To_address>@<my_host>.com";

    private final JavaMailSender mailSender;

    public GmailMailer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void run(String... args) throws Exception {
        sendMimeMessage();
    }

    private void sendMimeMessage() throws Exception {
        System.out.println("Starting mime message demo...");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(FROM_ADDRESS);
        helper.setTo(TO_ADDRESS);
        helper.setSubject("My subject line");
        String imageContentId = "image1234"; // arbitrary
        String body =
            "<html><body><h1>In Congress</h1>" +
            "Thank you for signing the Declaration of Independence" +
            "<p>Sincerely," +
            "<br>John Hancock" +
            "<p><img src='cid:" + imageContentId + "'>" +
            "</body></html>";
        helper.setText(body, true);
        Resource resource = new ClassPathResource("/images/john_hancock.jpg");
        helper.addInline(imageContentId, resource);
        mailSender.send(message);
        System.out.println("Done.");
    }

    private void sendSimpleMessage() {
        System.out.println("Starting simple message demo...");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM_ADDRESS);
        message.setTo(TO_ADDRESS);
        message.setSubject("My subject line");
        message.setText("Body of the email");
        mailSender.send(message);
        System.out.println("Done.");
    }
}
