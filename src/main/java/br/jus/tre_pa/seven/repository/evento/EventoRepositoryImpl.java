package br.jus.tre_pa.seven.repository.evento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.jus.tre_pa.seven.domain.Evento;
import br.jus.tre_pa.seven.repository.filter.EventoFilter;

public class EventoRepositoryImpl implements EventoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Evento> filtrar(EventoFilter eventoFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Evento> criteria = builder.createQuery(Evento.class);
		Root<Evento> root = criteria.from(Evento.class);

		// Criar as Restrições
		Predicate[] predicates = criarRestricoes(eventoFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Evento> query = manager.createQuery(criteria);

		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(eventoFilter));
	}

	private Long total(EventoFilter eventoFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Evento> root = criteria.from(Evento.class);

		Predicate[] predicates = criarRestricoes(eventoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Evento> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);

	}

	private Predicate[] criarRestricoes(EventoFilter eventoFilter, CriteriaBuilder builder, Root<Evento> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(eventoFilter.getNome())) {
			predicates.add(
					builder.like(builder.lower(root.get("nome")), "%" + eventoFilter.getNome().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(eventoFilter.getSigla())) {
			predicates.add(
					builder.like(builder.lower(root.get("sigla")), "%" + eventoFilter.getSigla().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(eventoFilter.getTipoDeEvento().getNomeSingular())) {
			predicates.add(
					builder.like(builder.lower(root.get("tipoEvento")), "%" + eventoFilter.getTipoDeEvento().getNomeSingular().toLowerCase() + "%"));
		}

		if (eventoFilter.getInicioEventoDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("inicioEvento"), eventoFilter.getInicioEventoDe()));
		}

		if (eventoFilter.getInicioEventoAte() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("inicioEvento"), eventoFilter.getInicioEventoAte()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);

	}
}
