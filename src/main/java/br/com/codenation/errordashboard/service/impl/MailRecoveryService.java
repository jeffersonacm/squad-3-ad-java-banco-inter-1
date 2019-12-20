package br.com.codenation.errordashboard.service.impl;

import br.com.codenation.errordashboard.domain.dao.MailRecoveryDAO;
import br.com.codenation.errordashboard.domain.entity.MailRecovery;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.domain.enums.MailStatus;
import br.com.codenation.errordashboard.exceptions.InvalidOrExpiredToken;
import br.com.codenation.errordashboard.exceptions.UserNotFoundException;
import br.com.codenation.errordashboard.service.interfaces.MailRecoveryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MailRecoveryService implements MailRecoveryServiceInterface {

    @Autowired
    private MailRecoveryDAO mailRecoveryDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    public MailRecovery save(String mail) {
        User user = userService.findByEmail(mail);

        if(user == null) {
            throw new UserNotFoundException();
        }

        MailRecovery mailRecovery = MailRecovery.builder()
                .user(user)
                .securityHash(generateCode())
                .status(MailStatus.ACTIVE.ordinal())
                .createdAt(LocalDateTime.now())
                .build();

        MailRecovery mailRecoverySaved = mailRecoveryDAO.save(mailRecovery);

        emailService.sendrecoveryEmail(user, mailRecoverySaved);

        return mailRecoverySaved;
    }

    public String generateCode() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public MailRecovery validToken(String token) {
        MailRecovery mailRecovery = mailRecoveryDAO.findBySecurityHash(token);

        if (mailRecovery.getStatus() != MailStatus.ACTIVE.ordinal()) {
            throw new InvalidOrExpiredToken();
        }

        return mailRecovery;
    }

    public void updateStatus(MailRecovery mailRecovery, Integer MailStatus) {
        mailRecovery.setStatus(MailStatus);
        mailRecoveryDAO.save(mailRecovery);
    }
}
