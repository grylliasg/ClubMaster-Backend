package com.clubmaster.clubmaster.service.impl;

import com.clubmaster.clubmaster.dto.player.CreatePlayerDto;
import com.clubmaster.clubmaster.dto.player.PlayerResponseDto;
import com.clubmaster.clubmaster.entity.Player;
import com.clubmaster.clubmaster.entity.Team;
import com.clubmaster.clubmaster.exception.ResourceAlreadyExistsException;
import com.clubmaster.clubmaster.exception.ResourceNotFoundException;
import com.clubmaster.clubmaster.repository.PlayerRepository;
import com.clubmaster.clubmaster.repository.TeamRepository;
import com.clubmaster.clubmaster.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Player> getPlayersByTeamName(String teamName) {
        List<Player> players = playerRepository.findByTeamName(teamName);

        if (players.isEmpty()) {
            throw new ResourceNotFoundException("Players or Team not found");
        }

        return players;
    }

    @Override
    public Player getPlayerByName(String firstName, String lastName) {
        Player player = playerRepository.findByFirstNameAndLastName(firstName, lastName);

        if (player == null) {
            throw new ResourceNotFoundException("Player not found");
        }

        return player;
    }

    @Override
    public Player getPlayerById(Integer id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found"));
    }

    @Override
    public PlayerResponseDto createPlayer(CreatePlayerDto playerDto) {

        if (playerRepository.existsByFirstNameAndLastName(playerDto.getFirstName(), playerDto.getLastName())) {
            throw new ResourceAlreadyExistsException("Player already exists");
        }

        Player player = new Player(playerDto.getFirstName(), playerDto.getLastName(), playerDto.getPosition(), playerDto.getDateOfBirth(), playerDto.getTeam());

        Player newplayer = playerRepository.save(player);

        return new PlayerResponseDto(newplayer.getId(), newplayer.getFirstName(), newplayer.getLastName(), newplayer.getPosition(), newplayer.getDateOfBirth(), newplayer.getTeam().getId());
    }

    @Override
    public Player updatePlayer(Player player) {
        if (playerRepository.existsById(player.getId())) {
            return playerRepository.save(player);
        } else throw new ResourceNotFoundException("Player not found");
    }

    @Override
    public void deletePlayerById(Integer id) {
        if (!playerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Player not found");
        }

        playerRepository.deleteById(id);
    }

    @Override
    public void transferPlayer(Integer newTeamId, Player player) {
        Team newTeam = teamRepository.findById(newTeamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found"));

        if (newTeam.getId().equals(player.getTeam().getId())) {
            throw new ResourceAlreadyExistsException("Player cannot be transferred to his current team");
        }

        player.setTeam(newTeam);

        playerRepository.save(player);
    }
}
