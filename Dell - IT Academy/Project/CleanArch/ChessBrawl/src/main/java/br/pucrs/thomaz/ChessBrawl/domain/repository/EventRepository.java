package br.pucrs.thomaz.ChessBrawl.domain.repository;

import br.pucrs.thomaz.ChessBrawl.domain.entities.Battle;
import br.pucrs.thomaz.ChessBrawl.domain.entities.Event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class that represents a Repository for the Event entity
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByBattle(Battle battle);
    List<Event> findByPlayerCode(Long playerId);
}
