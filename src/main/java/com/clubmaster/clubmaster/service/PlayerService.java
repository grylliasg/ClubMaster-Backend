package com.clubmaster.clubmaster.service;

import com.clubmaster.clubmaster.dto.player.CreatePlayerDto;
import com.clubmaster.clubmaster.dto.player.PlayerResponseDto;
import com.clubmaster.clubmaster.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlayerService {

    List<Player> getPlayersByTeamId(Integer teamId);

    Player getPlayerByName(String firstName, String lastName);

    Player getPlayerById(Integer id);

    PlayerResponseDto createPlayer(CreatePlayerDto playerDto);

    Player updatePlayer(Player player);

    void deletePlayerById(Integer id);

    void transferPlayer(Integer playerId, Integer newTeamId);

    Page<PlayerResponseDto> getPlayers(
            String search,
            Pageable pageable
    );
}
