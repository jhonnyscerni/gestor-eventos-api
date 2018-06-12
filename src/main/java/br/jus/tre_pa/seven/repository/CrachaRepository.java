package br.jus.tre_pa.seven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.jus.tre_pa.seven.domain.Cracha;

public interface CrachaRepository extends JpaRepository<Cracha, Long> {
	
	@Query("select c from Cracha c inner join c.evento e where e.id = ?1")
	Cracha findAllByEventoId(Long idEvento);

}
