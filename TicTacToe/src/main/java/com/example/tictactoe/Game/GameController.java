package com.example.tictactoe.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/games")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/getAllHistory")
    public String GetGames() {
        return gameService.getGames();
    }

    @GetMapping("/getSingleHistory")
    public String GetGames(
            @RequestParam(name = "playerHistory") String player
    ) {
        return gameService.getGames(player);
    }

    @PostMapping("/addGame")
    public void addGame(@RequestBody Game game) {

        gameService.addNewGame(game);
//        gameService.addNewGame(new Game(LocalDateTime.now().toString(), "play1Test", "play2Test", "WinnerTest"));

    }

    @GetMapping("/getTotalWins")
    public String getTotalWins(
            @RequestParam(name="player") String player
    ) {
        return gameService.getTotalWins(player);
    }

}
