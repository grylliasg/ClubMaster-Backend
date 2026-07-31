package com.clubmaster.clubmaster.service.impl;

import com.clubmaster.clubmaster.entity.User;
import com.clubmaster.clubmaster.repository.TeamRepository;
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
    public User findByLogin(String username) {
        return userRepository.findByLogin(username);
    }

}
