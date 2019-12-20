package br.com.codenation.errordashboard.service.impl;

import br.com.codenation.errordashboard.domain.entity.MailRecovery;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.service.interfaces.EmailServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Slf4j
@Component
public class EmailService implements EmailServiceInterface {

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${default.url}")
    private String contextPath;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendrecoveryEmail(User user, MailRecovery mailRecovery){
        try {
            MimeMessage mimeMessage = prepareMimeMessage(user, mailRecovery);
            sendHtmlEmail(mimeMessage);
        } catch (MessagingException msg) {
            throw new MailSendException("Ocorreu um erro ao realizar o envio do e-mail");
        }
    }

    protected MimeMessage prepareMimeMessage(User user, MailRecovery mailRecovery) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setFrom(this.sender);
            mimeMessageHelper.setSubject("Recuperação de Senha");
            mimeMessageHelper.setSentDate(new Date((System.currentTimeMillis())));
            mimeMessageHelper.setText(htmlFromTemplate(mailRecovery), true);

        return mimeMessage;
    }

    protected String htmlFromTemplate(MailRecovery mailRecovery){
        String recoveryConfirmUrl = this.contextPath + "/mailRecovery/token=" + mailRecovery.getSecurityHash();

        Context context = new Context();
        context.setVariable("recoveryConfirmUrl", recoveryConfirmUrl);

        return templateEngine.process("email/recoveryPassword", context);
    }

    public void sendHtmlEmail(MimeMessage msg) {
        log.info("Iniciando o envio do e-mail!");
        javaMailSender.send(msg);
        log.info("E-mail enviado com sucesso!");
    }
}
