package com.techietester.repository;

import com.techietester.model.VideoGame;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class VideoGameRepository {

    private final JdbcTemplate jdbcTemplate;

    public VideoGameRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<VideoGame> findAll() {
        return jdbcTemplate.query("SELECT * FROM videogames", new BeanPropertyRowMapper<>(VideoGame.class));
    }

    public VideoGame findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM videogames WHERE id = ?", new BeanPropertyRowMapper<>(VideoGame.class), id);
    }

    public int save(VideoGame game) {
        return jdbcTemplate.update(
                "INSERT INTO videogames (name, genre, platform, rating) VALUES (?, ?, ?, ?)",
                game.getName(), game.getGenre(), game.getPlatform(), game.getRating());
    }

    public int update(int id, VideoGame game) {
        return jdbcTemplate.update(
                "UPDATE videogames SET name=?, genre=?, platform=?, rating=? WHERE id=?",
                game.getName(), game.getGenre(), game.getPlatform(), game.getRating(), id);
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM videogames WHERE id=?", id);
    }
}
