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
import br.jus.tre_pa.seven.repository.InscricaoRepository;

@Service
public class InscricaoService {

	@Autowired
	private InscricaoRepository inscricaoRespository;

	public Inscricao salvar(Inscricao inscricao) {
		UUID uuid = UUID.randomUUID();
		String strUuid = uuid.toString();
		inscricao.setCodigoQrCode(strUuid);
		
		inscricao.setDtInscricao(LocalDateTime.now());
		return inscricaoRespository.save(inscricao);
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
	
	public Map<Long, Long> findCountInscritosGroupByCategoriaParticipanteEvento(Long id){
		return inscricaoRespository.findCountInscritosByCategoriaParticipanteEvento(id).stream()
				.collect(Collectors.toMap(CountInscritosGroupByCategoriaParticipanteEvento::getCategoriaParticipanteEventoId, CountInscritosGroupByCategoriaParticipanteEvento::getCount));
	}
}
