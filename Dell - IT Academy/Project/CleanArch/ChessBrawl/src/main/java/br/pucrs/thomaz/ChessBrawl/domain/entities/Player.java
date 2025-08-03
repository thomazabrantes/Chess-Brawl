package br.pucrs.thomaz.ChessBrawl.domain.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

/**
 * Class that represents a player in the game
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
@Getter
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String name;
    private String nickname;

    private int rankingPoints;
    private int tournamentPoints;

    private int originalMove;
    private int mistake;
    private int advantageousPosition;
    private int disrespectToOpponent;
    private int furyAttack;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();


    /**
     * Default constructor for JPA
     */
    protected Player() {
        // JPA needs a no-args constructor
    }

    /**
     * Constructor for Player
     * @param name is the player's name
     * @param nickname is the player's nickname
     * @param rankingPoints is the player's ranking points
     */
    private Player(String name, String nickname, int rankingPoints) {
        this.name = name;
        this.nickname = nickname;
        this.rankingPoints = rankingPoints;
        this.tournamentPoints = 70;
        this.originalMove = 0;
        this.mistake = 0;
        this.advantageousPosition = 0;
        this.disrespectToOpponent = 0;
        this.furyAttack = 0;
    }

    /**
     * Method to create a new player
     * @param name is the player's name
     * @param nickname is the player's nickname
     * @param rankingPoints is the player's ranking points
     * @throws IllegalArgumentException if the name or nickname is empty or if the ranking points are invalid
     * @return a new Player object
     */
    public static Player create(String name, String nickname, int rankingPoints) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be empty!");
        }
        if (nickname == null || nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("Player nickname cannot be empty!");
        }
        if (rankingPoints < 1 || rankingPoints > 15000) {
            throw new IllegalArgumentException("Ranking points must be between 1 and 15000!");
        }
        return new Player(name, nickname, rankingPoints);
    }

    // Getters and Setters
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be empty!");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNickname(String nickname) {
        if (nickname == null || nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("Player nickname cannot be empty!");
        }
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setRankingPoints(int rankingPoints) {
        if (rankingPoints < 1 || rankingPoints > 15000) {
            throw new IllegalArgumentException("Ranking points must be between 1 and 15000!");
        }
        this.rankingPoints = rankingPoints;
    }

    public int getRankingPoints() {
        return rankingPoints;
    }

    public void setTournamentPoints(int tournamentPoints) {
        if (tournamentPoints < 0) {
            throw new IllegalArgumentException("Tournament points must be non-negative.");
        }
        this.tournamentPoints = tournamentPoints;
    }

    public int getTournamentPoints() {
        return tournamentPoints;
    }

    public void setOriginalMove(int originalMove) {
        this.originalMove = originalMove;
    }

    public int getOriginalMove() {
        return originalMove;
    }

    public void setMistake(int mistake) {
        this.mistake = mistake;
    }

    public int getMistake() {
        return mistake;
    }

    public void setAdvantageousPosition(int advantageousPosition) {
        this.advantageousPosition = advantageousPosition;
    }

    public int getAdvantageousPosition() {
        return advantageousPosition;
    }

    public void setDisrespectToOpponent(int disrespectToOpponent) {
        this.disrespectToOpponent = disrespectToOpponent;
    }

    public int getDisrespectToOpponent() {
        return disrespectToOpponent;
    }

    public void setFuryAttack(int furyAttack) {
        this.furyAttack = furyAttack;
    }

    public int getFuryAttack() {
        return furyAttack;
    }

    /**
     * Method to add tournament points to the player
     * @param points is the number of points to be added
     * @throws IllegalArgumentException if the points are negative
     */
    public void addTournamentPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Tournament points must be non-negative.");
        }
        this.tournamentPoints += points;
    }

    public void addEvent(Event event) {
        event.setPlayer(this); 
        this.events.add(event);
    }

    public void applyEventEffect(EventType type) {
        switch (type) {
            case ORIGINAL_MOVE -> this.originalMove++;
            case MISTAKE -> this.mistake++;
            case ADVANTAGEOUS_POSITION -> this.advantageousPosition++;
            case DISRESPECT_TO_OPPONENT -> this.disrespectToOpponent++;
            case FURY_ATTACK -> this.furyAttack++;
        }
    }
    
}