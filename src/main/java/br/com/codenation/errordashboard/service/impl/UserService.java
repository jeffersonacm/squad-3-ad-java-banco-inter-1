package br.com.codenation.errordashboard.service.impl;

import br.com.codenation.errordashboard.domain.dto.UserDTO;
import br.com.codenation.errordashboard.exceptions.UserEmailExistsException;
import br.com.codenation.errordashboard.exceptions.UserNotFoundException;
import br.com.codenation.errordashboard.domain.dao.UserDAO;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.mappers.UserMapper;
import br.com.codenation.errordashboard.service.interfaces.UserServiceInterface;
import br.com.codenation.errordashboard.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserDAO userDAO;

    protected UserMapper userMapper;

    public UserService(UserDAO userDAO) {

        this.userDAO = userDAO;
    }

    public User getUserDetails(String email) {
        User user = userDAO.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public UserDTO createUser(String name, String email, String password) {

        emailValidate(email);

        PasswordEncoder passwordEncoder = new PasswordEncoder();

        User user = User.builder()
                .name(name)
                .email(email)
                .password_hash(passwordEncoder.hash(password))
                .last_seen(LocalDateTime.now())
                .build();

        User userSaved;
        userSaved = userDAO.save(user);

        return User.toUserDto(userSaved);
    }

    public void emailValidate(String email) {
        User user = userDAO.findByEmail(email);

        if(user != null) {
            throw new UserEmailExistsException();
        }
    }
}
