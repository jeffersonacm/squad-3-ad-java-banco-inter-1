package br.com.codenation.errordashboard.service.impl;

import br.com.codenation.errordashboard.domain.dto.RecoveryPasswordDTO;
import br.com.codenation.errordashboard.domain.dto.UserDTO;
import br.com.codenation.errordashboard.exceptions.UserEmailExistsException;
import br.com.codenation.errordashboard.domain.dao.UserDAO;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User save(User user) {

        emailValidate(user.getEmail());
        user.setPasswordHash(hashPassword(user.getPasswordHash()));
        user.setLastSeen(LocalDateTime.now());
        user.setToken(generateToken()+generateToken());

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

    public UserDTO changePassword(RecoveryPasswordDTO recoveryPasswordDTO, User user) {
        user.setPasswordHash(hashPassword(recoveryPasswordDTO.getPassword()));

        User userSaved = userDAO.save(user);

        return User.toUserDto(userSaved);
    }

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
