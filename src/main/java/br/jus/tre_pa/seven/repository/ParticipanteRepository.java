package br.jus.tre_pa.seven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.jus.tre_pa.seven.domain.Participante;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
	
	
	@Query("select c from Participante c where c.email = ?1")
	Participante buscarParticipantePorEmail(String email);

}
