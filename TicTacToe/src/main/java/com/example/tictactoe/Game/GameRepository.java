package com.example.tictactoe.Game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game,String> {
    Optional<Game> findGameByGameId(String gameId);

    @Query(value = "SELECT game_winner, COUNT(*) AS name_count FROM game_data WHERE game_winner = :winnerName GROUP BY game_winner", nativeQuery = true)
    List<Object[]> getTotalWins(@RequestParam("personName") String winnerName);

}
