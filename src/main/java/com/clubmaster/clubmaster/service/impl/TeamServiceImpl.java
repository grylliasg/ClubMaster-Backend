package com.clubmaster.clubmaster.service.impl;

import com.clubmaster.clubmaster.entity.Team;
import com.clubmaster.clubmaster.repository.TeamRepository;
import com.clubmaster.clubmaster.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team findByName(String name) {
        return teamRepository.findByName(name);
    }

    @Override
    public Team createTeam(Team team) {
        if (teamRepository.existsByName(team.getName())) {
            return null;
        }
        else return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Team team) {
        if (teamRepository.existsById(team.getId())) {
            return teamRepository.save(team);
        }
        else return null;
    }

    @Override
    public void deleteTeam(Integer id) {
        teamRepository.deleteById(id);
    }
}
