package com.clubmaster.clubmaster.service;

import com.clubmaster.clubmaster.entity.Player;
import com.clubmaster.clubmaster.entity.Team;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PlayerService {

    List<Player> getPlayersByTeam(Team team);

    Player getPlayerByName(String firstName, String lastName);

    Player createPlayer(Player player);

    Player updatePlayer(Player player);

    void deletePlayerById(Integer id);
}
