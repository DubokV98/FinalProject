package com.LeverX.FinalProject.controller;

import com.LeverX.FinalProject.entity.Game;
import com.LeverX.FinalProject.entity.GameObject;
import com.LeverX.FinalProject.entity.User;
import com.LeverX.FinalProject.service.GameObjectService;
import com.LeverX.FinalProject.service.GameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TraderController {
    @Autowired
    private GameService gameService;
    @Autowired
    private GameObjectService gameObjectService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/object", method = RequestMethod.POST)
    public ResponseEntity<String> addGameObject(@Valid GameObject gameObject,
                                                @RequestParam int author_id,
                                                @RequestParam String name_Game,
                                                @RequestParam String style) throws JsonProcessingException {
        Game game = gameService.addGame(name_Game, style);

        gameObject = gameObjectService.addGameObject(gameObject, author_id, game);

        String currentGameObject = objectMapper.writeValueAsString(gameObject);
        return ResponseEntity.ok(currentGameObject);
    }

    @RequestMapping(value = "/object", method = RequestMethod.GET)
    public ResponseEntity<String> getAllObjectByTrader() throws JsonProcessingException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int authorIdInt = user.getId().intValue();

        List<GameObject> gameObjectsList = gameObjectService.getAllGameObjectByAuthorId(authorIdInt);

        String obj = objectMapper.writeValueAsString(gameObjectsList);
        return ResponseEntity.ok(obj);
    }

    @RequestMapping(value = "/object", method = RequestMethod.DELETE, params = "id")
    public String deleteTraderObject(@RequestParam int id) {
        gameObjectService.deleteGameObjectById(id);

        return "index";
    }

    @RequestMapping(value = "/object", method = RequestMethod.PUT)
    public ResponseEntity<String> editTradeObject(@RequestParam int id,
                                                  @RequestParam double price,
                                                  @RequestParam String title,
                                                  @RequestParam String text) throws JsonProcessingException {
        GameObject gameObject = gameObjectService.updateGameObject(id, title, text, price);

        String obj = objectMapper.writeValueAsString(gameObject);
        return ResponseEntity.ok(obj);
    }
}
