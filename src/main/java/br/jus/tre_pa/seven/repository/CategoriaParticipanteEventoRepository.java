package br.jus.tre_pa.seven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.jus.tre_pa.seven.domain.CategoriaParticipanteEvento;

@Repository
public interface CategoriaParticipanteEventoRepository extends JpaRepository<CategoriaParticipanteEvento, Long> {

	@Query("select c from CategoriaParticipanteEvento c inner join c.evento e where e.id = ?1")
	List<CategoriaParticipanteEvento> findAllByEventoId(Long idEvento);
}
