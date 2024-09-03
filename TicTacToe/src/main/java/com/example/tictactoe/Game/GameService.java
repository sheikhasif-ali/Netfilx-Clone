package com.example.tictactoe.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public String getGames() {
        String json = "";
        for (Game loop : gameRepository.findAll()) {
            System.out.println(loop.toString());;
            if (loop.toString().contains("Draw")) {
                json = "<div class='card'><p class='card-content'>" + loop.toStringDraw() + "</p></div>" + json;

            } else {
                json = "<div class='card'><p class='card-content'>" + loop.toStringWinner() + "</p></div>" + json;

            }

        }
        return json;
    }

    public String getGames(String player) {
        String json = "";
        for (Game loop : gameRepository.findAll()) {
            if (loop.getGameWinner().equals(player)) {
                json = "<div class='card'><p class='card-content'>" + loop.toString() + "</p></div>" + json;

            }
        }
        return json;
    }

    public void addNewGame(Game game) {
        Optional<Game> gameOptional = gameRepository
                .findGameByGameId(game.getGameId());
        if (gameOptional.isPresent()) {
            throw new IllegalStateException("game exists");
        }
        gameRepository.save(game);
    }

    public String getTotalWins(String playerName) {

        List<Object[]> list = gameRepository.getTotalWins(playerName);
//        return Arrays.toString(list.get(0)[0]);
        return list.get(0)[0] + " Won " + list.get(0)[1] + " Times";
    }
}
