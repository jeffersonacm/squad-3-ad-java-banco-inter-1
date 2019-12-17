package br.com.codenation.errordashboard.service.impl;

import br.com.codenation.errordashboard.domain.dao.LogDAO;
import br.com.codenation.errordashboard.domain.dto.FilterLogDTO;
import br.com.codenation.errordashboard.domain.dto.LogDTO;
import br.com.codenation.errordashboard.domain.entity.Log;
import br.com.codenation.errordashboard.domain.entity.LogId;
import br.com.codenation.errordashboard.domain.entity.User;
import br.com.codenation.errordashboard.service.interfaces.LogServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Service
public class LogService implements LogServiceInterface {

    @Autowired
    LogDAO logDAO;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public LogDTO create(LogDTO logDTO, User user) {

        Log log = modelMapper.map(logDTO, Log.class);
        log = logDAO.save(log);
        return modelMapper.map(log, LogDTO.class);
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
        return modelMapper.map(log, LogDTO.class);
    }

    @Override
    public List<LogDTO> filter(FilterLogDTO filter){
       // Log log = logDAO.getOne(id);
      //  logDAO.delete(log);
        return null;
    }
}
