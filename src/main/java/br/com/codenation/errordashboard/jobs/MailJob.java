package br.com.codenation.errordashboard.jobs;

import br.com.codenation.errordashboard.service.impl.MailRecoveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Slf4j
public class MailJob {

    @Autowired
    MailRecoveryService mailRecoveryService;

    @Scheduled(fixedRate = 1000000)
    public void expireVerificationToken() {
        log.info("iniciando a busca");
        log.info(mailRecoveryService.expireTokens() + " tokens expirados!");
    }
}
