package br.pucrs.thomaz.ChessBrawl.application.dto;

import java.util.List;

/**
 * Class that represents a Tournament Data Transfer Object (DTO)
 * 
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
public class TournamentDTO {
    private Long code;

    private List<PlayerDTO> players;
    private List<RoundDTO> rounds;

    private Long championCode;
    private String championNickname;

    private boolean finished;

    public TournamentDTO() {
        // Default constructor, no parameters
    }

    public TournamentDTO(Long code, List<PlayerDTO> players, List<RoundDTO> rounds,
            Long championCode, String championNickname, boolean finished) {
        this.code = code;
        this.players = players;
        this.rounds = rounds;
        this.championCode = championCode;
        this.championNickname = championNickname;
        this.finished = finished;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }

    public List<RoundDTO> getRounds() {
        return rounds;
    }

    public void setRounds(List<RoundDTO> rounds) {
        this.rounds = rounds;
    }

    public Long getChampionCode() {
        return championCode;
    }

    public void setChampionCode(Long championCode) {
        this.championCode = championCode;
    }

    public String getChampionNickname() {
        return championNickname;
    }

    public void setChampionNickname(String championNickname) {
        this.championNickname = championNickname;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "TournamentDTO [code=" + code + ", championCode=" + championCode +
                ", championNickname=" + championNickname + ", finished=" + finished +
                ", players=" + players + ", rounds=" + rounds + "]";
    }
}
