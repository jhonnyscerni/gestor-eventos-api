package br.jus.tre_pa.seven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.jus.tre_pa.seven.domain.Cracha;
import br.jus.tre_pa.seven.repository.CrachaRepository;

@Service
public class CrachaService {
	
	@Autowired
	private CrachaRepository crachaRepository;
	
public Cracha salvar(Cracha cracha){
		
		return crachaRepository.save(cracha);
	}

}
