package com.clubmaster.clubmaster.service.impl;

import com.clubmaster.clubmaster.entity.Player;
import com.clubmaster.clubmaster.exception.ResourceAlreadyExistsException;
import com.clubmaster.clubmaster.exception.ResourceNotFoundException;
import com.clubmaster.clubmaster.repository.PlayerRepository;
import com.clubmaster.clubmaster.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
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

        return  player;
    }

    @Override
    public Player createPlayer(Player player){
        if (playerRepository.existsByFirstNameAndLastName(player.getFirstName(), player.getLastName())) {
            throw new ResourceAlreadyExistsException("Player already exists");
        }

        return playerRepository.save(player);
    }

    @Override
    public Player updatePlayer(Player player){
        if (playerRepository.existsById(player.getId())) {
            return playerRepository.save(player);
        }
        else throw new ResourceNotFoundException("Player not found");
    }

    @Override
    public void deletePlayerById(Integer id) {
        if (!playerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Player not found");
        }

        playerRepository.deleteById(id);
    }
}
