package br.jus.tre_pa.seven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.jus.tre_pa.seven.domain.Facilitador;

@Repository
public interface FacilitadorRepository extends JpaRepository<Facilitador, Long> {

	@Query("select c from Facilitador c inner join c.evento e where e.id = ?1")
	List<Facilitador> findAllByEventoId(Long idEvento);
}
