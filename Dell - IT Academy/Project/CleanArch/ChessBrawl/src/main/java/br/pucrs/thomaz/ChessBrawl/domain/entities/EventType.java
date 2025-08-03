package br.pucrs.thomaz.ChessBrawl.domain.entities;

/**
 * Class that represents the types of events that can happen in a battle
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
public enum EventType {
    ORIGINAL_MOVE(5),
    MISTAKE(-3),
    ADVANTAGEOUS_POSITION(2),
    DISRESPECT_TO_OPPONENT(-5),
    FURY_ATTACK(-7);

    private final int points;

    EventType(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
