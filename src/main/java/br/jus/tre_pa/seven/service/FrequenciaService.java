package br.jus.tre_pa.seven.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.tre_pa.seven.domain.Frequencia;
import br.jus.tre_pa.seven.domain.Inscricao;
import br.jus.tre_pa.seven.repository.FrequenciaRepository;
import br.jus.tre_pa.seven.repository.InscricaoRepository;

@Service
public class FrequenciaService {

	@Autowired
	private InscricaoRepository inscricaoRespository;
	
	@Autowired
	private FrequenciaRepository frequenciaRepository;
	
	public Frequencia salvar(String uuid) {
		Inscricao inscricao = inscricaoRespository.findOneByCodigoQrCode(uuid);
		Frequencia frequencia = new Frequencia();
		frequencia.setDataFrequencia(new Date());
		frequencia.setInscricao(inscricao);
		
		return frequenciaRepository.save(frequencia);
		
	}
}
