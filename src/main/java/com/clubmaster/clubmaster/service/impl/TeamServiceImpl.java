package com.clubmaster.clubmaster.service.impl;

import com.clubmaster.clubmaster.entity.Team;
import com.clubmaster.clubmaster.exception.ResourceAlreadyExistsException;
import com.clubmaster.clubmaster.exception.ResourceNotFoundException;
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
        Team team = teamRepository.findByName(name);

        if (team == null) {
            throw new ResourceNotFoundException("Team not found");
        }

        return team;
    }

    @Override
    public Team createTeam(Team team) {
        if (teamRepository.existsByName(team.getName())) {
            throw new ResourceAlreadyExistsException("Team already exists");
        }

        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Team team) {
        if (!teamRepository.existsById(team.getId())) {
            throw new ResourceNotFoundException("Team not found");
        }

        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Integer id) {
        if (!teamRepository.existsById(id)) {
            throw new ResourceNotFoundException("Team not found");
        }

        teamRepository.deleteById(id);
    }
}
