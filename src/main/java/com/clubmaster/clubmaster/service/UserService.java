package com.clubmaster.clubmaster.service;

import com.clubmaster.clubmaster.dto.UserDTO;
import com.clubmaster.clubmaster.entity.User;

public interface UserService {

    UserDTO findByLogin(String username);

}
