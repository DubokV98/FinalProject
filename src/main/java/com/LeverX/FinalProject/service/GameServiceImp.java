package com.LeverX.FinalProject.service;

import com.LeverX.FinalProject.entity.Game;
import com.LeverX.FinalProject.repository.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@Transactional
public class GameServiceImp implements GameService {
    @Autowired
    private GameRepo gameRepo;

    @Override
    public Game addGame(Game game) {
        Game currentGame = gameRepo.findGameByName(game.getName());

        if (currentGame == null) {
            gameRepo.addGame(game);
            currentGame = gameRepo.findGameByName(game.getName());
        }

        return currentGame;
    }

    @Override
    public List<Game> getAllGame() {
        return gameRepo.getAllGame();
    }

    @Override
    public List<Game> filterGame(String filter) {
        try {
            return gameRepo.filterGameByStyle(filter);
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public Game updateGame(int id, String name, String style) {
        try {
            Game game = gameRepo.findGameById(id);
            if (style.length() != 0) {
                game.setStyle(style);
            }
            game.setName(name);
            gameRepo.editGame(game);
            return game;
        } catch (NoResultException ex) {
            return null;
        }
    }
}
