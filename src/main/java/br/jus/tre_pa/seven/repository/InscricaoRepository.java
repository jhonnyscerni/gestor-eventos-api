package br.jus.tre_pa.seven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.jus.tre_pa.seven.domain.Inscricao;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

	
	@Query("select c from Inscricao c inner join c.evento e where e.id = ?1")
	List<Inscricao> findAllByEventoId(Long idEvento);
}
