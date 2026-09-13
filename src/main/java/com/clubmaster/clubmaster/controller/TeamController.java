package com.clubmaster.clubmaster.controller;

import com.clubmaster.clubmaster.entity.Player;
import com.clubmaster.clubmaster.entity.Team;
import com.clubmaster.clubmaster.service.PlayerService;
import com.clubmaster.clubmaster.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;
    private final PlayerService playerService;

    public TeamController(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping(params = "name")
    public Team getTeamByName(@RequestParam String name) {
        return teamService.findByName(name);
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Integer id) {
        return teamService.findById(id);
    }

    @GetMapping("/{id}/players")
    public List<Player> getPlayersByTeamId(@PathVariable Integer id) {
        return playerService.getPlayersByTeamId(id);
    }

    @PostMapping
    public Team createTeam(@Valid @RequestBody Team team) {
        return teamService.createTeam(team);
    }

    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable Integer id, @RequestBody Team team) {
        team.setId(id);
        return teamService.updateTeam(team);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Integer id) {
        teamService.deleteTeam(id);
    }
}
