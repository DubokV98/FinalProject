package com.LeverX.FinalProject.controller;

import com.LeverX.FinalProject.entity.Comment;
import com.LeverX.FinalProject.entity.GameObject;
import com.LeverX.FinalProject.service.CommentService;
import com.LeverX.FinalProject.service.GameObjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private GameObjectService gameObjectService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/approve/comment", method = RequestMethod.GET)
    public ResponseEntity<String> getAllCommentToApprove() throws JsonProcessingException {
        List<Comment> commentList = commentService.getAllCommentToApprove();

        String obj = objectMapper.writeValueAsString(commentList);
        return ResponseEntity.ok(obj);
    }

    @RequestMapping(value = "/approve/gameobject", method = RequestMethod.GET)
    public ResponseEntity<String> getAllGameObjectWhereStatusExpectation() throws JsonProcessingException {
        List<GameObject> gameObjectList = gameObjectService.getAllGameObjectWhereStatusExpectation();

        String obj = objectMapper.writeValueAsString(gameObjectList);
        return ResponseEntity.ok(obj);
    }

    @RequestMapping(value = "/approve/gameobject", method = RequestMethod.PUT)
    public ResponseEntity<String> editStatusOfGameObject(@RequestParam int id, @RequestParam String status) throws JsonProcessingException {
        GameObject gameObject = gameObjectService.updateGameObjectStatus(id, status);

        String obj = objectMapper.writeValueAsString(gameObject);
        return ResponseEntity.ok(obj);
    }

    @RequestMapping(value = "/approve/comment", method = RequestMethod.PUT)
    public ResponseEntity<String> editApproveOfComment(@RequestParam int id, @RequestParam boolean approve) throws JsonProcessingException {
        Comment comment = commentService.updateCommentApprove(id, approve);

        String obj = objectMapper.writeValueAsString(comment);
        return ResponseEntity.ok(obj);
    }
}
