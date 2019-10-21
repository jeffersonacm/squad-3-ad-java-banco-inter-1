package br.com.codenation.errordashboard.service;

import br.com.codenation.errordashboard.UserNotFoundException;
import br.com.codenation.errordashboard.repository.UserRepository;
import br.com.codenation.errordashboard.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public User getUserDetails(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
