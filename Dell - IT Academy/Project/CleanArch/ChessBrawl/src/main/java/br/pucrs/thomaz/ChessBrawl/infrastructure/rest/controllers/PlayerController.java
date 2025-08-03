package br.pucrs.thomaz.ChessBrawl.infrastructure.rest.controllers;

import br.pucrs.thomaz.ChessBrawl.application.dto.PlayerDTO;
import br.pucrs.thomaz.ChessBrawl.application.dto.PlayerEventHistoryDTO;
import br.pucrs.thomaz.ChessBrawl.application.usecase.Player.PlayerEventHistoryUseCase;
import br.pucrs.thomaz.ChessBrawl.application.usecase.Player.PlayerRegistrationUseCase;
import br.pucrs.thomaz.ChessBrawl.application.usecase.Player.PlayerListingUseCase;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Class that represents a Controller for the Player entity
 * 
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */

@RestController
@RequestMapping("/servcad/players")
public class PlayerController {
    private final PlayerRegistrationUseCase playerRegistrationUseCase;
    private final PlayerEventHistoryUseCase playerEventHistoryUseCase;
    private final PlayerListingUseCase playerListingUseCase;

    public PlayerController(PlayerRegistrationUseCase playerRegistrationUseCase, PlayerEventHistoryUseCase playerEventHistoryUseCase, PlayerListingUseCase playerListingUseCase) {
        this.playerRegistrationUseCase = playerRegistrationUseCase;
        this.playerEventHistoryUseCase = playerEventHistoryUseCase;
        this.playerListingUseCase = playerListingUseCase;
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) {
        try {
            PlayerDTO created = playerRegistrationUseCase.registerPlayer(playerDTO.getName(), playerDTO.getNickname(),
                    playerDTO.getRankingPoints());
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{playerId}/events")
    public ResponseEntity<PlayerEventHistoryDTO> getPlayerEvents(@PathVariable Long playerId) {
        PlayerEventHistoryDTO history = playerEventHistoryUseCase.getHistory(playerId);
        return ResponseEntity.ok(history);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> playerListing() {
        try {
            List<PlayerDTO> players = playerListingUseCase.listPlayers();
            return new ResponseEntity<>(players, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
