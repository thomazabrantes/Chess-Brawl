package br.pucrs.thomaz.ChessBrawl.application.dto;

/**
 * Class that represents a Battle Data Transfer Object (DTO)
 * 
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
public class BattleDTO {
    private Long code;

    private Long playerACode;
    private String playerANickname;

    private Long playerBCode;
    private String playerBNickname;

    private Long winnerCode;

    private boolean blitzMatchUsed = false;
    private boolean finished = false;

    private int playerAScore;
    private int playerBScore;

    public BattleDTO() {
        // Default constructor, no parameters
    }

    public BattleDTO(Long code, Long playerACode, String playerANickname, Long playerBCode, String playerBNickname,
            Long winnerCode, boolean blitzMatchUsed, boolean finished, int playerAScore, int playerBScore) {
        this.code = code;
        this.playerACode = playerACode;
        this.playerANickname = playerANickname;
        this.playerBCode = playerBCode;
        this.playerBNickname = playerBNickname;
        this.winnerCode = winnerCode;
        this.blitzMatchUsed = blitzMatchUsed;
        this.finished = finished;
        this.playerAScore = playerAScore;
        this.playerBScore = playerBScore;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getPlayerACode() {
        return playerACode;
    }

    public void setPlayerACode(Long playerACode) {
        this.playerACode = playerACode;
    }

    public String getPlayerANickname() {
        return playerANickname;
    }

    public void setPlayerANickname(String playerANickname) {
        this.playerANickname = playerANickname;
    }

    public Long getPlayerBCode() {
        return playerBCode;
    }

    public void setPlayerBCode(Long playerBCode) {
        this.playerBCode = playerBCode;
    }

    public String getPlayerBNickname() {
        return playerBNickname;
    }

    public void setPlayerBNickname(String playerBNickname) {
        this.playerBNickname = playerBNickname;
    }

    public Long getWinnerCode() {
        return winnerCode;
    }

    public void setWinnerCode(Long winnerCode) {
        this.winnerCode = winnerCode;
    }

    public boolean isBlitzMatchUsed() {
        return blitzMatchUsed;
    }

    public void setBlitzMatchUsed(boolean blitzMatchUsed) {
        this.blitzMatchUsed = blitzMatchUsed;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getPlayerAScore() {
        return playerAScore;
    }

    public void setPlayerAScore(int playerAScore) {
        this.playerAScore = playerAScore;
    }

    public int getPlayerBScore() {
        return playerBScore;
    }

    public void setPlayerBScore(int playerBScore) {
        this.playerBScore = playerBScore;
    }

    @Override
    public String toString() {
        return "BattleDTO [code=" + code + ", playerACode=" + playerACode + ", playerANickname=" + playerANickname
                + ", playerBCode=" + playerBCode + ", playerBNickname=" + playerBNickname + ", winnerCode=" + winnerCode
                + ", blitzMatchUsed=" + blitzMatchUsed + ", finished=" + finished + ", playerAScore=" + playerAScore
                + ", playerBScore=" + playerBScore + "]";
    }
}
