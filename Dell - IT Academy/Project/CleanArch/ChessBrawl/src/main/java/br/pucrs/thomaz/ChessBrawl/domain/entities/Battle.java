package br.pucrs.thomaz.ChessBrawl.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

/**
 * Class that represents a battle between two players
 * It contains these players, their scores and the winner
 * It contains methods to add scores, apply a blitz match and finalize the battle as well
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
@Getter
@Entity
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @ManyToOne(optional = false)
    private Player playerA;

    @ManyToOne(optional = false)
    private Player playerB;

    @ManyToOne
    private Player winner;

    private boolean blitzMatchUsed = false;
    private boolean finished = false;

    private int playerAScore;
    private int playerBScore;

    /**
     * Default constructor for JPA
     */
    protected Battle() {
        // JPA needs a no-args constructor
    }

    /**
     * The constructor for the Battle entity
     * @param playerA is the first player
     * @param playerB is the second player
     * @throws IllegalArgumentException if the players are null or the same
     */
    private Battle(Player playerA, Player playerB) {
        if (playerA == null || playerB == null || playerA.equals(playerB)) {
            throw new IllegalArgumentException("A battle must be between two distinct players.");
        }
        this.playerA = playerA;
        this.playerB = playerB;
        this.playerAScore = 0;
        this.playerBScore = 0;
    }
    /**
     * Method to create a new battle
     * @param playerA is the first player
     * @param playerB is the second player
     * @return a new Battle object
     */
    public static Battle create(Player playerA, Player playerB) {
        return new Battle(playerA, playerB);
    }

    /**
     * Method to add a specific score to a determined player
     * @param player the player that will get the score
     * @param points that will be added to the player score
     * @throws IllegalStateException if the battle is already finished
     * @throws IllegalArgumentException if the player does not belong to this battle
     */
    public void addScore(Player player, int points) {
        if (finished) throw new IllegalStateException("Battle is already finished.");
        if (player.equals(playerA)) {
            this.playerAScore += points;
        } else if (player.equals(playerB)) {
            this.playerBScore += points;
        } else {
            throw new IllegalArgumentException("Player does not belong to this battle.");
        }
    }

    /**
     * Method used to apply a blitz match in case of a tie
     * The blitz match randomly gives 2 points to one of the players, to decide the winner
     * @throws IllegalStateException if the battle is already finished or the blitz match was already used
     */
    public void applyBlitzMatch() {
        if (finished || winner != null || blitzMatchUsed) {
            throw new IllegalStateException("Blitz Match cannot be applied.");
        }

        int bonus = 2;
        if (Math.random() < 0.5) {
            this.playerAScore += bonus;
        } else {
            this.playerBScore += bonus;
        }
        this.blitzMatchUsed = true;
    }

    /**
     * Method to end the battle and determine the winner
     * @throws IllegalStateException if the battle is already finished
     */
    public void finalizeBattle() {
        if (finished) throw new IllegalStateException("Battle already finalized.");
    
        if (playerAScore == playerBScore) {
            applyBlitzMatch();
        }
    
        if (playerAScore > playerBScore) {
            this.winner = playerA;
        } else {
            this.winner = playerB;
        }
    
        // O vencedor ganha 30 pontos
        this.winner.addTournamentPoints(30);
        this.finished = true;
    }
    
}
