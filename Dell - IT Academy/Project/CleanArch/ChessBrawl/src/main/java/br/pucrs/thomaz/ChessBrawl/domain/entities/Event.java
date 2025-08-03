package br.pucrs.thomaz.ChessBrawl.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * Class that represents an event in the game
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
@Getter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Enumerated(EnumType.STRING)
    private EventType type;

    private int points;

    @ManyToOne(optional = false)
    private Player player;

    @ManyToOne(optional = false)
    private Battle battle;

    /**
     * Default constructor for JPA
     */
    protected Event() {
        // JPA needs a no-args constructor
    }

    /**
     * The constructor for the Event entity
     * @param type is one of the types of events
     * @param player is the player who caused the event
     * @param battle is the battle in which the event happened
     * @throws IllegalArgumentException if the type, the player or the battle are null
     */
    private Event(EventType type, Player player, Battle battle) {
        if (type == null || player == null || battle == null) {
            throw new IllegalArgumentException("Event must have type, player, and battle.");
        }

        this.type = type;
        this.points = type.getPoints();
        this.player = player;
        this.battle = battle;
    }

    /**
     * Method to create a new event
     * @param type is one of the types of events
     * @param player is the player who caused the event
     * @param battle is the battle in which the event happened
     * @return a new Event object
     */
    public static Event create(EventType type, Player player, Battle battle) {
        return new Event(type, player, battle);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
