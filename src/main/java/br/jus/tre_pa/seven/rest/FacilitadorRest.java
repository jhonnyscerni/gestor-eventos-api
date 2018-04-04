package br.jus.tre_pa.seven.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.jus.tre_pa.seven.domain.Facilitador;
import br.jus.tre_pa.seven.repository.FacilitadorRepository;

@RestController
@RequestMapping("/facilitadores")
public class FacilitadorRest {
	
	@Autowired
	private FacilitadorRepository facilitadorRepository;
	
	@GetMapping
	public List<Facilitador> listar() {
		return facilitadorRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Facilitador> buscarPeloId(@PathVariable Long id) {
		Facilitador facilitador = facilitadorRepository.findOne(id);
		return facilitador != null ? ResponseEntity.ok(facilitador) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		facilitadorRepository.delete(id);
	}

}
