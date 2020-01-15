package br.com.codenation.errordashboard.domain.dao;

import br.com.codenation.errordashboard.domain.entity.Environment;
import br.com.codenation.errordashboard.domain.entity.Level;
import br.com.codenation.errordashboard.domain.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EnvironmentDAO extends JpaRepository<Environment, Long>, JpaSpecificationExecutor<Environment> {

    Environment getByName(String name);
}
