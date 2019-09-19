package com.LeverX.FinalProject.repository;

import com.LeverX.FinalProject.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepoImp implements UserRepo {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> allUser() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User ").list();
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(user);
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public void edit(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public User findUserByEmailFromSecure(String email) {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.createQuery("FROM User U WHERE U.email = '" + email + "'").getSingleResult();
    }

    @Override
    public User findUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("FROM User U WHERE U.id = '" + id + "'").getSingleResult();
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        User user;
        List<User> userList = session.createQuery("FROM User U WHERE U.email = '" + email + "'").list();
        if (!userList.isEmpty()) {
            user = userList.get(0);
        } else {
            user = null;
        }
        return user;
    }

    @Override
    public User finByActivationCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("FROM User U WHERE U.activationCode = '" + code + "'").getSingleResult();
        return user;
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public List<User> getAllTrader() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM User U WHERE U.rating != '" + null + "'").list();
    }
}
