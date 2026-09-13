package com.clubmaster.clubmaster.controller;

import com.clubmaster.clubmaster.dto.player.CreatePlayerDto;
import com.clubmaster.clubmaster.dto.player.PlayerResponseDto;
import com.clubmaster.clubmaster.dto.player.TransferPlayerDto;
import com.clubmaster.clubmaster.entity.Player;
import com.clubmaster.clubmaster.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/by-name")
    public Player getPlayerByName(
            @RequestParam String firstName,
            @RequestParam String lastName
    ) {
        return playerService.getPlayerByName(firstName, lastName);
    }

    @GetMapping
    public Page<PlayerResponseDto> getPlayers(
            @RequestParam(required = false) String search,
            Pageable pageable
    ) {
        return playerService.getPlayers(search, pageable);
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Integer id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping
    public PlayerResponseDto createPlayer(@Valid @RequestBody CreatePlayerDto playerDto) {
        return playerService.createPlayer(playerDto);
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

    @PatchMapping("/{id}")
    public void transferPlayer(@PathVariable Integer id, @Valid @RequestBody TransferPlayerDto transferDto) {
        playerService.transferPlayer(id, transferDto.getTeamId());
    }
}
