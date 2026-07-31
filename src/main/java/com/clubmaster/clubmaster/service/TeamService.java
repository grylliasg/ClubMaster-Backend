package com.clubmaster.clubmaster.service;

import com.clubmaster.clubmaster.entity.Team;

import java.util.List;

public interface TeamService {

    List<Team> getAllTeams();

    Team findByName(String name);

    Team createTeam(Team team);

    Team updateTeam(Team team);

    void deleteTeam(Integer id);
}
