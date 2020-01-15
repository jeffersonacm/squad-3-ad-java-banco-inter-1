package br.com.codenation.errordashboard.service.interfaces;

import br.com.codenation.errordashboard.domain.entity.User;

public interface UserServiceInterface {

    User findByEmail(String email);

    User save(User user);
}
