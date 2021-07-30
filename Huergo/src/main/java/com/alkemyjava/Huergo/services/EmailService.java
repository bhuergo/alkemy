package com.alkemyjava.Huergo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(String to, String subject, String body) {
        new Thread(() -> {
            try {
                MimeMessage message = sender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
                helper.setFrom(from);
                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText(body, true);

                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(body, "text/html");

                sender.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }).run();
    }

    public String body(String username, String password) {
        return ("Bienvenido " + username + " a la página de personajes de Disney" +
                "\nTus datos de acceso son los siguientes:" +
                "\nUsuario: " + username + "" +
                "\nContraseña: " + password);
    }
}
