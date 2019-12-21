package br.com.codenation.errordashboard.endpoints;

import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseController {

    @Autowired
    UserService userService;

        public User getUser(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userService.findByEmail(userDetails.getUsername());

        }
}
