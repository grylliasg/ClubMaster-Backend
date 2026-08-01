package com.clubmaster.clubmaster.service.impl;

import com.clubmaster.clubmaster.dto.UserDTO;
import com.clubmaster.clubmaster.entity.User;
import com.clubmaster.clubmaster.repository.UserRepository;
import com.clubmaster.clubmaster.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO findByLogin(String username) {

        // Convert User to UserDTO to prevent exposing the user's password to the client:
        User user = userRepository.findByLogin(username);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());

        return userDTO;
    }

}
