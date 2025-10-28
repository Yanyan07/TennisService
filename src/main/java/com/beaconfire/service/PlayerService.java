package com.beaconfire.service;

import com.beaconfire.DAO.PlayerRepository;
import com.beaconfire.domain.Player;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> getAllPlayers() {
        return repository.findAll();
    }

    public Player getPlayerById(int id) {
        return repository.findById(id);
    }

    public int createPlayer(Player player) {
        return repository.save(player);
    }

    public int updatePlayer(int id, Player player) {
        player.setPlayerId(id);
        return repository.update(player);
    }

    public int deletePlayer(int id) {
        return repository.deleteById(id);
    }
}
