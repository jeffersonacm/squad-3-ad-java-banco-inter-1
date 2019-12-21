package br.com.codenation.errordashboard.service.interfaces;

import br.com.codenation.errordashboard.domain.entity.MailRecovery;
import br.com.codenation.errordashboard.domain.entity.User;

import javax.mail.internet.MimeMessage;

public interface EmailServiceInterface {

    void sendrecoveryEmail(User user, MailRecovery mailRecovery);

    void sendHtmlEmail(MimeMessage msg);
}
