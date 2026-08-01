package com.clubmaster.clubmaster.controller;

import com.clubmaster.clubmaster.entity.Team;
import com.clubmaster.clubmaster.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{name}")
    public Team getTeamByName(@PathVariable String name) {
        return teamService.findByName(name);
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
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
