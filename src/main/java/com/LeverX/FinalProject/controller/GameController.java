package com.LeverX.FinalProject.controller;

import com.LeverX.FinalProject.entity.Game;
import com.LeverX.FinalProject.repository.GameRepo;
import com.LeverX.FinalProject.service.GameService;
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
public class GameController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public ResponseEntity<String> getAllGame() throws JsonProcessingException {
        List<Game> gameList = gameService.getAllGame();

        String obj = objectMapper.writeValueAsString(gameList);
        return ResponseEntity.ok(obj);
    }

    @RequestMapping(value = "/games", method = RequestMethod.POST)
    public ResponseEntity<String> addGame(@RequestParam String name, @RequestParam String style) throws JsonProcessingException {
        Game game = gameService.addGame(name, style);

        String obj = objectMapper.writeValueAsString(game);
        return ResponseEntity.ok(obj);
    }

    @RequestMapping(value = "/games", method = RequestMethod.PUT)
    public ResponseEntity<String> updateGame(@RequestParam int id,
                                             @RequestParam String name) throws JsonProcessingException {
        Game game = gameService.updateGame(id, name);

        String obj = objectMapper.writeValueAsString(game);
        return ResponseEntity.ok(obj);
    }
}
