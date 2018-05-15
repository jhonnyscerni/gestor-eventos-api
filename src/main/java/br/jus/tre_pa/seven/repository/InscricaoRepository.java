package br.jus.tre_pa.seven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.jus.tre_pa.seven.domain.Inscricao;
import br.jus.tre_pa.seven.repository.inscricao.InscricaoRepositoryQuery;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long>, InscricaoRepositoryQuery {

	@Query("select c from Inscricao c inner join c.evento e where e.id = ?1")
	List<Inscricao> findAllByEventoId(Long idEvento);

	@Query("select p from Inscricao p inner join p.participante e where p.id = ?1")
	Inscricao findOneParticipanteByInscricao(Long idInscricao);
	
	
	/*
	 * SELECT * FROM INSCRICAO i
		INNER JOIN EVENTO e ON i.EVENTO_ID = e.id
		INNER JOIN CERTIFICADO c ON c.EVENTO_ID = e.id
		INNER JOIN PARTICIPANTE P ON p.id = i.participante_id
		Where i.id = 9
	 * */


	Inscricao findOneByCodigoQrCode(String uuid);
}
