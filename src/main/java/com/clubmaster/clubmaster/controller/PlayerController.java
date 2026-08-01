package com.clubmaster.clubmaster.controller;

import com.clubmaster.clubmaster.entity.Player;
import com.clubmaster.clubmaster.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{teamName}")
    public List<Player> getPlayersByTeamName(@PathVariable String teamName) {
        return playerService.getPlayersByTeamName(teamName);
    }

    @GetMapping
    public Player getPlayerByName(@RequestParam String firstName, @RequestParam String lastName) {
        return playerService.getPlayerByName(firstName, lastName);
    }

    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable Integer id, @RequestBody Player player) {
        player.setId(id);
        return playerService.updatePlayer(player);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Integer id) {
        playerService.deletePlayerById(id);
    }
}
