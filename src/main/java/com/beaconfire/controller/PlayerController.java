package com.beaconfire.controller;

import com.beaconfire.domain.Player;
import com.beaconfire.domain.Views;
import com.beaconfire.service.PlayerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/players")
public class PlayerController {
    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    @JsonView(Views.Regular.class) // Default view for regular users
    public List<Player> getAllPlayers(@RequestParam(required = false, defaultValue = "false") boolean isAdmin) {
        if (isAdmin) {
            return service.getAllPlayers(); // Use Admin view
        }
        return service.getAllPlayers(); // Regular view
    }

    @GetMapping("/{id}")
    @JsonView(Views.Regular.class)
    public Player getPlayer(@PathVariable int id,
                            @RequestParam(required = false, defaultValue = "false") boolean isAdmin) {
        return service.getPlayerById(id);
    }

    @PostMapping
    public ResponseEntity<String> createPlayer(@RequestBody Player player) {
        service.createPlayer(player);
        return ResponseEntity.ok("Player created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlayer(@PathVariable int id, @RequestBody Player player) {
        int rows = service.updatePlayer(id, player);
        if (rows > 0) return ResponseEntity.ok("Player updated successfully");
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable int id) {
        int rows = service.deletePlayer(id);
        if (rows > 0) return ResponseEntity.ok("Player deleted successfully");
        else return ResponseEntity.notFound().build();
    }
}
