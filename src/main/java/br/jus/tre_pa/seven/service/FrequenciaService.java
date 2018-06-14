package br.jus.tre_pa.seven.service;

import java.time.LocalDateTime;
import java.util.List;

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

	private Frequencia frequencia = new Frequencia();

	public Frequencia salvar(String uuid) {
		Inscricao inscricao = inscricaoRespository.findOneByCodigoQrCode(uuid);
		
		List<Frequencia> frequenciaExistente = frequenciaRepository
				.findAllFrequenciaByInscricaoByParticipanteByEnventoParticipante(inscricao.getEvento().getId(),
						inscricao.getId());

		LocalDateTime dataHoraFrequencia = LocalDateTime.now();

		for (Frequencia frequenciaFor : frequenciaExistente) {

				if (dataHoraFrequencia.getHour() >= 0 && dataHoraFrequencia.getHour() <= 11) {
					if (frequenciaFor.getDataFrequencia().getDayOfYear() == dataHoraFrequencia.getDayOfYear()) {
					
						if (frequenciaFor.getFrequenciaTurno() == FrequenciaTurno.TARDE || frequenciaFor.getFrequenciaTurno() == FrequenciaTurno.NOITE) {
							
						}else {
						
					if (frequenciaFor.getFrequenciaTurno() == FrequenciaTurno.MANHA) {
						System.out.println("JA EFETUOU A PRESENCA PELA PARTE DA MANHA");
					} else {

						frequencia.setDataFrequencia(dataHoraFrequencia);
						frequencia.setFrequenciaTurno(FrequenciaTurno.MANHA);
						frequencia.setInscricao(inscricao);
						frequenciaRepository.save(frequencia);

					}
					}
					}
				}

				if (dataHoraFrequencia.getHour() >= 12 && dataHoraFrequencia.getHour() <= 17) {
					if (frequenciaFor.getDataFrequencia().getDayOfYear() == dataHoraFrequencia.getDayOfYear()) {
						
						if (frequenciaFor.getFrequenciaTurno() == FrequenciaTurno.MANHA || frequenciaFor.getFrequenciaTurno() == FrequenciaTurno.NOITE) {
						}else {
						
						if (frequenciaFor.getFrequenciaTurno() == FrequenciaTurno.TARDE) {
								System.out.println("JA EFETUOU A PRESENCA PELA PARTE DA TARDE");
							}else {
								frequencia.setDataFrequencia(dataHoraFrequencia);
								frequencia.setFrequenciaTurno(FrequenciaTurno.TARDE);
								frequencia.setInscricao(inscricao);
								frequenciaRepository.save(frequencia);
							}
						}
					}
						
	
					
				}

				if (dataHoraFrequencia.getHour() >= 18) {
					if (frequenciaFor.getDataFrequencia().getDayOfYear() == dataHoraFrequencia.getDayOfYear()) {
						
					if (frequenciaFor.getFrequenciaTurno() == FrequenciaTurno.MANHA || frequenciaFor.getFrequenciaTurno() == FrequenciaTurno.NOITE) {
							
						}else {
					if (frequenciaFor.getFrequenciaTurno() == FrequenciaTurno.NOITE) {

						System.out.println("JA EFETUOU A PRESENCA PELA PARTE DA NOITE");
					} else {
						frequencia.setDataFrequencia(dataHoraFrequencia);
						frequencia.setFrequenciaTurno(FrequenciaTurno.NOITE);
						frequencia.setInscricao(inscricao);
						frequenciaRepository.save(frequencia);
					}
					}
					}
				}

		}
		return null;

	}
}
