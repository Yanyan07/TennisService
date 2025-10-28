package com.beaconfire.DAO;

import com.beaconfire.domain.Player;
import com.beaconfire.domain.Team;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TeamRepository {
    private final JdbcTemplate jdbcTemplate;

    public TeamRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Team> findAllTeams() {
        return jdbcTemplate.query("SELECT * FROM teams", new BeanPropertyRowMapper<>(Team.class));
    }

    public List<Player> findPlayersByTeamId(int teamId) {
        return jdbcTemplate.query(
                "SELECT * FROM players WHERE team_id = ?",
                new BeanPropertyRowMapper<>(Player.class),
                teamId
        );
    }
}
