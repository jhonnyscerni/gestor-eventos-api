package br.jus.tre_pa.seven.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.tre_pa.seven.domain.CategoriaParticipante;
import br.jus.tre_pa.seven.repository.CategoriaParticipanteRepository;

@RestController
@RequestMapping("/categoria-participante")
public class CategoriaParticipanteRest {

	@Autowired
	private CategoriaParticipanteRepository categoriaParticipanteRepository;
	
	@GetMapping
	public List<CategoriaParticipante> listar() {
		return categoriaParticipanteRepository.findAll();
	}
}
