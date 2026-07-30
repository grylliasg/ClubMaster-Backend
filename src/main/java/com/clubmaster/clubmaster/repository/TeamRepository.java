package com.clubmaster.clubmaster.repository;

import com.clubmaster.clubmaster.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Integer> {

    // All CRUD operations (save, findall/byid, deletebyid....) and also:
    public Team findByName(String name);
}
