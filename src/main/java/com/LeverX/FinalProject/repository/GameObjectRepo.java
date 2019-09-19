package com.LeverX.FinalProject.repository;

import com.LeverX.FinalProject.entity.GameObject;

import java.util.List;

public interface GameObjectRepo {
    List<GameObject> getAllGameObject();

    void addGameObject(GameObject gameObject);

    List<GameObject> getAllGameObjectByAuthorId(int author_id);

    void deleteGameObject(GameObject gameObject);

    GameObject findGameObjectById(int id);

    GameObject editGameObject(GameObject gameObject);

    List<GameObject> getAllGameObjectWhereStatusExpectation();
}
