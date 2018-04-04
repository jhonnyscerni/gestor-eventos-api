package br.jus.tre_pa.seven.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.tre_pa.seven.domain.Facilitador;
import br.jus.tre_pa.seven.repository.FacilitadorRepository;

@Service
public class FacilitadorService {

	@Autowired
	private FacilitadorRepository facilitadorRepository;
	
	public Facilitador salvar(Facilitador facilitador){
		return facilitadorRepository.save(facilitador);
	}
	
	public Facilitador atualizar(Long id, Facilitador facilitador){
		Facilitador FacilitadorSalvo = buscarFacilitadorExistente(id);
		
		BeanUtils.copyProperties(facilitador, FacilitadorSalvo, "id");
		
		return facilitadorRepository.save(FacilitadorSalvo);
	}
	
	
	public Facilitador buscarFacilitadorExistente(Long id){
		Facilitador facilitadorSalvo = facilitadorRepository.findOne(id);
		
		if (facilitadorSalvo == null) {
			throw new IllegalArgumentException();
		}
		
		return facilitadorSalvo;
	}
}
