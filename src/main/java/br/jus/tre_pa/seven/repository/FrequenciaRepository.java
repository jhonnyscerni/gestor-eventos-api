package br.jus.tre_pa.seven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.jus.tre_pa.seven.domain.Frequencia;

@Repository
public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {
	
	@Query("select f from Frequencia f inner join f.inscricao i inner join i.participante p inner join i.evento e where e.id = ?1")
	List<Frequencia> findAllFrequenciaByInscricaoByParticipanteByEnvento(Long idEvento);
	
	@Query("select f from Frequencia f inner join f.inscricao i inner join i.participante p inner join i.evento e where e.id = ?1 and i.id = ?2")
	List<Frequencia> findAllFrequenciaByInscricaoByParticipanteByEnventoParticipante(Long idEvento, Long idInscricao);

}
