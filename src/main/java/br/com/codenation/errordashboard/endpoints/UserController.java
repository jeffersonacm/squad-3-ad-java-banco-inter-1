package br.com.codenation.errordashboard.endpoints;

import br.com.codenation.errordashboard.domain.dto.UserDTO;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.exceptions.UserNotFoundException;
import br.com.codenation.errordashboard.service.impl.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Api(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get User by email")
    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private User getUser(@PathVariable("email") String email) {
        return userService.getUserDetails(email);
    }

    @ApiOperation(value = "Create a new User")
    @PostMapping("/create")
    private ResponseEntity createUser(@Valid @RequestParam String name,
                                      @Valid @RequestParam String email,
                                      @Valid @RequestParam String password
                                      ) {
        UserDTO userDTO = userService.createUser(name, email, password);

        return ResponseEntity.ok(userDTO);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void userNotFoundHandler(UserNotFoundException ex){

    }
}
