package br.jus.tre_pa.seven.repository.evento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.tre_pa.seven.domain.Evento;
import br.jus.tre_pa.seven.repository.filter.EventoFilter;

public interface EventoRepositoryQuery {

	public Page<Evento> filtrar(EventoFilter eventoFilter, Pageable pageable);
}
