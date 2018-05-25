package br.jus.tre_pa.seven.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.jus.tre_pa.seven.domain.Certificado;

public interface CertificadoRepository extends JpaRepository<Certificado, Long> {
	
	@Query("select c from Certificado c inner join c.evento e where e.id = ?1")
	Certificado findAllByEventoId(Long idEvento);

}
