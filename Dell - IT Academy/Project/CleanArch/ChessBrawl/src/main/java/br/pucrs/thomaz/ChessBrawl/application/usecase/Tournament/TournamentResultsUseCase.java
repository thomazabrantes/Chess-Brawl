package br.pucrs.thomaz.ChessBrawl.application.usecase.Tournament;

import br.pucrs.thomaz.ChessBrawl.application.dto.PlayerStatsDTO;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Player;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Tournament;
import br.pucrs.thomaz.ChessBrawl.domain.repository.TournamentRepository;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentResultsUseCase {

    private final TournamentRepository tournamentRepository;

    public TournamentResultsUseCase(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public List<PlayerStatsDTO> getTournamentResults(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
            .orElseThrow(() -> new IllegalArgumentException("Tournament not found."));

        List<Player> players = tournament.getPlayers();

        return players.stream()
            .sorted(Comparator.comparingInt(Player::getTournamentPoints).reversed())
            .map(player -> new PlayerStatsDTO(
                player.getCode(),
                player.getName(),
                player.getNickname(),
                player.getRankingPoints(),
                player.getTournamentPoints(),
                player.getOriginalMove(),
                player.getMistake(),
                player.getAdvantageousPosition(),
                player.getDisrespectToOpponent(),
                player.getFuryAttack()
            ))
            .collect(Collectors.toList());
    }
}
