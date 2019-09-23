package com.LeverX.FinalProject.service;

import com.LeverX.FinalProject.entity.Game;

import java.util.List;

public interface GameService {
    Game addGame(Game game);

    Game updateGame(int id, String name, String style);

    List<Game> getAllGame();

    List<Game> filterGame(String filter);
}
