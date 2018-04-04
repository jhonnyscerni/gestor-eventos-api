package br.jus.tre_pa.seven.rest;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.jus.tre_pa.seven.domain.TipoDeEvento;
import br.jus.tre_pa.seven.event.RecursoCriadoEvent;
import br.jus.tre_pa.seven.repository.TipoDeEventoRepository;

@RestController
@RequestMapping("/tipo-evento")
public class TipoDeEventoRest {

	@Autowired
	private TipoDeEventoRepository tipoDeEventoRespository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<TipoDeEvento> listar()
	{
		return tipoDeEventoRespository.findAll();
		
	}
	
	@PostMapping
	public ResponseEntity<TipoDeEvento> criar(@Valid @RequestBody TipoDeEvento tipoDeEvento, HttpServletResponse response) {
		
		TipoDeEvento tipoDeEventoSalvo = tipoDeEventoRespository.save(tipoDeEvento);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(tipoDeEventoSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, tipoDeEventoSalvo.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoDeEventoSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarTipoDeEvento(@PathVariable Long id)
	{
		TipoDeEvento tipoDeEventoRetornado = tipoDeEventoRespository.findOne(id);
		
		return tipoDeEventoRetornado != null ? ResponseEntity.ok(tipoDeEventoRetornado) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id)
	{
		tipoDeEventoRespository.delete(id);
	}
	
}
