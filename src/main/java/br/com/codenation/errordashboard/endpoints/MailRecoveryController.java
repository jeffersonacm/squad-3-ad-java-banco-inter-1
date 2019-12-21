package br.com.codenation.errordashboard.endpoints;

import br.com.codenation.errordashboard.domain.entity.MailRecovery;
import br.com.codenation.errordashboard.service.impl.MailRecoveryService;
import br.com.codenation.errordashboard.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URLDecoder;

@RestController
@RequestMapping("/mailRecovery")
@Api(value = "mailRecovery")
public class MailRecoveryController {

    @Autowired
    private MailRecoveryService mailRecoveryService;

    @Transactional
    @ApiOperation(value = "Create a mail recovery solicitation")
    @PostMapping
    public ResponseEntity save(@RequestParam("email") String userEmail) {
        try {
            mailRecoveryService.save(userEmail);
            return ResponseEntity.status(HttpStatus.CREATED).body("Solicitação enviada com sucesso");

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
