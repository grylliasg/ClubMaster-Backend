package com.clubmaster.clubmaster.repository;

import com.clubmaster.clubmaster.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String username);
}
