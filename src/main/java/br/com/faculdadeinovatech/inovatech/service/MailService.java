package br.com.faculdadeinovatech.inovatech.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contato@inovatech.com", "Inovatech Support");
        helper.setTo(recipientEmail);

        String subject = "Aqui está a ligação para redefinir sua senha";

        String content = "<p>Olá,</p>"
                + "<p>Você solicitou a redefinição de sua senha.</p>"
                + "<p>Clique na hiperligação abaixo para mudar sua senha:</p>"
                + "<p><a href=\"" + link + "\">Mudar minha senha</a></p>"
                + "<br>"
                + "<p>Ignore este correio eletrônico se você não fez a solicitação.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }
}
