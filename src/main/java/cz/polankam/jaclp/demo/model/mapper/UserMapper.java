package cz.polankam.jaclp.demo.model.mapper;

import cz.polankam.jaclp.demo.model.dto.UserDTO;
import cz.polankam.jaclp.demo.model.entity.UserEntity;
import org.mapstruct.Mapper;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(UserEntity entity);
}
