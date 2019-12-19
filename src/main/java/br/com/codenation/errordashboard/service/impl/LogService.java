package br.com.codenation.errordashboard.service.impl;

import br.com.codenation.errordashboard.domain.dao.LogDAO;
import br.com.codenation.errordashboard.domain.dao.UserDAO;
import br.com.codenation.errordashboard.domain.dto.FilterLogDTO;
import br.com.codenation.errordashboard.domain.dto.LogDTO;
import br.com.codenation.errordashboard.domain.entity.Log;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.mappers.LogMapper;
import br.com.codenation.errordashboard.service.interfaces.LogServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class LogService implements LogServiceInterface {


    private LogMapper mapper;

    @Autowired
    private LogDAO logDAO;

    @Override
    @Transactional
    public LogDTO create(LogDTO logDTO, User user) {

        Log log = mapper.map(logDTO);
        log.setTimestamp(new Date());
        log = logDAO.save(log);
        return null;
    }

    @Override
    public Boolean archive(Long id){
        Log log = logDAO.getOne(id);
        log.setStatus(2);
        logDAO.save(log);
        return true;
    }
    @Override
    public Boolean delete(Long id){
        Log log = logDAO.getOne(id);
        logDAO.delete(log);
        return true;
    }

    @Override
    public LogDTO getById(Long id){
        Log log = logDAO.getOne(id);
        return null;
    }

    @Override
    public List<LogDTO> filter(FilterLogDTO filter){

        String typeKeyword = "";
        if(filter.getTypeKeyword().equals("Level")){
            typeKeyword = "level.name";
        }
        else if(filter.getKeyword().equals("Descricao")){
            typeKeyword = "log.description";
        }
        else if(filter.getKeyword().equals("Origem")){
            typeKeyword = "origin";
        }

        String orderBy ="";
        if(filter.getOrderBy().equals("L")){
            orderBy = "level.id";
        }
        else if(filter.getOrderBy().equals("F")){
            orderBy = "frequency";
        }

      // ArrayList<Log> logs = logDAO.filter(filter.getEnvironment(), orderBy, typeKeyword, filter.getKeyword());
        return null;
    }
}
