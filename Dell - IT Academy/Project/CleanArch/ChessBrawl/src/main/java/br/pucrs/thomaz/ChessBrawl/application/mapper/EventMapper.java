package br.pucrs.thomaz.ChessBrawl.application.mapper;

import br.pucrs.thomaz.ChessBrawl.application.dto.EventDTO;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Event;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Player;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Battle;

/**
 * Class that represents a Mapper for Event
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
public class EventMapper {

    public static EventDTO toDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setCode(event.getCode());
        dto.setType(event.getType());
        dto.setPoints(event.getPoints());

        Player player = event.getPlayer();
        dto.setPlayerCode(player.getCode());
        dto.setPlayerNickname(player.getNickname());

        dto.setBattleCode(event.getBattle().getCode());

        return dto;
    }

    public static Event toEntity(EventDTO dto, Player player, Battle battle) {
        return Event.create(dto.getType(), player, battle);
    }
}
