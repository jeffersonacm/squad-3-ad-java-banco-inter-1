package br.com.codenation.errordashboard.service;

import br.com.codenation.errordashboard.exceptions.UserNotFoundException;
import br.com.codenation.errordashboard.domain.dao.UserDAO;
import br.com.codenation.errordashboard.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

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
}
