package com.LeverX.FinalProject.repository;

import com.LeverX.FinalProject.entity.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CommentRepoImp implements CommentRepo {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addComment(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(comment);
    }

    @Override
    public List<Comment> getAllCommentByTraderAndApprove(int userId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Comment C WHERE C.user.id = '" + userId + "'" +
                "and C.approve is 1").list();
    }

    @Override
    public List<Comment> getAllCommentWhereApproveFalse() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Comment C WHERE C.approve = 0").list();
    }

    @Override
    public void updateComment(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.update(comment);
    }

    @Override
    public Comment findCommentById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Comment comment = (Comment) session.createQuery("from Comment C WHERE C.id = '" + id + "'").getSingleResult();
        return comment;
    }
}
