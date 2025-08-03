package br.pucrs.thomaz.ChessBrawl.application.dto;

import java.util.List;

public class PlayerEventHistoryDTO {
    private Long playerId;
    private String playerNickname;
    private List<String> eventTypes;

    public PlayerEventHistoryDTO(Long playerId, String playerNickname, List<String> eventTypes) {
        this.playerId = playerId;
        this.playerNickname = playerNickname;
        this.eventTypes = eventTypes;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }

    public List<String> getEventTypes() {
        return eventTypes;
    }
}
