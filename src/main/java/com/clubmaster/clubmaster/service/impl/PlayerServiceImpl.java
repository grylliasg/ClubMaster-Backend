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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Player> getPlayersByTeamId(Integer teamId) {
        if (!teamRepository.existsById(teamId)) {
            throw new ResourceNotFoundException("Team not found");
        }

        return playerRepository.findByTeamId(teamId);
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

        Player player = new Player(playerDto.getFirstName(), playerDto.getLastName(), playerDto.getPosition(), playerDto.getDescription(), playerDto.getDateOfBirth(), playerDto.getTeam());

        Player newplayer = playerRepository.save(player);

        return new PlayerResponseDto(newplayer.getId(), newplayer.getFirstName(), newplayer.getLastName(), newplayer.getPosition(), newplayer.getDescription(), newplayer.getDateOfBirth(), newplayer.getTeam().getId());
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
    public void transferPlayer(Integer playerId, Integer newTeamId) {
        // Find the new team
        Team newTeam = teamRepository.findById(newTeamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found"));

        // Find the player
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found"));

        // Cannot transfer to his current team
        if (newTeamId.equals(player.getTeam().getId())) {
            throw new ResourceAlreadyExistsException("Player cannot be transferred to his current team");
        }

        player.setTeam(newTeam);

        playerRepository.save(player);
    }

    @Override
    public Page<PlayerResponseDto> getPlayers(String search, Pageable pageable) {

        Page<Player> players;

        if (search == null || search.isBlank()) {
            players = playerRepository.findAll(pageable);
        } else {
            players = playerRepository
                    .findByLastNameIgnoreCase(
                            search,
                            pageable
                    );
        }

        return players.map(player ->
                new PlayerResponseDto(
                        player.getId(),
                        player.getFirstName(),
                        player.getLastName(),
                        player.getPosition(),
                        player.getDescription(),
                        player.getDateOfBirth(),
                        player.getTeam().getId()
                )
        );
    }
}
