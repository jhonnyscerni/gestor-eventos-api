package br.jus.tre_pa.seven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.tre_pa.seven.domain.Evento;
import br.jus.tre_pa.seven.repository.evento.EventoRepositoryQuery;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>, EventoRepositoryQuery {

}
