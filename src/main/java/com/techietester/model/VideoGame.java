package com.techietester.model;

import lombok.Data;

import java.util.Date;

@Data
public class VideoGame {
    private int id;
    private String name;
    private Date releaseDate;
    private int reviewScore;
    private String category;
    private String rating;
}
