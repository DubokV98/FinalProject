package com.LeverX.FinalProject.service;

import com.LeverX.FinalProject.entity.Rating;
import com.LeverX.FinalProject.entity.User;
import com.LeverX.FinalProject.repository.RatingRepo;
import com.LeverX.FinalProject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class RatingServiceImp implements RatingService {
    private Comparator<User> sortByAvgRating = (User u1, User u2) -> {
        if (u2.getRating().getAvg_rating() > u1.getRating().getAvg_rating()) {
            return 1;
        }

        if (u2.getRating().getAvg_rating() < u1.getRating().getAvg_rating()) {
            return -1;
        }

        return 0;
    };

    @Autowired
    private RatingRepo ratingRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public Rating addRating() {
        Rating rating = new Rating();
        ratingRepo.addRating(rating);
        return rating;
    }

    @Override
    public void updateRating(Rating rating, double ratingMark) {
        int ratingCol = rating.getRatingcol();
        double ratingTableSum = rating.getRating();
        ratingCol++;
        ratingTableSum = ratingTableSum + ratingMark;

        rating.setRatingcol(ratingCol);
        rating.setRating(ratingTableSum);
        double avgRating = (ratingTableSum / ratingCol);
        rating.setAvg_rating(avgRating);

        ratingRepo.updateRating(rating);
    }

    @Override
    public List<User> sortByRating() {
        List<User> userList = userRepo.getAllTrader();
        userList.sort(sortByAvgRating);

        return userList;
    }
}
