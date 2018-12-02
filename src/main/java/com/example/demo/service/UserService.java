package com.example.demo.service;


import com.example.demo.domain.UserEntity;
import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    private final UserMapper userMapper;


    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserDTO> getUser(Long id) {
        return userRepository.getOneById(id).map(userMapper::toDto);
    }
    public UserEntity getUserEntity(Long id) {
        return userRepository.getOne(id);
    }

    public Optional<UserEntity> getMaybeUser(Long id) {
        return userRepository.getOneById(id);
    }
}
