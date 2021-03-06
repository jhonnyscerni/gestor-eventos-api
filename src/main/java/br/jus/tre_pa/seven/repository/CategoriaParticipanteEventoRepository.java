package br.jus.tre_pa.seven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.jus.tre_pa.seven.domain.CategoriaParticipanteEvento;

@Repository
public interface CategoriaParticipanteEventoRepository extends JpaRepository<CategoriaParticipanteEvento, Long> {

	@Query("select c from CategoriaParticipanteEvento c inner join c.evento e inner join c.categoriaParticipante where e.id = ?1")
	List<CategoriaParticipanteEvento> findAllByEventoId(Long idEvento);
	
	@Query("SELECT SUM(c.vagas) from CategoriaParticipanteEvento c inner join c.evento e where e.id = ?1")
    int getSumVagasByEvento(Long idEvento);
	
	@Query("SELECT SUM(c.vagas) from CategoriaParticipanteEvento c inner join c.categoriaParticipante cp inner join c.evento e where e.id = ?1 and cp.id = ?2")
	int getSumVagasByEventoByCategoriaParticipanteEvento(Long idEvento, Long idCategoriaParticipante);
}
