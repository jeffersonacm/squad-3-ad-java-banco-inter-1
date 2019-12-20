package br.com.codenation.errordashboard.domain.dao;

import br.com.codenation.errordashboard.domain.entity.MailRecovery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRecoveryDAO extends JpaRepository<MailRecovery, Long> {

    MailRecovery findBySecurityHash(String token);
}
