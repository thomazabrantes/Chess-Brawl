package br.pucrs.thomaz.ChessBrawl.application.mapper;

import br.pucrs.thomaz.ChessBrawl.domain.entities.Player;
import br.pucrs.thomaz.ChessBrawl.application.dto.PlayerDTO;

/**
 * Class that represents a Mapper for Player
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
public class PlayerMapper {
    public static PlayerDTO toDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setCode(player.getCode());
        dto.setName(player.getName());
        dto.setNickname(player.getNickname());
        dto.setRankingPoints(player.getRankingPoints());
        dto.setTournamentPoints(player.getTournamentPoints());
        dto.setOriginalMove(player.getOriginalMove());
        dto.setMistake(player.getMistake());
        dto.setAdvantageousPosition(player.getAdvantageousPosition());
        dto.setDisrespectToOpponent(player.getDisrespectToOpponent());
        dto.setFuryAttack(player.getFuryAttack());
        return dto;
    }

    public static Player toEntity(PlayerDTO dto) {
        return Player.create(dto.getName(), dto.getNickname(), dto.getRankingPoints());
    }
}
