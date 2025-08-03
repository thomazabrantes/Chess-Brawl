package br.pucrs.thomaz.ChessBrawl.application.usecase.Tournament;

import br.pucrs.thomaz.ChessBrawl.domain.entities.Round;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Tournament;
import br.pucrs.thomaz.ChessBrawl.domain.repository.TournamentRepository;
import org.springframework.stereotype.Service;

@Service
public class RoundAdvanceUseCase {

    private final TournamentRepository tournamentRepository;

    public RoundAdvanceUseCase(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public Round advanceRound(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
            .orElseThrow(() -> new IllegalArgumentException("Tournament not found."));

        if (tournament.isFinished()) {
            throw new IllegalStateException("The tournament is already finished.");
        }

        if (!tournament.getCurrentRound().isFinished()) {
            throw new IllegalStateException("Current round is not finished yet.");
        }

        tournament.proceedToNextRound();
        tournamentRepository.save(tournament);

        return tournament.getCurrentRound();
    }
}
