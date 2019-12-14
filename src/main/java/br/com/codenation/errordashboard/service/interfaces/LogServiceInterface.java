package br.com.codenation.errordashboard.service.interfaces;

import br.com.codenation.errordashboard.domain.dto.LogDTO;
import br.com.codenation.errordashboard.domain.entity.Log;
import br.com.codenation.errordashboard.domain.entity.User;

public interface LogServiceInterface {

    LogDTO create(LogDTO logDTO, User user);
    Boolean delete(Long id, User user);
    Log archive(Long id, User user);

}
