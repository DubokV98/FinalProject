package com.LeverX.FinalProject.service;

import com.LeverX.FinalProject.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment addCommentOnGameObjectAndTrader(int gameObject, int userId, String commentatorName, String message);

    List<Comment> getAllCommentByTrader(int userId);

    List<Comment> getAllCommentToApprove();

    Comment updateCommentApprove(int id, boolean approve);
}
