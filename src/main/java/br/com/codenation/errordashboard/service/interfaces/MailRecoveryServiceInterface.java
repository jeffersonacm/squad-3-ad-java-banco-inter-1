package br.com.codenation.errordashboard.service.interfaces;

import br.com.codenation.errordashboard.domain.entity.MailRecovery;

public interface MailRecoveryServiceInterface {

    MailRecovery save(String mail);

    String generateCode();

    MailRecovery validToken(String token);

    void updateStatus(MailRecovery mailRecovery, Integer MailStatus);
}
