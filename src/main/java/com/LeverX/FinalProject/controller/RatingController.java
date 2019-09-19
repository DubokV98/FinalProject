package com.LeverX.FinalProject.controller;

import com.LeverX.FinalProject.entity.User;
import com.LeverX.FinalProject.service.RatingService;
import com.LeverX.FinalProject.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/users/rating", method = RequestMethod.PUT)
    public ResponseEntity<String> updateRating(@RequestParam int user_id, @RequestParam double rating) throws JsonProcessingException {
        User user = userService.findUserById(user_id);

        ratingService.updateRating(user.getRating(), rating);
        String obj = objectMapper.writeValueAsString(user);
        return ResponseEntity.ok(obj);
    }

    @RequestMapping(value = "/users/rating", method = RequestMethod.GET)
    public ResponseEntity<String> getTopRating() throws JsonProcessingException {
        List<User> userList = ratingService.sortByRating();

        String obj = objectMapper.writeValueAsString(userList);
        return ResponseEntity.ok(obj);
    }
}
