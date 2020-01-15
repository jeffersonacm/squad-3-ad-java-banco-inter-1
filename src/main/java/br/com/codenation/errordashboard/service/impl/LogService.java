package br.com.codenation.errordashboard.service.impl;

import br.com.codenation.errordashboard.domain.dao.EnvironmentDAO;
import br.com.codenation.errordashboard.domain.dao.LevelDAO;
import br.com.codenation.errordashboard.domain.dao.LogDAO;
import br.com.codenation.errordashboard.domain.dto.FilterLogDTO;
import br.com.codenation.errordashboard.domain.dto.LogDTO;
import br.com.codenation.errordashboard.domain.entity.Environment;
import br.com.codenation.errordashboard.domain.entity.Level;
import br.com.codenation.errordashboard.domain.entity.Log;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.service.interfaces.LogServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class LogService implements LogServiceInterface {


    @Autowired
    private LogDAO logDAO;

    @Autowired
    private EnvironmentDAO environmentDAO;

    @Autowired
    private LevelDAO levelDAO;


    @Override
    @Transactional
    public LogDTO create(LogDTO logDTO, User user) {

        Log log = LogVOToEntity(logDTO);
        log.setTimestamp(new Date());

        //Level
        Level level = levelDAO.getByName(logDTO.getLevel());
        if(level == null)
        {
            level = new Level();
            level.setName(logDTO.getLevel());
            level = levelDAO.save(level);

        }

        log.setLevel(level);

        //Environment
        Environment environment = environmentDAO.getByName(logDTO.getEnvironment());
        if(environment == null)
        {
            environment = new Environment();
            environment.setName(logDTO.getLevel());
            environment = environmentDAO.save(environment);

        }
        log.setEnvironment(environment);
        log.setUser(user);
        log = logDAO.save(log);
        return EntityToLogVO(log);
    }

    @Override
    public void archive(Long id) {
        Log log = logDAO.getOne(id);
        log.setStatus(2);
        logDAO.save(log);
    }

    @Override
    public void delete(Long id) {
        Log log = logDAO.getOne(id);
        logDAO.delete(log);
    }

    @Override
    public LogDTO getById(Long id) {
        Log log = logDAO.getOne(id);
        return EntityToLogVO(log);
    }

    @Override
    public List<LogDTO> filter(FilterLogDTO filter) {

        String typeKeyword = "";
        if (filter.getTypeKeyword().equals("Level")) {
            typeKeyword = "level.name";
        } else if (filter.getKeyword().equals("Descricao")) {
            typeKeyword = "log.description";
        } else if (filter.getKeyword().equals("Origem")) {
            typeKeyword = "origin";
        }

        String orderBy = "";
        if (filter.getOrderBy().equals("L")) {
            orderBy = "level.id";
        } else if (filter.getOrderBy().equals("F")) {
            orderBy = "frequency";
        }
        ArrayList<LogDTO> logsDTO= new ArrayList<LogDTO>();
       // List<Log> logs = logDAO.filter(filter.getEnvironment(), orderBy, typeKeyword, filter.getKeyword());
        List<Log> logs = logDAO.findAll();
        for (Log log:
             logs) {
           LogDTO logDTO =  EntityToLogVO(log);
            logsDTO.add(logDTO);
        }
        return logsDTO;
    }

    @Override
    public List<LogDTO> getAll() {

        ArrayList<LogDTO> logsDTO= new ArrayList<LogDTO>();
        List<Log> logs = logDAO.findAll();
        for (Log log:
                logs) {
            LogDTO logDTO =  EntityToLogVO(log);
            logsDTO.add(logDTO);
        }
        return logsDTO;
    }

    public LogDTO EntityToLogVO(Log log) {
        return LogDTO
                .builder()
                .id(log.getId())
                .level(log.getLevel().getName())
                .title(log.getTitle())
                .logDescription(log.getDescription())
                .environment(log.getEnvironment().getName())
                .status(log.getStatus())
                .createdAt(log.getTimestamp())
                .details(log.getDetails())
                .origin(log.getOrigin())
                .frequency(log.getFrequency())
                .build();
    }

    public Log LogVOToEntity(LogDTO logDTO) {
        return Log
                .builder()
                .title(logDTO.getTitle())
                .description(logDTO.getLogDescription())
                .details(logDTO.getDetails())
                .origin(logDTO.getOrigin())
                .status(logDTO.getStatus())
                .frequency(logDTO.getFrequency()).build();
    }
}
