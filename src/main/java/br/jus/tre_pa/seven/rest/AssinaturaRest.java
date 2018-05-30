package br.jus.tre_pa.seven.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.tre_pa.seven.domain.Assinatura;
import br.jus.tre_pa.seven.repository.AssinaturaRepository;

@RestController
@RequestMapping("/assinaturas")
public class AssinaturaRest {

	@Autowired
	private AssinaturaRepository assinaturaRepository;
	
	@GetMapping
	public List<Assinatura> listar() {
		return assinaturaRepository.findAll();
	}
}
