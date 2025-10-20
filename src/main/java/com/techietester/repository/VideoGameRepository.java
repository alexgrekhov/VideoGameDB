package com.techietester.repository;

import com.techietester.model.VideoGame;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VideoGameRepository {

    private final List<VideoGame> games = new ArrayList<>();

    public List<VideoGame> findAll() {
        return games;
    }

    public VideoGame findById(int id) {
        Optional<VideoGame> game = games.stream().filter(g -> g.getId() == id).findFirst();
        return game.orElse(null);
    }

    public void save(VideoGame game) {
        games.add(game);
    }

    public void update(int id, VideoGame updated) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId() == id) {
                games.set(i, updated);
                return;
            }
        }
    }

    public void delete(int id) {
        games.removeIf(g -> g.getId() == id);
    }
}
