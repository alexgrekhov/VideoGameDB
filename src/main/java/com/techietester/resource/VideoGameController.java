package com.techietester.resource;

import com.techietester.model.VideoGame;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videogames")
@Tag(name = "Video Games", description = "Video Game Database Operations")
public class VideoGameController {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @GetMapping
    @Operation(summary = "Get list of all video games")
    public List<VideoGame> listVideoGames() {
        String sql = "SELECT id, name, released_on AS releaseDate, review_score AS reviewScore, category, rating FROM VIDEOGAME";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(VideoGame.class));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get video game by ID")
    public VideoGame getVideoGame(@PathVariable("id") int id) {
        String sql = "SELECT id, name, released_on AS releaseDate, review_score AS reviewScore, category, rating FROM VIDEOGAME WHERE id=:id";
        return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id), new BeanPropertyRowMapper<>(VideoGame.class));
    }

    @PostMapping
    @Operation(summary = "Add a new video game")
    public String createVideoGame(@RequestBody VideoGame game) {
        String sql = "INSERT INTO VIDEOGAME (id, name, released_on, review_score, category, rating) " +
                "VALUES (:id, :name, :releaseDate, :reviewScore, :category, :rating)";
        jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(game));
        return "{\"status\":\"Record Added Successfully\"}";
    }
}
