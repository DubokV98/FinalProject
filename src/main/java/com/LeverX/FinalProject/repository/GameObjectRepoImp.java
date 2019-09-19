package com.LeverX.FinalProject.repository;

import com.LeverX.FinalProject.entity.GameObject;
import com.LeverX.FinalProject.entity.GameObjectStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameObjectRepoImp implements GameObjectRepo {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addGameObject(GameObject gameObject) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(gameObject);
    }

    @Override
    public List<GameObject> getAllGameObjectByAuthorId(int author_id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM GameObject GO WHERE GO.user.id = '" + author_id + "'").list();
    }

    @Override
    public List<GameObject> getAllGameObject() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM GameObject").list();
    }

    @Override
    public void deleteGameObject(GameObject gameObject) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(gameObject);
    }

    @Override
    public GameObject findGameObjectById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (GameObject) session.createQuery("FROM GameObject GO WHERE GO.id = '" + id + "'").getSingleResult();
    }

    @Override
    public GameObject editGameObject(GameObject gameObject) {
        Session session = sessionFactory.getCurrentSession();
        session.update(gameObject);
        return gameObject;
    }

    @Override
    public List<GameObject> getAllGameObjectWhereStatusExpectation() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM GameObject GO WHERE GO.status ='" + GameObjectStatus.EXPECTATION + "'").list();
    }
}