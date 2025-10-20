package com.techietester.model;

public class VideoGame {
    private int id;
    private String name;
    private String category;
    private int reviewScore;
    private String rating;

    public VideoGame() {}

    public VideoGame(int id, String name, String category, int reviewScore, String rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.reviewScore = reviewScore;
        this.rating = rating;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getReviewScore() { return reviewScore; }
    public void setReviewScore(int reviewScore) { this.reviewScore = reviewScore; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }
}
