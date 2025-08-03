package br.pucrs.thomaz.ChessBrawl.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.pucrs.thomaz.ChessBrawl.application.dto.RoundDTO;
import br.pucrs.thomaz.ChessBrawl.application.dto.BattleDTO;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Battle;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Round;

/**
 * Class that represents a Mapper for Round
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
public class RoundMapper {

    public static RoundDTO toDTO(Round round) {
        RoundDTO dto = new RoundDTO();
        dto.setCode(round.getCode());
        dto.setRoundNumber(round.getRoundNumber());
        dto.setFinished(round.isFinished());

        List<BattleDTO> battleDTOs = round.getBattles().stream()
            .map(BattleMapper::toDTO)
            .collect(Collectors.toList());
        dto.setBattles(battleDTOs);

        return dto;
    }

    public static Round toEntity(RoundDTO dto, List<Battle> battles) {
        return Round.create(dto.getRoundNumber(), battles);
    }
}
