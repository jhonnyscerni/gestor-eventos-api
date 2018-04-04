package br.jus.tre_pa.seven.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.tre_pa.seven.domain.CategoriaParticipanteEvento;
import br.jus.tre_pa.seven.repository.CategoriaParticipanteEventoRepository;

@Service
public class CategoriaParticipanteEventoService {
	
	@Autowired
	private CategoriaParticipanteEventoRepository categoriaParticipanteEventoRepository;
	
	public CategoriaParticipanteEvento salvar(CategoriaParticipanteEvento categoriParticipanteEvento){
		return categoriaParticipanteEventoRepository.save(categoriParticipanteEvento);
	}
	
	
	public CategoriaParticipanteEvento atualizar(Long id, CategoriaParticipanteEvento categoriParticipanteEvento){
		CategoriaParticipanteEvento categoriParticipanteEventoSalvo = buscarCategoriaParticipanteEventoExistente(id);
		
		BeanUtils.copyProperties(categoriParticipanteEvento, categoriParticipanteEventoSalvo, "id");
		
		return categoriaParticipanteEventoRepository.save(categoriParticipanteEventoSalvo);
	}
	
	public CategoriaParticipanteEvento buscarCategoriaParticipanteEventoExistente(Long id){
		CategoriaParticipanteEvento categoriParticipanteEventoSalvo = categoriaParticipanteEventoRepository.findOne(id);
		
		if (categoriParticipanteEventoSalvo == null) {
			throw new IllegalArgumentException();
		}
		
		return categoriParticipanteEventoSalvo;
	}

}
