package br.com.codenation.errordashboard.domain.dao;

import br.com.codenation.errordashboard.domain.dto.FilterLogDTO;
import br.com.codenation.errordashboard.domain.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogDAO extends JpaRepository<Log, Long> {

    @Query("SELECT Log FROM Log log JOIN challenge.accelerations acceleration " +
            "JOIN acceleration.candidates " +
            "candidate JOIN candidate.id.user usr  WHERE usr.id = :userId and acceleration.id = :accelerationId")
    List<Log> findAccelerationIdAndUserId(@Param("accelerationId") FilterLogDTO accelerationId);
}
