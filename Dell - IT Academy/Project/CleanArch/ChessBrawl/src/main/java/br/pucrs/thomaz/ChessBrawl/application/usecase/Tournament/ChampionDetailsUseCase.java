package br.pucrs.thomaz.ChessBrawl.application.usecase.Tournament;

import br.pucrs.thomaz.ChessBrawl.domain.entities.Player;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Tournament;
import br.pucrs.thomaz.ChessBrawl.domain.repository.TournamentRepository;
import br.pucrs.thomaz.ChessBrawl.application.dto.PlayerDTO;
import br.pucrs.thomaz.ChessBrawl.application.mapper.PlayerMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class ChampionDetailsUseCase {

    private final TournamentRepository tournamentRepository;

    public ChampionDetailsUseCase(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public PlayerDTO getChampion(Long tournamentCode) {
        Tournament tournament = tournamentRepository.findByCode(tournamentCode)
            .orElseThrow(() -> new IllegalArgumentException("The tournament was not found."));

        if (tournament.getPlayers().isEmpty()) {
            throw new IllegalStateException("No players in the tournament.");
        }

        Player champion = tournament.getPlayers().stream()
            .max(Comparator.comparingInt(Player::getTournamentPoints))
            .orElseThrow(() -> new IllegalStateException("Could not determine champion."));

        return PlayerMapper.toDTO(champion);
    }
}
