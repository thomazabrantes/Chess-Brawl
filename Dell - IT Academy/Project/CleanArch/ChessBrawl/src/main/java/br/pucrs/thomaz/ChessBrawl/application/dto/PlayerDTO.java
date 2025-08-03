package br.pucrs.thomaz.ChessBrawl.application.dto;

/**
 * Class that represents a Player Data Transfer Object (DTO)
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
public class PlayerDTO {
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

    public PlayerDTO() {
        // Default constructor, no parameters
    }

    public PlayerDTO(Long code, String name, String nickname, int rankingPoints) {
        this.code = code;
        this.name = name;
        this.nickname = nickname;
        this.rankingPoints = rankingPoints;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getRankingPoints() {
        return rankingPoints;
    }

    public void setRankingPoints(int rankingPoints) {
        this.rankingPoints = rankingPoints;
    }

    public int getTournamentPoints() {
        return tournamentPoints;
    }

    public void setTournamentPoints(int tournamentPoints) {
        this.tournamentPoints = tournamentPoints;
    }

    public int getOriginalMove() {
        return originalMove;
    }

    public void setOriginalMove(int originalMove) {
        this.originalMove = originalMove;
    }

    public int getMistake() {
        return mistake;
    }

    public void setMistake(int mistake) {
        this.mistake = mistake;
    }

    public int getAdvantageousPosition() {
        return advantageousPosition;
    }

    public void setAdvantageousPosition(int advantageousPosition) {
        this.advantageousPosition = advantageousPosition;
    }

    public int getDisrespectToOpponent() {
        return disrespectToOpponent;
    }

    public void setDisrespectToOpponent(int disrespectToOpponent) {
        this.disrespectToOpponent = disrespectToOpponent;
    }

    public int getFuryAttack() {
        return furyAttack;
    }

    public void setFuryAttack(int furyAttack) {
        this.furyAttack = furyAttack;
    }

    @Override
    public String toString() {
        return "PlayerDTO [code=" + code + ", name=" + name + ", nickname=" + nickname + ", rankingPoints="
                + rankingPoints + ", tournamentPoints=" + tournamentPoints + ", originalMove=" + originalMove
                + ", mistake=" + mistake + ", advantageousPosition=" + advantageousPosition + ", disrespectToOpponent="
                + disrespectToOpponent + ", furyAttack=" + furyAttack + "]";
    }
}
