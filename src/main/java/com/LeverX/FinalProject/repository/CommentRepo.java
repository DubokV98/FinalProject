package com.LeverX.FinalProject.repository;

import com.LeverX.FinalProject.entity.Comment;

import java.util.List;

public interface CommentRepo {
    void addComment(Comment comment);

    List<Comment> getAllCommentByTraderAndApprove(int userId);

    List<Comment> getAllCommentWhereApproveFalse();

    void updateComment(Comment comment);

    Comment findCommentById(int id);
}
