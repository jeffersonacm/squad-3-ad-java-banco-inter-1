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

    @Autowired
    private UserRoleService userRoleService;

    private final Long roledefaultId = 2l;

    public UserService(UserDAO userDAO) {

        this.userDAO = userDAO;
    }
    public UserDTO createUser(String name, String email, String password) {

        emailValidate(email);

        PasswordEncoder passwordEncoder = new PasswordEncoder();

        User user = User.builder()
                .name(name)
                .email(email)
                .passwordHash(passwordEncoder.hash(password))
                .lastSeen(LocalDateTime.now())
                .build();

        User userSaved;
        userSaved = userDAO.save(user);

        userRoleService.CreateRole(userSaved.getId(), roledefaultId);

        return User.toUserDto(userSaved);
    }

    public void emailValidate(String email) {
        User user = userDAO.findByEmail(email);

        if(user != null) {
            throw new UserEmailExistsException();
        }
    }
}
