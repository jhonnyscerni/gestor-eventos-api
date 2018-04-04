package br.jus.tre_pa.seven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.tre_pa.seven.domain.CategoriaParticipante;

@Repository
public interface CategoriaParticipanteRepository extends JpaRepository<CategoriaParticipante, Long> {
	
}
