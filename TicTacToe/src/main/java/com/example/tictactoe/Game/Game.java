package com.example.tictactoe.Game;

import jakarta.persistence.*;

@Entity
@Table(name = "game_data")
public class Game {
    @Id
    private String gameId;
    private String pl1;
    private String pl2;
    private String gameWinner;

    public Game() {

    }

    @Override
    public String toString() {
        return ("<br><br>Played At:" + gameId + "<br>Player 1: " + pl1 + "<br>Player 2: " + pl2 + "<br>Winner: " + gameWinner);
    }

    public String toStringWinner() {
        return ("<br><br>Played At:" + gameId + "<br>Player 1: " + pl1 + "<br>Player 2: " + pl2 + "<br>" + gameWinner + " Won!");
    }

    public String toStringDraw() {
        return ("<br><br>Played At:" + gameId + "<br>Player 1: " + pl1 + "<br>Player 2: " + pl2 + "<br>Match Draw");
    }



    public Game(String gameId, String pl1, String pl2, String gameWinner) {
        this.gameId = gameId;
        this.pl1 = pl1;
        this.pl2 = pl2;
        this.gameWinner = gameWinner;
    }

    public Game(String pl1, String pl2, String gameWinner) {
        this.pl1 = pl1;
        this.pl2 = pl2;
        this.gameWinner = gameWinner;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPl1() {
        return pl1;
    }

    public void setPl1(String pl1) {
        this.pl1 = pl1;
    }

    public String getPl2() {
        return pl2;
    }

    public void setPl2(String pl2) {
        this.pl2 = pl2;
    }

    public String getGameWinner() {
        return gameWinner;
    }

    public void setGameWinner(String gameWinner) {
        this.gameWinner = gameWinner;
    }
}
