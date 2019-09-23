package com.LeverX.FinalProject.controller;

import com.LeverX.FinalProject.entity.Comment;
import com.LeverX.FinalProject.service.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/articles/comments", method = RequestMethod.POST)
    public ResponseEntity<String> addCommentOnGameObjectAndTrader(@Valid Comment comment,
                                                                  @RequestParam int gameObjectId,
                                                                  @RequestParam int userId,
                                                                  BindingResult bindingResult)
                                                                  throws JsonProcessingException {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            String message = objectMapper.writeValueAsString(errors);
            return ResponseEntity.ok(message);
        }

        Comment currentComment = commentService.addCommentOnGameObjectAndTrader(gameObjectId, userId,comment);

        String obj = objectMapper.writeValueAsString(currentComment);
        return ResponseEntity.ok(obj);
    }

    @RequestMapping(value = "/users/comments", method = RequestMethod.GET)
    public ResponseEntity<String> getAllComment(@RequestParam int user_id) throws JsonProcessingException {
        List<Comment> commentList = commentService.getAllCommentByTrader(user_id);

        String obj = objectMapper.writeValueAsString(commentList);
        return ResponseEntity.ok(obj);
    }
}
