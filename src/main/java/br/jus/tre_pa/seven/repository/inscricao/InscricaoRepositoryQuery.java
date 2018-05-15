package br.jus.tre_pa.seven.repository.inscricao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.jus.tre_pa.seven.domain.Inscricao;
import br.jus.tre_pa.seven.repository.filter.InscricaoFilter;

public interface InscricaoRepositoryQuery {
	
	public Page<Inscricao> filtrar(Long idEvento, InscricaoFilter inscricaoFilter, Pageable pageable);

}
