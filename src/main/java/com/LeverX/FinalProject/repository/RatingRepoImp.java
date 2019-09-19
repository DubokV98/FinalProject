package com.LeverX.FinalProject.repository;

import com.LeverX.FinalProject.entity.Rating;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RatingRepoImp implements RatingRepo {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRating(Rating rating) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(rating);
    }

    @Override
    public Rating findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Rating rating = (Rating) session.createQuery("FROM Rating rating WHERE rating.id = '" + id + "' ").getSingleResult();
        return rating;
    }

    @Override
    public void updateRating(Rating rating) {
        Session session = sessionFactory.getCurrentSession();
        session.update(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Rating").list();
    }
}
