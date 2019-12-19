package br.com.codenation.errordashboard.mappers;

import br.com.codenation.errordashboard.domain.dto.LogDTO;
import br.com.codenation.errordashboard.domain.entity.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

@Mapper(componentModel="spring")
public interface LogMapper {

    Log toModel(LogDTO dto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "level.getName()", target = "level"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "environment.getName()", target = "environment"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "frequency", target = "frequency"),
            @Mapping(source = "timestamp", target = "timestamp", dateFormat = "yyyy-MM-dd HH:mm")
    })
    LogDTO toDTO(Log model);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "level.getName()", target = "level"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "environment.getName()", target = "environment"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "frequency", target = "frequency"),
            @Mapping(source = "timestamp", target = "timestamp", dateFormat = "yyyy-MM-dd HH:mm")
    })
    Log map(LogDTO model);

    List<LogDTO> map(List<Log> logs);


}
