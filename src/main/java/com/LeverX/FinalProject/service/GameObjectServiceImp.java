package com.LeverX.FinalProject.service;

import com.LeverX.FinalProject.entity.Game;
import com.LeverX.FinalProject.entity.GameObject;
import com.LeverX.FinalProject.entity.GameObjectStatus;
import com.LeverX.FinalProject.entity.User;
import com.LeverX.FinalProject.repository.GameObjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class GameObjectServiceImp implements GameObjectService {
    @Autowired
    private GameObjectRepo gameObjectRepo;
    @Autowired
    private UserService userService;

    @Override
    public GameObject addGameObject(GameObject gameObject, int author_id, Game game) {
        LocalDate created_at = LocalDate.now();
        User user = userService.findUserById(author_id);

        gameObject.setCreated_at(created_at);
        gameObject.setUpdated_at(created_at);
        gameObject.setStatus(GameObjectStatus.EXPECTATION);
        gameObject.setUser(user);
        gameObject.setGame(game);

        gameObjectRepo.addGameObject(gameObject);
        return gameObject;
    }

    @Override
    public List<GameObject> getAllGameObjectByAuthorId(int author_id) {
        return gameObjectRepo.getAllGameObjectByAuthorId(author_id);
    }

    @Override
    public List<GameObject> getAllGameObject() {
        return gameObjectRepo.getAllGameObject();
    }

    @Override
    public void deleteGameObjectById(int id) {
        GameObject gameObject = gameObjectRepo.findGameObjectById(id);
        gameObjectRepo.deleteGameObject(gameObject);
    }

    @Override
    public GameObject updateGameObjectStatus(int id, String status) {
        GameObject gameObject = gameObjectRepo.findGameObjectById(id);

        if (GameObjectStatus.DECLINE.equals(status)) {
            gameObject.setStatus(GameObjectStatus.DECLINE);
        } else {
            gameObject.setStatus(GameObjectStatus.APPROVE);
        }

        return gameObjectRepo.editGameObject(gameObject);
    }

    @Override
    public GameObject updateGameObject(int id, String title, String text, double price) {
        GameObject gameObject = gameObjectRepo.findGameObjectById(id);
        LocalDate updated_at = LocalDate.now();
        gameObject.setTitle(title);
        gameObject.setText(text);
        gameObject.setPrice(price);
        gameObject.setUpdated_at(updated_at);

        return gameObjectRepo.editGameObject(gameObject);
    }

    @Override
    public List<GameObject> getAllGameObjectWhereStatusExpectation() {
        return gameObjectRepo.getAllGameObjectWhereStatusExpectation();
    }
}
