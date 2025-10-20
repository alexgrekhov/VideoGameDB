package com.techietester.resource;

import com.techietester.model.VideoGame;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videogames")
@Tag(name = "Video Games", description = "API for Video Game Database")
public class VideoGameController {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @GetMapping
    @Operation(summary = "Get list of all video games", description = "Returns all video games in the database")
    public List<VideoGame> getAllVideoGames() {
        return jdbcTemplate.query("SELECT * FROM VIDEOGAME",
                BeanPropertyRowMapper.newInstance(VideoGame.class));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a single video game by ID", description = "Returns the details of a single game by ID")
    public VideoGame getVideoGameById(@PathVariable("id") Integer id) {
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(
                "SELECT * FROM VIDEOGAME WHERE id=:id",
                params,
                BeanPropertyRowMapper.newInstance(VideoGame.class)
        );
    }

    @PostMapping
    @Operation(summary = "Add a new video game", description = "Add a new video game to the database")
    public String createVideoGame(@RequestBody VideoGame videoGame) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(videoGame);
        jdbcTemplate.update(
                "INSERT INTO VIDEOGAME (id, name, released_on, review_score, category, rating) " +
                        "VALUES (:id, :name, :releaseDate, :reviewScore, :category, :rating)",
                params
        );
        return "{\"status\": \"Record Added Successfully\"}";
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a video game", description = "Update an existing video game by ID")
    public VideoGame updateVideoGame(@PathVariable("id") Integer id, @RequestBody VideoGame videoGame) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(videoGame);
        jdbcTemplate.update(
                "UPDATE VIDEOGAME SET name=:name, released_on=:releaseDate, review_score=:reviewScore, " +
                        "category=:category, rating=:rating WHERE id=:id",
                params
        );

        SqlParameterSource queryParams = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(
                "SELECT * FROM VIDEOGAME WHERE id=:id",
                queryParams,
                BeanPropertyRowMapper.newInstance(VideoGame.class)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a video game", description = "Deletes a video game from the database by ID")
    public String deleteVideoGame(@PathVariable("id") Integer id) {
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        jdbcTemplate.update("DELETE FROM VIDEOGAME WHERE id=:id", params);
        return "{\"status\": \"Record Deleted Successfully\"}";
    }
}
