package br.pucrs.thomaz.ChessBrawl.infrastructure.rest.controllers;

import br.pucrs.thomaz.ChessBrawl.application.dto.BattleDTO;
import br.pucrs.thomaz.ChessBrawl.application.dto.EventDTO;
import br.pucrs.thomaz.ChessBrawl.application.usecase.Battle.BattleAdministrationUseCase;
import br.pucrs.thomaz.ChessBrawl.application.usecase.Battle.BattleDetailsUseCase;
import br.pucrs.thomaz.ChessBrawl.application.usecase.Battle.BattleEventRegistrationUseCase;
import br.pucrs.thomaz.ChessBrawl.application.usecase.Battle.BattleFinalizerUseCase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Class that represents a Controller for the Battle entity
 * 
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */

@RestController
@RequestMapping("/servcad/battles")
public class BattleController {
    private final BattleAdministrationUseCase battleAdministrationUseCase;
    private final BattleDetailsUseCase battleDetailsUseCase;
    private final BattleEventRegistrationUseCase battleEventRegistrationUseCase;
    private final BattleFinalizerUseCase battleFinalizerUseCase;

    public BattleController(BattleAdministrationUseCase battleAdministrationUseCase,
            BattleDetailsUseCase battleDetailsUseCase,
            BattleEventRegistrationUseCase battleEventRegistrationUseCase,
            BattleFinalizerUseCase battleFinalizerUseCase) {
        this.battleAdministrationUseCase = battleAdministrationUseCase;
        this.battleDetailsUseCase = battleDetailsUseCase;
        this.battleEventRegistrationUseCase = battleEventRegistrationUseCase;
        this.battleFinalizerUseCase = battleFinalizerUseCase;
    }

    /**
     * Obtém os detalhes de uma batalha específica.
     */
    @GetMapping("/details")
    public ResponseEntity<BattleDTO> getBattleDetails(
            @RequestParam Long tournamentCode,
            @RequestParam int roundNumber,
            @RequestParam int battleIndex) {
        try {
            BattleDTO battle = battleDetailsUseCase.getBattleDetails(tournamentCode, roundNumber, battleIndex);
            return new ResponseEntity<>(battle, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Seleciona uma batalha específica (equivalente ao details, mas pode ser usado
     * para lógica separada).
     */
    @GetMapping("/select")
    public ResponseEntity<BattleDTO> selectBattle(
            @RequestParam Long tournamentCode,
            @RequestParam int roundNumber,
            @RequestParam int battleIndex) {
        try {
            BattleDTO battle = battleAdministrationUseCase.selectBattle(tournamentCode, roundNumber, battleIndex);
            return new ResponseEntity<>(battle, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Registra um novo evento em uma batalha.
     */
    @PostMapping("/{battleId}/events")
    public ResponseEntity<Void> registerEvent(
            @PathVariable Long battleId,
            @RequestBody EventDTO eventDTO,
            @RequestParam Long playerId) {
        try {
            battleEventRegistrationUseCase.registerEvent(battleId, playerId, eventDTO.getType());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Finaliza uma batalha com um vencedor.
     */
    @PostMapping("/{battleId}/finalize")
    public ResponseEntity<BattleDTO> finalizeBattle(@PathVariable Long battleId) {
        try {
            BattleDTO finalizedBattle = battleFinalizerUseCase.execute(battleId);
            return new ResponseEntity<>(finalizedBattle, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
