package br.com.codenation.errordashboard.endpoints;

import br.com.codenation.errordashboard.domain.dto.RecoveryPasswordDTO;
import br.com.codenation.errordashboard.domain.entity.MailRecovery;
import br.com.codenation.errordashboard.domain.entity.Role;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.domain.enums.MailStatus;
import br.com.codenation.errordashboard.exceptions.UserNotFoundException;
import br.com.codenation.errordashboard.service.impl.MailRecoveryService;
import br.com.codenation.errordashboard.service.impl.UserRoleService;
import br.com.codenation.errordashboard.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/users")
@Api(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private MailRecoveryService mailRecoveryService;

    private final Long roledefaultId = 2l;

    @Transactional
    @ApiOperation(value = "Create a new User")
    @PostMapping(consumes = "application/json")
    public ResponseEntity createUser(@RequestBody User user) {
        try {
            User userSaved = userService.save(user);
            userRoleService.save(userSaved, Role.builder().id(roledefaultId).build());

            return ResponseEntity.status(HttpStatus.CREATED).body(User.toUserDto(userSaved));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void userNotFoundHandler(UserNotFoundException ex){

    }

    @Transactional
    @ApiOperation(value = "Change user password")
    @PutMapping(consumes = "application/json")
    public ResponseEntity changePassword(@RequestBody RecoveryPasswordDTO recoveryPasswordDTO) {
        try {
            MailRecovery mailRecovery = mailRecoveryService.validToken(recoveryPasswordDTO.getSecurityHash());
            User user = mailRecovery.getUser();
            mailRecoveryService.updateStatus(mailRecovery, MailStatus.COMFIRMED.ordinal());

            return ResponseEntity.status(HttpStatus.OK).body(userService.changePassword(recoveryPasswordDTO, user));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
