package com.LeverX.FinalProject.service;

import com.LeverX.FinalProject.entity.Comment;
import com.LeverX.FinalProject.entity.GameObject;
import com.LeverX.FinalProject.entity.User;
import com.LeverX.FinalProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private GameObjectRepo gameObjectRepo;
    @Autowired
    private CommentRepo commentRepo;

    @Override
    public Comment addCommentOnGameObjectAndTrader(int gameObjectId, int userId, Comment comment) {
        User user = userRepo.findUserById(userId);
        GameObject gameObject = gameObjectRepo.findGameObjectById(gameObjectId);

        comment.setCreated_at(LocalDate.now());
        comment.setApprove(false);
        comment.setUser(user);
        comment.setGameObject(gameObject);

        commentRepo.addComment(comment);
        return comment;
    }

    @Override
    public List<Comment> getAllCommentByTrader(int userId) {
        return commentRepo.getAllCommentByTraderAndApprove(userId);
    }

    @Override
    public List<Comment> getAllCommentToApprove() {
        return commentRepo.getAllCommentWhereApproveFalse();
    }

    @Override
    public Comment updateCommentApprove(int id, boolean approve) {
        Comment comment = commentRepo.findCommentById(id);
        comment.setApprove(approve);

        commentRepo.updateComment(comment);

        return comment;
    }
}
