package br.com.codenation.errordashboard.service.interfaces;

import br.com.codenation.errordashboard.domain.dto.UserDTO;
import br.com.codenation.errordashboard.domain.entity.User;

public interface UserServiceInterface {

    User getUserDetails(String email);

    UserDTO save(User user);
}
