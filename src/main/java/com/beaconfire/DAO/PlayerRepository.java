package com.beaconfire.DAO;

import com.beaconfire.domain.Player;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PlayerRepository {
    private final JdbcTemplate jdbcTemplate;

    public PlayerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Player player) {
        return jdbcTemplate.update(
                "INSERT INTO players (first_name, last_name, birth_date, gender, height_cm, weight_kg, career_titles, career_wins, country, ranking, team_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                player.getFirstName(), player.getLastName(), player.getBirthDate(), player.getGender(),
                player.getHeightCm(), player.getWeightKg(), player.getCareerTitles(), player.getCareerWins(),
                player.getCountry(), player.getRanking(), player.getTeamId()
        );
    }

    public List<Player> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM players",
                new BeanPropertyRowMapper<>(Player.class)
        );
    }

    public List<Player> findAllPaginatedAndSorted(int page, int size, String sortBy, String direction) {
        String sortRule;
        switch(sortBy) {
            case "age": sortRule="birth_date"; break;
            case "height": sortRule = "height_cm"; break;
            case "careerTitle": sortRule = "career_titles"; break;
            case "careerWins": sortRule = "career_wins"; break;
            default: sortRule = "player_id";
        }

        if(!"asc".equalsIgnoreCase(direction) && !"desc".equalsIgnoreCase(direction)) {
            direction = "asc";
        }
        int offset = page * size;
        String sql = "SELECT * FROM players ORDER BY " + sortRule + " " + direction + " LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Player.class), size, offset);
    }

    public Player findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM players WHERE player_id = ?",
                new BeanPropertyRowMapper<>(Player.class),
                id
        );
    }

    public int update(Player player) {
        return jdbcTemplate.update(
                "UPDATE players SET first_name=?, last_name=?, birth_date=?, gender=?, height_cm=?, weight_kg=?, " +
                        "career_titles=?, career_wins=?, country=?, ranking=?, team_id=? WHERE player_id=?",
                player.getFirstName(), player.getLastName(), player.getBirthDate(), player.getGender(),
                player.getHeightCm(), player.getWeightKg(), player.getCareerTitles(), player.getCareerWins(),
                player.getCountry(), player.getRanking(), player.getTeamId(), player.getPlayerId()
        );
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM players WHERE player_id=?", id);
    }

}
