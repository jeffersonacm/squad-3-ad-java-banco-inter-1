package br.com.codenation.errordashboard.service.impl;

import br.com.codenation.errordashboard.domain.dto.UserDTO;
import br.com.codenation.errordashboard.exceptions.UserEmailExistsException;
import br.com.codenation.errordashboard.exceptions.UserNotFoundException;
import br.com.codenation.errordashboard.domain.dao.UserDAO;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User save(User user) {

        emailValidate(user.getEmail());

        PasswordEncoder passwordEncoder = new PasswordEncoder();

        user.setPasswordHash(passwordEncoder.hash(user.getPasswordHash()));
        user.setLastSeen(LocalDateTime.now());

        return userDAO.save(user);
    }

    public void emailValidate(String email) {
        User user = userDAO.findByEmail(email);

        if(user != null) {
            throw new UserEmailExistsException();
        }
    }

    public User findByEmail(String email){
        return userDAO.findByEmail(email);
    }
}
