package br.pucrs.thomaz.ChessBrawl.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.pucrs.thomaz.ChessBrawl.application.dto.TournamentDTO;
import br.pucrs.thomaz.ChessBrawl.application.dto.PlayerDTO;
import br.pucrs.thomaz.ChessBrawl.application.dto.RoundDTO;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Player;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Tournament;

/**
 * Class that represents a Mapper for Tournament
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
public class TournamentMapper {

    public static TournamentDTO toDTO(Tournament tournament) {
        TournamentDTO dto = new TournamentDTO();
        dto.setCode(tournament.getCode());
        dto.setFinished(tournament.isFinished());

        // Players
        List<PlayerDTO> playerDTOs = tournament.getPlayers().stream()
            .map(PlayerMapper::toDTO)
            .collect(Collectors.toList());
        dto.setPlayers(playerDTOs);

        // Rounds
        List<RoundDTO> roundDTOs = tournament.getRounds().stream()
            .map(RoundMapper::toDTO)
            .collect(Collectors.toList());
        dto.setRounds(roundDTOs);

        // Champion
        Player champion = tournament.getChampion();
        if (champion != null) {
            dto.setChampionCode(champion.getCode());
            dto.setChampionNickname(champion.getNickname());
        }

        return dto;
    }

    public static Tournament toEntity(TournamentDTO dto, List<Player> players) {
        return Tournament.create(players);
    }
}
