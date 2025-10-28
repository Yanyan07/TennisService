package com.beaconfire.service;

import com.beaconfire.DAO.TeamRepository;
import com.beaconfire.domain.Player;
import com.beaconfire.domain.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<Team> getAllTeams() {
        return repository.findAllTeams();
    }

    public List<Player> getPlayersByTeamId(int teamId) {
        return repository.findPlayersByTeamId(teamId);
    }
}
