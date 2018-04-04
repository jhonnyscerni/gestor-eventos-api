package br.jus.tre_pa.seven.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.tre_pa.seven.domain.Evento;
import br.jus.tre_pa.seven.repository.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;
	
	public Evento salvar(Evento evento){
		return eventoRepository.save(evento);
	}
	
	public Evento atualizar(Long id, Evento evento){
		Evento eventoSalvo = buscarEventoExistente(id);
		
		BeanUtils.copyProperties(evento, eventoSalvo, "id");
		
		return eventoRepository.save(eventoSalvo);
	}
	
	
	public Evento buscarEventoExistente(Long id){
		Evento eventoSalvo = eventoRepository.findOne(id);
		
		if (eventoSalvo == null) {
			throw new IllegalArgumentException();
		}
		
		return eventoSalvo;
	}
}
