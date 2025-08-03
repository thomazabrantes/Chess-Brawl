package br.pucrs.thomaz.ChessBrawl.application.usecase.Battle;

import br.pucrs.thomaz.ChessBrawl.domain.entities.Battle;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Event;
import br.pucrs.thomaz.ChessBrawl.domain.entities.EventType;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Player;
import br.pucrs.thomaz.ChessBrawl.domain.repository.BattleRepository;
import br.pucrs.thomaz.ChessBrawl.domain.repository.EventRepository;
import br.pucrs.thomaz.ChessBrawl.domain.repository.PlayerRepository;

import org.springframework.stereotype.Service;

@Service
public class BattleEventRegistrationUseCase {

    private final BattleRepository battleRepository;
    private final PlayerRepository playerRepository;
    private final EventRepository eventRepository;

    public BattleEventRegistrationUseCase(BattleRepository battleRepository,
            PlayerRepository playerRepository,
            EventRepository eventRepository) {
        this.battleRepository = battleRepository;
        this.playerRepository = playerRepository;
        this.eventRepository = eventRepository;
    }

    public void registerEvent(Long battleCode, Long playerCode, EventType eventType) {
        Battle battle = battleRepository.findById(battleCode)
                .orElseThrow(() -> new IllegalArgumentException("The battle was not found."));

        Player player = playerRepository.findById(playerCode)
                .orElseThrow(() -> new IllegalArgumentException("The player was not found."));

        if (!player.equals(battle.getPlayerA()) && !player.equals(battle.getPlayerB())) {
            throw new IllegalArgumentException("The player does not belong in this battle.");
        }

        Event event = Event.create(eventType, player, battle);
        battle.addScore(player, event.getPoints());

        player.applyEventEffect(eventType);

        playerRepository.save(player);     

        eventRepository.save(event);
        battleRepository.save(battle);
    }
}
