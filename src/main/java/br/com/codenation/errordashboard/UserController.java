package br.com.codenation.errordashboard;

import br.com.codenation.errordashboard.domain.User;
import br.com.codenation.errordashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users/{username}")
    private User getUser(@PathVariable("username") String userName) {
        return userService.getUserDetails(userName);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void userNotFoundHandler(UserNotFoundException ex){

    }
}
