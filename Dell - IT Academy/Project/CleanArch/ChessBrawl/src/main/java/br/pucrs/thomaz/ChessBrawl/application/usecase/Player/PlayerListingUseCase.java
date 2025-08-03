package br.pucrs.thomaz.ChessBrawl.application.usecase.Player;

import br.pucrs.thomaz.ChessBrawl.application.dto.PlayerDTO;
import br.pucrs.thomaz.ChessBrawl.application.mapper.PlayerMapper;
import br.pucrs.thomaz.ChessBrawl.domain.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerListingUseCase {

    private final PlayerRepository playerRepository;

    public PlayerListingUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerDTO> listPlayers() {
        return playerRepository.findAll().stream()
                .map(PlayerMapper::toDTO)
                .collect(Collectors.toList());
    }
}
