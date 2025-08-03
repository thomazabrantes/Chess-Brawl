package br.pucrs.thomaz.ChessBrawl.application.dto;

import java.util.List;

/**
 * Class that represents a Round Data Transfer Object (DTO)
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
public class RoundDTO {
    private Long code;
    private int roundNumber;
    private boolean finished;

    private List<BattleDTO> battles;

    public RoundDTO() {
        // Default constructor, no parameters
    }

    public RoundDTO(Long code, int roundNumber, boolean finished, List<BattleDTO> battles) {
        this.code = code;
        this.roundNumber = roundNumber;
        this.finished = finished;
        this.battles = battles;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public List<BattleDTO> getBattles() {
        return battles;
    }

    public void setBattles(List<BattleDTO> battles) {
        this.battles = battles;
    }

    @Override
    public String toString() {
        return "RoundDTO [code=" + code + ", roundNumber=" + roundNumber +
                ", finished=" + finished + ", battles=" + battles + "]";
    }
}
