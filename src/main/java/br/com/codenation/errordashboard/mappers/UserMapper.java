package br.com.codenation.errordashboard.mappers;

import br.com.codenation.errordashboard.domain.dto.UserDTO;
import br.com.codenation.errordashboard.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

//@Mapper(componentModel="spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "last_seen", target = "last_seen", dateFormat = "yyyy-MM-dd HH:mm")
    })
    UserDTO map(User model);

    List<UserDTO> map(List<User> users);
}