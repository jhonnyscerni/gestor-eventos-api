package br.jus.tre_pa.seven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.jus.tre_pa.seven.domain.Inscricao;
import br.jus.tre_pa.seven.projection.CountInscritosGroupByCategoriaParticipanteEvento;
import br.jus.tre_pa.seven.repository.inscricao.InscricaoRepositoryQuery;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long>, InscricaoRepositoryQuery {

	@Query("select c from Inscricao c inner join c.evento e where e.id = ?1")
	List<Inscricao> findAllByEventoId(Long idEvento);

	@Query("select p from Inscricao p inner join p.participante e where p.id = ?1")
	Inscricao findOneParticipanteByInscricao(Long idInscricao);
	
	@Query("select p from Inscricao p inner join p.participante e where e.id = ?1")
	List<Inscricao> findAllInscricaoByParticipante(Long idParticipante);
	
	@Query("select count(0) from Inscricao i inner join i.participante p inner join i.evento e where p.id = ?1 and e.id=?2")
	Integer findAllInscricaoByParticipanteEvento(Long idParticipante, Long idEvento);
	
	@Query("select i.categoriaParticipanteEvento.id as categoriaParticipanteEventoId, count(0) as count from Inscricao i where i.evento.id = ?1 group by i.categoriaParticipanteEvento")
	public List<CountInscritosGroupByCategoriaParticipanteEvento> findCountInscritosByCategoriaParticipanteEvento(Long id);
	
	@Query("select count(0) as count from Inscricao i where i.evento.id = ?1 and i.categoriaParticipanteEvento.categoriaParticipante.id= ?2  ")
	int findCountInscritosByCategoriaParticipanteEventoId(Long id , Long idCategoriaParticipanteEvento);

	Inscricao findOneByCodigoQrCode(String uuid);
}
