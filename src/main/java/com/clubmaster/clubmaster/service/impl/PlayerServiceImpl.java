package com.clubmaster.clubmaster.service.impl;

import com.clubmaster.clubmaster.entity.Player;
import com.clubmaster.clubmaster.entity.Team;
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
    public List<Player> getPlayersByTeam(Team team) {
        return playerRepository.findByTeamId(team.getId());
    }

    @Override
    public Player getPlayerByName(String firstName, String lastName) {
        return playerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Player createPlayer(Player player){
        if (playerRepository.existsByFirstNameAndLastName(player.getFirstName(), player.getLastName())) {
            return null;
        }
        else return playerRepository.save(player);
    }

    @Override
    public Player updatePlayer(Player player){
        if (playerRepository.existsById(player.getId())) {
            return playerRepository.save(player);
        }
        else return null;
    }

    @Override
    public void deletePlayerById(Integer id){
        playerRepository.deleteById(id);
    }
}
