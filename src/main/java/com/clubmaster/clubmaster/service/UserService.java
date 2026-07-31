package com.clubmaster.clubmaster.service;

import com.clubmaster.clubmaster.entity.User;

public interface UserService {

    User findByLogin(String username);

}
