package br.pucrs.thomaz.ChessBrawl.domain.repository;

import br.pucrs.thomaz.ChessBrawl.domain.entities.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class that represents a Repository for the Round entity
 * @author Thomaz Abrantes de Oliveira Martinelli Silva
 * @version 1.0
 * @since 2025-05-01
 */
@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {
    
}
