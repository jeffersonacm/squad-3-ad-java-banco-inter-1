package br.com.codenation.errordashboard.repository;

import br.com.codenation.errordashboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    List<User> findAll();
}
