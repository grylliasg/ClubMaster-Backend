package com.clubmaster.clubmaster.repository;

import com.clubmaster.clubmaster.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player,Integer> {

    // All CRUD operations (save, findall/byId, deletebyid....) and also:

    Player findByFirstNameAndLastName(String firstName, String lastName);

    List<Player> findByTeamName(String teamName);

    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}