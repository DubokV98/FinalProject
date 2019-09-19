package com.LeverX.FinalProject.repository;

import com.LeverX.FinalProject.entity.Game;

import java.util.List;

public interface GameRepo {
    List<Game> allGame();

    void addGame(Game game);

    void deleteGame(Game game);

    void editGame(Game game);

    List<Game> getAllGame();

    Game findGameByName(String name);

    Game findGameById(int id);

    List<Game> filterGameByStyle(String filter);
}
