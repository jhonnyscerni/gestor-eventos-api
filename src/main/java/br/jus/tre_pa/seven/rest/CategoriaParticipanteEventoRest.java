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

import br.jus.tre_pa.seven.domain.CategoriaParticipanteEvento;
import br.jus.tre_pa.seven.repository.CategoriaParticipanteEventoRepository;

@RestController
@RequestMapping("/categoria-participante-evento")
public class CategoriaParticipanteEventoRest {

	@Autowired
	private CategoriaParticipanteEventoRepository categoriaParticipanteEventoRepository;

	@GetMapping
	public List<CategoriaParticipanteEvento> listar() {
		return categoriaParticipanteEventoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaParticipanteEvento> buscarPeloId(@PathVariable Long id)
	{
		CategoriaParticipanteEvento categoriaParticipanteEvento = categoriaParticipanteEventoRepository.findOne(id);
		return categoriaParticipanteEvento != null ? ResponseEntity.ok(categoriaParticipanteEvento) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		categoriaParticipanteEventoRepository.delete(id);
	}
	
}
