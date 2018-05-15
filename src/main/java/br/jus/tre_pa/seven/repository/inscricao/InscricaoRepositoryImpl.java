package br.jus.tre_pa.seven.repository.inscricao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.jus.tre_pa.seven.domain.Evento;
import br.jus.tre_pa.seven.domain.Inscricao;
import br.jus.tre_pa.seven.domain.Participante;
import br.jus.tre_pa.seven.repository.filter.InscricaoFilter;

public class InscricaoRepositoryImpl implements InscricaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Inscricao> filtrar(Long idEvento, InscricaoFilter inscricaoFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Inscricao> criteria = builder.createQuery(Inscricao.class);
		Root<Inscricao> root = criteria.from(Inscricao.class);

		// Criar as Restrições
		Predicate[] predicates = criarRestricoes(idEvento, inscricaoFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Inscricao> query = manager.createQuery(criteria);

		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(idEvento,inscricaoFilter));
	}

	private Long total(Long idEvento, InscricaoFilter inscricaoFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Inscricao> root = criteria.from(Inscricao.class);

		Predicate[] predicates = criarRestricoes(idEvento, inscricaoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Inscricao> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);

	}

	private Predicate[] criarRestricoes(Long idEvento, InscricaoFilter inscricaoFilter, CriteriaBuilder builder, Root<Inscricao> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		Join<Inscricao, Evento> joinEvento = root.join("evento", JoinType.INNER);

		predicates.add(builder.equal(joinEvento.get("id"), idEvento));
		
		Join<Inscricao, Participante> joinParticipante = root.join("participante", JoinType.INNER);
		
		if (!StringUtils.isEmpty(inscricaoFilter.getParticipante().getNome())) {
			predicates.add(
					builder.like(builder.lower(joinParticipante.get("nome")), "%" + inscricaoFilter.getParticipante().getNome().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(inscricaoFilter.getParticipante().getNomeCracha())) {
			predicates.add(
					builder.like(builder.lower(joinParticipante.get("nomeCracha")), "%" + inscricaoFilter.getParticipante().getNomeCracha().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(inscricaoFilter.getParticipante().getCpf())) {
			predicates.add(
					builder.like(builder.lower(joinParticipante.get("cpf")), "%" + inscricaoFilter.getParticipante().getCpf().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);

	}
}
