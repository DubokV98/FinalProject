package com.LeverX.FinalProject.repository;

import com.LeverX.FinalProject.entity.Game;
import com.LeverX.FinalProject.entity.GameObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class GameRepoImp implements GameRepo {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Game> allGame() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Game ").list();
    }

    @Override
    public void addGame(Game game) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(game);
    }

    @Override
    public void deleteGame(Game game) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(game);
    }

    @Override
    public void editGame(Game game) {
        Session session = sessionFactory.getCurrentSession();
        session.update(game);
    }

    @Override
    public Game findGameByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Game game;
        List<Game> gameList = session.createQuery("FROM Game G WHERE G.name = '" + name + "'").list();
        if (!gameList.isEmpty()) {
            game = gameList.get(0);
        } else {
            game = null;
        }
        return game;
    }

    @Override
    public List<Game> getAllGame() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Game").list();
    }

    @Override
    public Game findGameById(int id) throws NoResultException {
        Session session = sessionFactory.getCurrentSession();
        Game game = (Game) session.createQuery("FROM Game G WHERE G.id = '" + id + "'").getSingleResult();
        return game;
    }

    @Override
    public List<Game> filterGameByStyle(String filter) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Game G WHERE G.style = '" + filter + "'").list();
    }
}
