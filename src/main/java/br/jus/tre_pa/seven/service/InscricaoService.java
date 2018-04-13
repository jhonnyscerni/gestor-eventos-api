package br.jus.tre_pa.seven.service;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.tre_pa.seven.domain.Inscricao;
import br.jus.tre_pa.seven.repository.InscricaoRepository;

@Service
public class InscricaoService {

	@Autowired
	private InscricaoRepository inscricaoRespository;

	public Inscricao salvar(Inscricao inscricao) {
		UUID uuid = UUID.randomUUID();
		String strUuid = uuid.toString();
		inscricao.setCodigoQrCode(strUuid);
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
}
