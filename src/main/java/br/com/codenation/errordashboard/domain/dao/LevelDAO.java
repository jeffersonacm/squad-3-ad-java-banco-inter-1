package br.com.codenation.errordashboard.domain.dao;

import br.com.codenation.errordashboard.domain.entity.Level;
import br.com.codenation.errordashboard.domain.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LevelDAO extends JpaRepository<Level, Long>, JpaSpecificationExecutor<Level> {

    Level getByName(String name);
}
