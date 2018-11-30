package com.example.demo.mapper;

import com.example.demo.domain.UserEntity;
import com.example.demo.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserEntity, UserDTO> {
    @Override
    public UserEntity toEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setLogin(userDTO.getLogin());
        return userEntity;
    }

    @Override
    public UserDTO toDto(UserEntity userEntity) {
        UserDTO dto = new UserDTO();
        dto.setId(userEntity.getId());
        dto.setFirstName(userEntity.getFirstName());
        dto.setLastName(userEntity.getLastName());
        dto.setLogin(userEntity.getLogin());
        return dto;
    }
}
