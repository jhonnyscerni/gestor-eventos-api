package br.jus.tre_pa.seven.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.tre_pa.seven.domain.Frequencia;
import br.jus.tre_pa.seven.domain.Inscricao;
import br.jus.tre_pa.seven.domain.enums.FrequenciaTurno;
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
		
		LocalDateTime dataHoraFrequencia = LocalDateTime.now();
		
		if(dataHoraFrequencia.getHour() >= 0 && dataHoraFrequencia.getHour() <= 11 ) {
			frequencia.setFrequenciaTurno(FrequenciaTurno.MANHA);
		}
		
		if(dataHoraFrequencia.getHour() >= 12 && dataHoraFrequencia.getHour() <= 17 ) {
			frequencia.setFrequenciaTurno(FrequenciaTurno.TARDE);
		}
		
		if(dataHoraFrequencia.getHour() >= 18 ) {
			frequencia.setFrequenciaTurno(FrequenciaTurno.NOITE);
		}
		
		frequencia.setDataFrequencia(dataHoraFrequencia);
	
		
		
		frequencia.setInscricao(inscricao);
		
		return frequenciaRepository.save(frequencia);
		
	}
}
