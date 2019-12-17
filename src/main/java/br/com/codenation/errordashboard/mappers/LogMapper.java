package br.com.codenation.errordashboard.mappers;

import br.com.codenation.errordashboard.domain.dto.LogDTO;
import br.com.codenation.errordashboard.domain.dto.UserDTO;
import br.com.codenation.errordashboard.domain.entity.Log;
import br.com.codenation.errordashboard.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel="spring")
public interface LogMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "level", target = "level"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "logDescription", target = "logDescription"),
            @Mapping(source = "environment", target = "environment"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm")
    })
    LogDTO map(Log model);

    List<LogDTO> map(List<Log> logs);


}
