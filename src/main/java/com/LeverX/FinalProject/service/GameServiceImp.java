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
    public Game addGame(String name, String style) {
        Game game = gameRepo.findGameByName(name);

        if (game == null) {
            game = new Game(name, style);
            gameRepo.addGame(game);
            game = gameRepo.findGameByName(name);
        }

        return game;
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
    public Game updateGame(int id, String name) {
        try {
            Game game = gameRepo.findGameById(id);
            game.setName(name);
            gameRepo.editGame(game);
            return game;
        } catch (NoResultException ex) {
            return null;
        }
    }
}
