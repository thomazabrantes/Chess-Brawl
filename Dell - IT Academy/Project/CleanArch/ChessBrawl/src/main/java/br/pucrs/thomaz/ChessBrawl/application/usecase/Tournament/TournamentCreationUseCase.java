package br.pucrs.thomaz.ChessBrawl.application.usecase.Tournament;

import br.pucrs.thomaz.ChessBrawl.domain.entities.Player;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Tournament;
import br.pucrs.thomaz.ChessBrawl.domain.repository.PlayerRepository;
import br.pucrs.thomaz.ChessBrawl.domain.repository.TournamentRepository;
import br.pucrs.thomaz.ChessBrawl.application.dto.TournamentDTO;
import br.pucrs.thomaz.ChessBrawl.application.mapper.TournamentMapper;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TournamentCreationUseCase {

    private final PlayerRepository playerRepository;
    private final TournamentRepository tournamentRepository;

    public TournamentCreationUseCase(PlayerRepository playerRepository, TournamentRepository tournamentRepository) {
        this.playerRepository = playerRepository;
        this.tournamentRepository = tournamentRepository;
    }

    public TournamentDTO createTournament(List<String> nicknames) {
        Set<String> unique = new HashSet<>(nicknames);
        if (unique.size() != nicknames.size()) {
            throw new IllegalArgumentException("Duplicated nicknames are not allowed.");
        }
    
        List<Player> players = nicknames.stream()
            .map(nickname -> playerRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("Player by nickname '" + nickname + "' was not found.")))
            .collect(Collectors.toList());
    
        Tournament tournament = Tournament.create(players); 
        tournament = tournamentRepository.save(tournament);
    
        return TournamentMapper.toDTO(tournament);
    }
    
}
