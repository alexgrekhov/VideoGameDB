package com.techietester.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoGame {
    private int id;
    private String name;
    private String genre;
    private String platform;
    private double rating;
}
