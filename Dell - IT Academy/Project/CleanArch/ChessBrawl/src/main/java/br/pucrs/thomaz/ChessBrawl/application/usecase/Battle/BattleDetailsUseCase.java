package br.pucrs.thomaz.ChessBrawl.application.usecase.Battle;

import br.pucrs.thomaz.ChessBrawl.domain.entities.Battle;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Round;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Tournament;
import br.pucrs.thomaz.ChessBrawl.domain.repository.TournamentRepository;
import br.pucrs.thomaz.ChessBrawl.application.dto.BattleDTO;
import br.pucrs.thomaz.ChessBrawl.application.mapper.BattleMapper;

import org.springframework.stereotype.Service;

@Service
public class BattleDetailsUseCase {

    private final TournamentRepository tournamentRepository;

    public BattleDetailsUseCase(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public BattleDTO getBattleDetails(Long tournamentCode, int roundNumber, int battleIndex) {
        Tournament tournament = tournamentRepository.findByCode(tournamentCode)
            .orElseThrow(() -> new IllegalArgumentException("The tournament was not found."));

        if (roundNumber < 1 || roundNumber > tournament.getRounds().size()) {
            throw new IllegalArgumentException("Invalid round.");
        }

        Round round = tournament.getRounds().get(roundNumber - 1);

        if (battleIndex < 0 || battleIndex >= round.getBattles().size()) {
            throw new IllegalArgumentException("Invalid battle index.");
        }

        Battle battle = round.getBattles().get(battleIndex);
        return BattleMapper.toDTO(battle);
    }
}
