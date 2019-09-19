package com.LeverX.FinalProject.repository;

import com.LeverX.FinalProject.entity.Rating;

import java.util.List;

public interface RatingRepo {
    void addRating(Rating rating);

    Rating findById(int id);

    void updateRating(Rating rating);

    List<Rating> getAllRating();
}
