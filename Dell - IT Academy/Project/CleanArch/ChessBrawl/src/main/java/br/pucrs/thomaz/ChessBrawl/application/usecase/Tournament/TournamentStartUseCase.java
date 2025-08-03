package br.pucrs.thomaz.ChessBrawl.application.usecase.Tournament;

import br.pucrs.thomaz.ChessBrawl.domain.entities.Battle;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Player;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Round;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Tournament;
import br.pucrs.thomaz.ChessBrawl.domain.repository.TournamentRepository;
import br.pucrs.thomaz.ChessBrawl.application.dto.TournamentDTO;
import br.pucrs.thomaz.ChessBrawl.application.mapper.TournamentMapper;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TournamentStartUseCase {

    private final TournamentRepository tournamentRepository;

    public TournamentStartUseCase(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public TournamentDTO startTournament(Long tournamentCode) {
        Tournament tournament = tournamentRepository.findByCode(tournamentCode)
            .orElseThrow(() -> new IllegalArgumentException("The tournament was not found."));

        if (!tournament.getRounds().isEmpty()) {
            throw new IllegalStateException("The tournament has already begun.");
        }

        List<Player> players = new ArrayList<>(tournament.getPlayers());
        Collections.shuffle(players);

        List<Battle> battles = new ArrayList<>();
        for (int i = 0; i < players.size(); i += 2) {
            Player player1 = players.get(i);
            Player player2 = players.get(i + 1);
            battles.add(Battle.create(player1, player2));
        }

        Round firstRound = Round.create(1, battles);

        tournament.getRounds().add(firstRound);

        tournamentRepository.save(tournament);

        return TournamentMapper.toDTO(tournament);
    }
}
