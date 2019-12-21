package br.com.codenation.errordashboard.domain.dao;

import br.com.codenation.errordashboard.domain.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface LogDAO extends JpaRepository<Log, Long>, JpaSpecificationExecutor<Log> {

    /*@Query("SELECT Log FROM Log log JOIN log.environment environment " +
            "JOIN log.level level " +
            "WHERE environment.id = :environmentId " +
            "and :keywordType= :keyword " +
            "order by :orderBy")
    List<Log> filter(@Param("environmentId") Long environmentId,
                          @Param("keywordType") String keywordType,
                          @Param("keyword") String keyword,
                          @Param("orderBy") String orderBy);*/
}
