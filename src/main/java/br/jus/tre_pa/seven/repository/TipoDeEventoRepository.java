package br.jus.tre_pa.seven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.tre_pa.seven.domain.TipoDeEvento;

@Repository
public interface TipoDeEventoRepository extends JpaRepository<TipoDeEvento, Long> {

}
