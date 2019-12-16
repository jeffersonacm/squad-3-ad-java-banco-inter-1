package br.com.codenation.errordashboard.domain.dao;

import br.com.codenation.errordashboard.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDAO extends JpaRepository<UserRole, Long> {

}
