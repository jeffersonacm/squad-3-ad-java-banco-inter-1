package br.com.codenation.errordashboard.domain.dao;

import br.com.codenation.errordashboard.domain.entity.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErrorDAO extends JpaRepository<Error, Long> {
    List<Error> findByEnviromnent(String enviroment);
}
