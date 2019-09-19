package com.LeverX.FinalProject.controller;

import com.LeverX.FinalProject.entity.Comment;
import com.LeverX.FinalProject.service.CommentService;
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
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/articles/comments", method = RequestMethod.POST)
    public ResponseEntity<String> addCommentOnGameObjectAndTrader(@RequestParam int game_object_id,
                                                                  @RequestParam int user_id,
                                                                  @RequestParam String commentator_name,
                                                                  @RequestParam String message) throws JsonProcessingException {
        Comment comment = commentService.addCommentOnGameObjectAndTrader(game_object_id, user_id, commentator_name, message);

        String obj = objectMapper.writeValueAsString(comment);
        return ResponseEntity.ok(obj);
    }

    @RequestMapping(value = "/users/comments", method = RequestMethod.GET)
    public ResponseEntity<String> getAllComment(@RequestParam int user_id) throws JsonProcessingException {
        List<Comment> commentList = commentService.getAllCommentByTrader(user_id);

        String obj = objectMapper.writeValueAsString(commentList);
        return ResponseEntity.ok(obj);
    }
}
