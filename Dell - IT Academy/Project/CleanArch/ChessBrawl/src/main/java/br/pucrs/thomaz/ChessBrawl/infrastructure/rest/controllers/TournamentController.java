package br.pucrs.thomaz.ChessBrawl.infrastructure.rest.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pucrs.thomaz.ChessBrawl.application.dto.PlayerDTO;
import br.pucrs.thomaz.ChessBrawl.application.dto.PlayerStatsDTO;
import br.pucrs.thomaz.ChessBrawl.application.dto.RoundDTO;
import br.pucrs.thomaz.ChessBrawl.application.dto.TournamentDTO;
import br.pucrs.thomaz.ChessBrawl.application.mapper.RoundMapper;
import br.pucrs.thomaz.ChessBrawl.application.usecase.Tournament.TournamentCreationUseCase;
import br.pucrs.thomaz.ChessBrawl.application.usecase.Tournament.TournamentStartUseCase;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Round;
import br.pucrs.thomaz.ChessBrawl.application.usecase.Tournament.RoundAdvanceUseCase;
import br.pucrs.thomaz.ChessBrawl.application.usecase.Tournament.TournamentResultsUseCase;
import br.pucrs.thomaz.ChessBrawl.application.usecase.Tournament.ChampionDetailsUseCase;

/**
 * Class that represents a Controller for the Tournament entity
 * 
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */

@RestController
@RequestMapping("/servcad/tournament")
public class TournamentController {
    private final TournamentCreationUseCase tournamentCreationUseCase;
    private final TournamentStartUseCase tournamentStartUseCase;
    private final RoundAdvanceUseCase roundAdvanceUseCase;
    private final TournamentResultsUseCase tournamentResultsUseCase;
    private final ChampionDetailsUseCase championDetailsUseCase;

    public TournamentController(TournamentCreationUseCase tournamentCreationUseCase,
            TournamentStartUseCase tournamentStartUseCase,
            RoundAdvanceUseCase roundAdvanceUseCase,
            TournamentResultsUseCase tournamentResultsUseCase,
            ChampionDetailsUseCase championDetailsUseCase) {
        this.tournamentCreationUseCase = tournamentCreationUseCase;
        this.tournamentStartUseCase = tournamentStartUseCase;
        this.roundAdvanceUseCase = roundAdvanceUseCase;
        this.tournamentResultsUseCase = tournamentResultsUseCase;
        this.championDetailsUseCase = championDetailsUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<TournamentDTO> createTournament(@RequestBody List<String> nicknames) {
        TournamentDTO tournament = tournamentCreationUseCase.createTournament(nicknames);
        return ResponseEntity.status(HttpStatus.CREATED).body(tournament);
    }

    @PostMapping("/{tournamentCode}/start")
    public ResponseEntity<TournamentDTO> startTournament(@PathVariable Long tournamentCode) {
        TournamentDTO startedTournament = tournamentStartUseCase.startTournament(tournamentCode);
        return ResponseEntity.ok(startedTournament);
    }

    @PostMapping("/{tournamentCode}/advance")
    public ResponseEntity<RoundDTO> advanceRound(@PathVariable Long tournamentCode) {
        Round round = roundAdvanceUseCase.advanceRound(tournamentCode);
        RoundDTO roundDTO = RoundMapper.toDTO(round); 
        return ResponseEntity.ok(roundDTO);
    }

    @GetMapping("/{tournamentId}/results")
    public ResponseEntity<List<PlayerStatsDTO>> getResults(@PathVariable Long tournamentId) {
        List<PlayerStatsDTO> results = tournamentResultsUseCase.getTournamentResults(tournamentId);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{tournamentCode}/champion")
    public ResponseEntity<PlayerDTO> getChampion(@PathVariable Long tournamentCode) {
        PlayerDTO champion = championDetailsUseCase.getChampion(tournamentCode);
        return ResponseEntity.ok(champion);
    }
}
