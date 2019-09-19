package com.LeverX.FinalProject.controller;

import com.LeverX.FinalProject.entity.Game;
import com.LeverX.FinalProject.entity.User;
import com.LeverX.FinalProject.service.GameService;
import com.LeverX.FinalProject.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;

    @Autowired
    public void setGameRepo(GameService gameService) {
        this.gameService = gameService;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage(Model model) {
        List<User> userList = userService.getAllUser();

        model.addAttribute("users", userList);
        return "main";
    }

    @RequestMapping(value = "/gamefilter", method = RequestMethod.PUT)
    public ResponseEntity<String> sortByGame(@RequestParam String filter) throws JsonProcessingException {
        List<Game> gameList = gameService.filterGame(filter);

        String obj = objectMapper.writeValueAsString(gameList);
        return ResponseEntity.ok(obj);
    }

}
