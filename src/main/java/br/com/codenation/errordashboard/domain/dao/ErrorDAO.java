package br.com.codenation.errordashboard.domain.dao;

import br.com.codenation.errordashboard.domain.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErrorDAO extends JpaRepository<Log, Long> {
    List<Log> findByEnviromnent(String enviroment);
}
