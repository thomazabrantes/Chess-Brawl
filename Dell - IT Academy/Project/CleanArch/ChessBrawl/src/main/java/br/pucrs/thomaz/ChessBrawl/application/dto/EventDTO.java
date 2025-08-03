package br.pucrs.thomaz.ChessBrawl.application.dto;

import br.pucrs.thomaz.ChessBrawl.domain.entities.EventType;

/**
 * Class that represents an Event Data Transfer Object (DTO)
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
public class EventDTO {
    private Long code;
    private EventType type;
    private int points;

    private Long playerCode;
    private String playerNickname;

    private Long battleCode;

    public EventDTO() {
        // Default constructor, no parameters
    }

    public EventDTO(Long code, EventType type, int points, Long playerCode, String playerNickname, Long battleCode) {
        this.code = code;
        this.type = type;
        this.points = points;
        this.playerCode = playerCode;
        this.playerNickname = playerNickname;
        this.battleCode = battleCode;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Long getPlayerCode() {
        return playerCode;
    }

    public void setPlayerCode(Long playerCode) {
        this.playerCode = playerCode;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }

    public void setPlayerNickname(String playerNickname) {
        this.playerNickname = playerNickname;
    }

    public Long getBattleCode() {
        return battleCode;
    }

    public void setBattleCode(Long battleCode) {
        this.battleCode = battleCode;
    }

    @Override
    public String toString() {
        return "EventDTO [code=" + code + ", type=" + type + ", points=" + points +
                ", playerCode=" + playerCode + ", playerNickname=" + playerNickname +
                ", battleCode=" + battleCode + "]";
    }
}
