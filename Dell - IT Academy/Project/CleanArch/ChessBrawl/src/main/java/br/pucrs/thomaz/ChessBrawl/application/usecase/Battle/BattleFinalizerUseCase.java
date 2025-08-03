package br.pucrs.thomaz.ChessBrawl.application.usecase.Battle;

import br.pucrs.thomaz.ChessBrawl.domain.entities.Battle;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Event;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Player;
import br.pucrs.thomaz.ChessBrawl.domain.repository.BattleRepository;
import br.pucrs.thomaz.ChessBrawl.domain.repository.EventRepository;
import br.pucrs.thomaz.ChessBrawl.application.dto.BattleDTO;
import br.pucrs.thomaz.ChessBrawl.application.mapper.BattleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BattleFinalizerUseCase {

    private final BattleRepository battleRepository;
    private final EventRepository eventRepository;
    private final BattleMapper battleMapper;

    public BattleFinalizerUseCase(
            BattleRepository battleRepository,
            EventRepository eventRepository,
            BattleMapper battleMapper
    ) {
        this.battleRepository = battleRepository;
        this.eventRepository = eventRepository;
        this.battleMapper = battleMapper;
    }

    @Transactional
    public BattleDTO execute(Long battleCode) {
        Battle battle = battleRepository.findById(battleCode)
                .orElseThrow(() -> new IllegalArgumentException("Battle not found with code: " + battleCode));

        if (battle.isFinished()) {
            throw new IllegalStateException("This battle is already finished.");
        }

        List<Event> events = eventRepository.findByBattle(battle);

        for (Event event : events) {
            Player player = event.getPlayer();
            int points = event.getPoints();
            battle.addScore(player, points);
        }

        battle.finalizeBattle();

        battleRepository.save(battle);

        return BattleMapper.toDTO(battle);
    }
}
