package br.com.codenation.errordashboard.service.interfaces;

import br.com.codenation.errordashboard.domain.dao.UserDAO;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.exceptions.UserNotFoundException;

import java.util.List;

public interface UserServiceInterface {

    User getUserDetails(String email);

    List<User> getAllUsers();

}
