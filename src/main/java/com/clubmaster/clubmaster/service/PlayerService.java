package com.clubmaster.clubmaster.service;

import com.clubmaster.clubmaster.dto.player.CreatePlayerDto;
import com.clubmaster.clubmaster.dto.player.PlayerResponseDto;
import com.clubmaster.clubmaster.entity.Player;
import com.clubmaster.clubmaster.entity.Team;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PlayerService {

    List<Player> getPlayersByTeamName(String teamName);

    Player getPlayerByName(String firstName, String lastName);

    Player getPlayerById(Integer id);

    PlayerResponseDto createPlayer(CreatePlayerDto playerDto);

    Player updatePlayer(Player player);

    void deletePlayerById(Integer id);

    void transferPlayer(Integer playerId, Integer newTeamId);
}
