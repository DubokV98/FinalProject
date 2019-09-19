package com.LeverX.FinalProject.service;

import com.LeverX.FinalProject.entity.Game;
import com.LeverX.FinalProject.entity.GameObject;

import java.util.List;

public interface GameObjectService {
    GameObject addGameObject(GameObject gameObject, int author_id, Game game);

    List<GameObject> getAllGameObjectByAuthorId(int author_id);

    List<GameObject> getAllGameObject();

    void deleteGameObjectById(int id);

    GameObject updateGameObjectStatus(int id, String status);

    GameObject updateGameObject(int id, String title, String text, double price);

    List<GameObject> getAllGameObjectWhereStatusExpectation();
}
