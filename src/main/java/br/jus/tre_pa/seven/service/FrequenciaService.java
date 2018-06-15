package br.jus.tre_pa.seven.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.tre_pa.seven.domain.Frequencia;
import br.jus.tre_pa.seven.domain.Inscricao;
import br.jus.tre_pa.seven.domain.enums.FrequenciaTurno;
import br.jus.tre_pa.seven.exception.FrequenciaException;
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

		Frequencia frequenciaHora = new Frequencia();

		LocalDateTime dataHoraFrequencia = LocalDateTime.now();
		LocalDate dataAtualFrequencia = LocalDate.now();

		if (dataHoraFrequencia.getHour() >= 0 && dataHoraFrequencia.getHour() <= 11) {
			frequenciaHora.setFrequenciaTurno(FrequenciaTurno.MANHA);
		}
		if (dataHoraFrequencia.getHour() >= 12 && dataHoraFrequencia.getHour() <= 17) {
			frequenciaHora.setFrequenciaTurno(FrequenciaTurno.TARDE);
		}
		if (dataHoraFrequencia.getHour() >= 18) {
			frequenciaHora.setFrequenciaTurno(FrequenciaTurno.NOITE);
		}

		List<Frequencia> frequenciaExistente = frequenciaRepository
				.findAllFrequenciaByInscricaoByParticipanteByEnventoParticipante(inscricao.getEvento().getId(),
						inscricao.getId(), frequenciaHora.getFrequenciaTurno(), dataAtualFrequencia);

		if (!frequenciaExistente.isEmpty()) {
			for (Frequencia frequenciaFor : frequenciaExistente) {

				if (dataHoraFrequencia.getHour() >= 0 && dataHoraFrequencia.getHour() <= 11) {

					if (frequenciaFor.getFrequenciaTurno() == FrequenciaTurno.MANHA) {
						System.out.println("JA EFETUOU A PRESENCA PELA PARTE DA MANHA");
						throw new FrequenciaException();
					} else {

						frequencia.setDataFrequencia(dataHoraFrequencia);
						frequencia.setFrequenciaTurno(FrequenciaTurno.MANHA);
						frequencia.setInscricao(inscricao);
						frequenciaRepository.save(frequencia);

					}
				}

				if (dataHoraFrequencia.getHour() >= 12 && dataHoraFrequencia.getHour() <= 17) {

					if (frequenciaFor.getFrequenciaTurno() == FrequenciaTurno.TARDE) {
						System.out.println("JA EFETUOU A PRESENCA PELA PARTE DA TARDE");
						throw new FrequenciaException();
					} else {
						frequencia.setDataFrequencia(dataHoraFrequencia);
						frequencia.setFrequenciaTurno(FrequenciaTurno.TARDE);
						frequencia.setInscricao(inscricao);
						frequenciaRepository.save(frequencia);
					}

				}

				if (dataHoraFrequencia.getHour() >= 18) {

					if (frequenciaFor.getFrequenciaTurno() == FrequenciaTurno.NOITE) {
						System.out.println("JA EFETUOU A PRESENCA PELA PARTE DA NOITE");
						throw new FrequenciaException();
					} else {
						frequencia.setDataFrequencia(dataHoraFrequencia);
						frequencia.setFrequenciaTurno(FrequenciaTurno.NOITE);
						frequencia.setInscricao(inscricao);
						frequenciaRepository.save(frequencia);
					}
				}

			}

		} else {

			if (dataHoraFrequencia.getHour() >= 0 && dataHoraFrequencia.getHour() <= 11) {
				frequencia.setDataFrequencia(dataHoraFrequencia);
				frequencia.setFrequenciaTurno(FrequenciaTurno.MANHA);
				frequencia.setInscricao(inscricao);
				frequenciaRepository.save(frequencia);
			}

			if (dataHoraFrequencia.getHour() >= 12 && dataHoraFrequencia.getHour() <= 17) {
				frequencia.setDataFrequencia(dataHoraFrequencia);
				frequencia.setFrequenciaTurno(FrequenciaTurno.TARDE);
				frequencia.setInscricao(inscricao);
				frequenciaRepository.save(frequencia);
			}

			if (dataHoraFrequencia.getHour() >= 18) {
				frequencia.setDataFrequencia(dataHoraFrequencia);
				frequencia.setFrequenciaTurno(FrequenciaTurno.NOITE);
				frequencia.setInscricao(inscricao);
				frequenciaRepository.save(frequencia);
			}

		}

		return null;

	}
}
