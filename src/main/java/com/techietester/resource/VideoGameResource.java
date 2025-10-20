package com.techietester.resource;

import com.techietester.model.VideoGame;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/videogames")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Api(value = "Video Games")
public class VideoGameResource {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // Конструктор по умолчанию нужен для Jersey
    public VideoGameResource() {
    }

    @GET
    @ApiOperation(value = "Get List of all Video Games", notes = "Returns all the videos games in the DB", response = VideoGame.class, responseContainer = "List")
    public List<VideoGame> listVideoGames() {
        String sql = "select * from VIDEOGAME";
        return namedParameterJdbcTemplate.query(sql, new VideoGameMapper());
    }

    @GET
    @Path("/{videoGameId}")
    @ApiOperation(value = "Get a single video game by ID", notes = "Returns the details of a single game by ID", response = VideoGame.class)
    public VideoGame getVideoGame(@PathParam("videoGameId") Integer videoGameId) {
        String sql = "select * from VIDEOGAME where id=:videoGameId";
        SqlParameterSource params = new MapSqlParameterSource("videoGameId", videoGameId);
        return namedParameterJdbcTemplate.query(sql, params, new VideoGameMapper()).get(0);
    }

    @POST
    @ApiOperation(value = "Add a new video game", notes = "Add a new video game to the DB")
    public String createVideoGame(final VideoGame videoGame) {
        String sql = "insert into VIDEOGAME values(:id, :name, :releaseDate, :reviewScore, :category, :rating)";
        SqlParameterSource params = new BeanPropertySqlParameterSource(videoGame);
        namedParameterJdbcTemplate.update(sql, params);
        return "{\"status\": \"Record Added Successfully\"}";
    }

    // остальные методы оставляем без изменений…

    private static final class VideoGameMapper implements RowMapper<VideoGame> {
        public VideoGame mapRow(ResultSet rs, int rowNum) throws SQLException {
            VideoGame videoGame = new VideoGame();
            videoGame.setId(rs.getInt("id"));
            videoGame.setName(rs.getString("name"));
            videoGame.setReleaseDate(rs.getDate("released_on"));
            videoGame.setReviewScore(rs.getInt("review_score"));
            videoGame.setCategory(rs.getString("category"));
            videoGame.setRating(rs.getString("rating"));
            return videoGame;
        }
    }
}
