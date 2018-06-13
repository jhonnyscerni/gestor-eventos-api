package br.jus.tre_pa.seven.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.tre_pa.seven.domain.Inscricao;
import br.jus.tre_pa.seven.projection.CountInscritosGroupByCategoriaParticipanteEvento;
import br.jus.tre_pa.seven.repository.CategoriaParticipanteEventoRepository;
import br.jus.tre_pa.seven.repository.InscricaoRepository;

@Service
public class InscricaoService {

	@Autowired
	private InscricaoRepository inscricaoRespository;

	@Autowired
	private CategoriaParticipanteEventoRepository categoriaParticipanteEventoRespository;

	public Inscricao salvar(Inscricao inscricao) {
		
		Inscricao inscricaoSalva = null;

		Long inscricaoCategoriaParticipanteId = inscricao.getCategoriaParticipanteEvento().getCategoriaParticipante()
				.getId();

		Long inscricaoEventoId = inscricao.getEvento().getId();

		int qtdVagasPorCategoria = categoriaParticipanteEventoRespository
				.getSumVagasByEventoByCategoriaParticipanteEvento(inscricaoEventoId, inscricaoCategoriaParticipanteId);

		int qtdInscritosPorCategoria = inscricaoRespository
				.findCountInscritosByCategoriaParticipanteEventoId(inscricaoEventoId, inscricaoCategoriaParticipanteId);

		if (qtdVagasPorCategoria > qtdInscritosPorCategoria) {
			UUID uuid = UUID.randomUUID();
			String strUuid = uuid.toString();
			inscricao.setCodigoQrCode(strUuid);

			inscricao.setDtInscricao(LocalDateTime.now());
			inscricaoSalva = inscricaoRespository.save(inscricao);
		} else {
			System.out.println("ERROR -> Numero de Vagas Esgotada pra essa Categoria !!!");
		}
		
		return inscricaoSalva;

	}

	public Inscricao atualizar(Long id, Inscricao inscricao) {
		Inscricao InscricaoSalvo = buscarInscricaoExistente(id);

		BeanUtils.copyProperties(inscricao, InscricaoSalvo, "id");

		return inscricaoRespository.save(InscricaoSalvo);
	}

	public Inscricao buscarInscricaoExistente(Long id) {
		Inscricao inscricaoSalvo = inscricaoRespository.findOne(id);

		if (inscricaoSalvo == null) {
			throw new IllegalArgumentException();
		}

		return inscricaoSalvo;
	}

	public Map<Long, Long> findCountInscritosGroupByCategoriaParticipanteEvento(Long id) {
		return inscricaoRespository.findCountInscritosByCategoriaParticipanteEvento(id).stream()
				.collect(Collectors.toMap(
						CountInscritosGroupByCategoriaParticipanteEvento::getCategoriaParticipanteEventoId,
						CountInscritosGroupByCategoriaParticipanteEvento::getCount));
	}
}
