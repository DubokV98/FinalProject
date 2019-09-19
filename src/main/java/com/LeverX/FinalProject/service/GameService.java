package com.LeverX.FinalProject.service;

import com.LeverX.FinalProject.entity.Game;

import java.util.List;

public interface GameService {
    Game addGame(String name, String style);

    Game updateGame(int id, String name);

    List<Game> getAllGame();

    List<Game> filterGame(String filter);
}
