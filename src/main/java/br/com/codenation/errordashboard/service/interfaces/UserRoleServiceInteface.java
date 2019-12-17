package br.com.codenation.errordashboard.service.interfaces;

import br.com.codenation.errordashboard.domain.entity.Role;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.domain.entity.UserRole;

public interface UserRoleServiceInteface {

    UserRole save(User user, Role role);
}
