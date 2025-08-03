package br.pucrs.thomaz.ChessBrawl.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that represents a round in the game
 * 
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
@Getter
@Entity
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private int roundNumber;

    private boolean finished = false;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Battle> battles = new ArrayList<>();

    protected Round() {
        // JPA needs no-args constructor
    }

    /**
     * Constructor for Round
     * 
     * @param roundNumber is the round number
     * @param battles     is the list of battles
     * @throws IllegalArgumentException if the battles list is null or empty
     */
    private Round(int roundNumber, List<Battle> battles) {
        if (battles == null || battles.isEmpty()) {
            throw new IllegalArgumentException("A round must have at least one battle.");
        }
        this.roundNumber = roundNumber;
        this.battles = battles;
    }

    /**
     * Method to create a new round
     * 
     * @param roundNumber is the round number
     * @param battles     is the list of battles
     * @return a new Round object
     */
    public static Round create(int roundNumber, List<Battle> battles) {
        return new Round(roundNumber, battles);
    }

    /**
     * Method to check if the battles in the round are finished
     * 
     * @return true if all battles are finished, false otherwise
     */
    public boolean isFinished() {
        return battles.stream().allMatch(Battle::isFinished);
    }

    /**
     * Method to mark the round as finished if all battles are finished
     */
    public void markAsFinishedIfApplicable() {
        if (isFinished()) {
            this.finished = true;
        }
    }

    /**
     * Method the get the list of winning players for each battle in the round
     * 
     * @return a list of winning players
     * @throws IllegalStateException if the round is not finished
     */
    public List<Player> getWinners() {
        return battles.stream()
                .filter(Battle::isFinished)
                .map(Battle::getWinner)
                .collect(Collectors.toList());
    }

}
