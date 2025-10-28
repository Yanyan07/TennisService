package com.beaconfire.controller;

import com.beaconfire.domain.Player;
import com.beaconfire.domain.Team;
import com.beaconfire.domain.Views;
import com.beaconfire.service.TeamService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/teams")
public class TeamController {
    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return service.getAllTeams();
    }

    @GetMapping("/{id}/players")
    @JsonView(Views.Regular.class) // Default Regular view
    public List<Player> getPlayersByTeam(
            @PathVariable int id,
            @RequestParam(required = false, defaultValue = "false") boolean isAdmin) {

        List<Player> players = service.getPlayersByTeamId(id);
        return players;
    }
}
