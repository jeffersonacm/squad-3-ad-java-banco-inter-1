package br.com.codenation.errordashboard.service.interfaces;

import br.com.codenation.errordashboard.domain.dto.FilterLogDTO;
import br.com.codenation.errordashboard.domain.dto.LogDTO;
import br.com.codenation.errordashboard.domain.entity.Log;
import br.com.codenation.errordashboard.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LogServiceInterface {

    LogDTO create(LogDTO logDTO, User user);
    void delete(Long id);
    void archive(Long id);
    List<LogDTO> filter(FilterLogDTO filter);
    LogDTO getById(Long id);
    List<LogDTO> getAll();
}
