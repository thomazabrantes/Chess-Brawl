package br.pucrs.thomaz.ChessBrawl.application.usecase.Player;

import br.pucrs.thomaz.ChessBrawl.application.dto.PlayerDTO;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Player;
import br.pucrs.thomaz.ChessBrawl.domain.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerRegistrationUseCase {
    private final PlayerRepository playerRepository;

    public PlayerRegistrationUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * * Method to register a player in the system
     * @param name is the player's name
     * @param nickname is the player's nickname
     * @param rankingPoints is the player's ranking points
     * @throws IllegalArgumentException if the nickname is already in use
     * @return PlayerDTO with the player's information
     */
    public PlayerDTO registerPlayer(String name, String nickname, int rankingPoints) {
        if (playerRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalArgumentException("Nickname is not available.");
        }
        Player player = Player.create(name, nickname, rankingPoints);
        player = playerRepository.save(player);
        return new PlayerDTO(player.getCode(), player.getName(), player.getNickname(), player.getRankingPoints());
    }
}
