package br.pucrs.thomaz.ChessBrawl.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that represents the tournament
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
@Getter
@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> players = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();

    @OneToOne
    private Player champion;

    private boolean finished = false;

    protected Tournament() {
        // JPA needs no-args constructor
    }

    /**
     * Constructor for Tournament
     * @param players is the list of players
     */
    private Tournament(List<Player> players) {
        validatePlayerCount(players);
        this.players.addAll(players);
    }

    /**
     * Method to create a new tournament
     * @param players is the list of players
     * @return a new Tournament object
     */
    public static Tournament create(List<Player> players) {
        return new Tournament(players);
    }

    /**
     * Method to validate the number of players in the tournament
     * @param players is the list of players
     * @throws IllegalArgumentException if the number of players is less than 4, more than 8, or odd
     */
    private void validatePlayerCount(List<Player> players) {
        if (players == null || players.size() < 4 || players.size() > 8 || players.size() % 2 != 0) {
            throw new IllegalArgumentException("Tournament must have 4 to 8 players, and the number must be even.");
        }
    }

    /**
     * Method to start the first round of the tournament
     */
    public void startFirstRound() {
        createNewRound(this.players, 1);
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    

    /**
     * Method to go to the next round of the tournament
     * @throws IllegalStateException if the current round is not finished
     */
    public void proceedToNextRound() {
        Round currentRound = this.getCurrentRound();
    
        if (!currentRound.isFinished()) {
            throw new IllegalStateException("Current round not finished");
        }
    
        List<Player> winners = currentRound.getWinners();
        if (winners.size() < 2) {
            this.setFinished(true);
            return;
        }
    
        Collections.shuffle(winners);
    
        List<Battle> battles = new ArrayList<>();
        for (int i = 0; i < winners.size(); i += 2) {
            Player p1 = winners.get(i);
            Player p2 = winners.get(i + 1);
            battles.add(Battle.create(p1, p2));
        }
    
        Round nextRound = Round.create(this.getRounds().size() + 1, battles);
        this.getRounds().add(nextRound);
    }
    

    /**
     * Method to create a new round
     * @param playersForRound is the list of players for the round
     * @param roundNumber is the round number
     */
    private void createNewRound(List<Player> playersForRound, int roundNumber) {
        Collections.shuffle(playersForRound);

        List<Battle> battles = new ArrayList<>();
        for (int i = 0; i < playersForRound.size(); i += 2) {
            battles.add(Battle.create(playersForRound.get(i), playersForRound.get(i + 1)));
        }

        Round round = Round.create(roundNumber, battles);
        this.rounds.add(round);
    }

    /**
     * Method to get the current round of the tournament
     * @throws IllegalStateException if the tournament has not started
     * @return the current round
     */
    public Round getCurrentRound() {
        if (rounds.isEmpty()) {
            throw new IllegalStateException("Tournament has not started.");
        }
        return rounds.get(rounds.size() - 1);
    }
}
