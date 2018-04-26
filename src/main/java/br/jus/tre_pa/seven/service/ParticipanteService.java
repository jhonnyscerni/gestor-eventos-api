package br.jus.tre_pa.seven.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.tre_pa.seven.domain.Participante;
import br.jus.tre_pa.seven.repository.ParticipanteRepository;

@Service
public class ParticipanteService {

	
	@Autowired
	private ParticipanteRepository participanteRepository;
	
	public Participante salvar(Participante participante){
		return participanteRepository.save(participante);
	}
	
	public Participante atualizar(Long id, Participante participante){
		Participante participanteSalvo = buscarEventoExistente(id);
		
		BeanUtils.copyProperties(participante, participanteSalvo, "id");
		
		return participanteRepository.save(participanteSalvo);
	}
	
	
	public Participante buscarEventoExistente(Long id){
		Participante participanteSalvo = participanteRepository.findOne(id);
		
		if (participanteSalvo == null) {
			throw new IllegalArgumentException();
		}
		
		return participanteSalvo;
	}
}
