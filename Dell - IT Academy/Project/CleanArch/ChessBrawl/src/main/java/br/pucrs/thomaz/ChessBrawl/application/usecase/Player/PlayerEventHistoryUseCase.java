package br.pucrs.thomaz.ChessBrawl.application.usecase.Player;

import br.pucrs.thomaz.ChessBrawl.application.dto.PlayerEventHistoryDTO;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Event;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Player;
import br.pucrs.thomaz.ChessBrawl.domain.repository.EventRepository;
import br.pucrs.thomaz.ChessBrawl.domain.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerEventHistoryUseCase {

    private final EventRepository eventRepository;
    private final PlayerRepository playerRepository;

    public PlayerEventHistoryUseCase(EventRepository eventRepository, PlayerRepository playerRepository) {
        this.eventRepository = eventRepository;
        this.playerRepository = playerRepository;
    }

    public PlayerEventHistoryDTO getHistory(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));

        List<Event> events = eventRepository.findByPlayerCode(playerId);
        List<String> types = events.stream()
                .map(e -> e.getType().toString())
                .collect(Collectors.toList());

        return new PlayerEventHistoryDTO(player.getCode(), player.getNickname(), types);
    }
}
