package br.com.codenation.errordashboard.service.impl;

import br.com.codenation.errordashboard.domain.dao.UserRoleDAO;
import br.com.codenation.errordashboard.domain.entity.Role;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.domain.entity.UserRole;
import br.com.codenation.errordashboard.domain.entity.UserRoleId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleDAO userRoleDAO;

    public UserRole CreateRole(Long userId, Long roleId) {

        User user = User.builder().id(userId).build();
        Role role = Role.builder().id(roleId).build();
        UserRole userRole = UserRole.builder()
                .userRoleId(new UserRoleId(user, role))
                .build();

        return userRoleDAO.save(userRole);
    }
}
