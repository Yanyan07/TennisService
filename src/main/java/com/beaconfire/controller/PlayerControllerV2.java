package com.beaconfire.controller;

import com.beaconfire.domain.Player;
import com.beaconfire.domain.Views;
import com.beaconfire.service.PlayerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v2/players")
public class PlayerControllerV2 {
    private final PlayerService service;

    public PlayerControllerV2(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    @JsonView(Views.Regular.class)
    public List<Player> getAllPlayers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "player_id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return service.getAllPlayersPaginatedAndSorted(page, size, sortBy, direction);
    }

}
