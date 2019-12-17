package br.com.codenation.errordashboard.endpoints;

import br.com.codenation.errordashboard.domain.dto.UserDTO;
import br.com.codenation.errordashboard.domain.entity.Role;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.exceptions.UserNotFoundException;
import br.com.codenation.errordashboard.service.impl.UserRoleService;
import br.com.codenation.errordashboard.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Api(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    private final Long roledefaultId = 2l;

    @ApiOperation(value = "Create a new User")
    @PostMapping(value = "/create", consumes = "application/json")
    private ResponseEntity createUser(@RequestBody User user) {
        User userSaved = userService.save(user);
        userRoleService.save(userSaved, Role.builder().id(roledefaultId).build());

        return ResponseEntity.status(HttpStatus.CREATED).body(User.toUserDto(userSaved));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void userNotFoundHandler(UserNotFoundException ex){

    }
}
