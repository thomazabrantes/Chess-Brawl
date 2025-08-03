package br.pucrs.thomaz.ChessBrawl.application.mapper;

import org.springframework.stereotype.Component;

import br.pucrs.thomaz.ChessBrawl.application.dto.BattleDTO;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Battle;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Player;

/**
 * Class that represents a Mapper for Battle
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
@Component
public class BattleMapper {
    public static BattleDTO toDTO(Battle battle) {
        BattleDTO dto = new BattleDTO();
        dto.setCode(battle.getCode());

        dto.setPlayerACode(battle.getPlayerA().getCode());
        dto.setPlayerANickname(battle.getPlayerA().getNickname());

        dto.setPlayerBCode(battle.getPlayerB().getCode());
        dto.setPlayerBNickname(battle.getPlayerB().getNickname());

        Player winner = battle.getWinner();
        if (winner != null) {
            dto.setWinnerCode(winner.getCode());
        }

        dto.setBlitzMatchUsed(battle.isBlitzMatchUsed());
        dto.setFinished(battle.isFinished());
        dto.setPlayerAScore(battle.getPlayerAScore());
        dto.setPlayerBScore(battle.getPlayerBScore());

        return dto;
    }

    public static Battle toEntity(BattleDTO dto, Player playerA, Player playerB) {
        return Battle.create(playerA, playerB);
    }
}
