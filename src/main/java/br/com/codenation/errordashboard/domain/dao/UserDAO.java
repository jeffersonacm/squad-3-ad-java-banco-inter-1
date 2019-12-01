package br.com.codenation.errordashboard.domain.dao;

import br.com.codenation.errordashboard.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByEmail(String email);
    List<User> findAll();
}
