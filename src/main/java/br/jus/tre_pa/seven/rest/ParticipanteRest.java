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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.jus.tre_pa.seven.domain.Participante;
import br.jus.tre_pa.seven.event.RecursoCriadoEvent;
import br.jus.tre_pa.seven.repository.ParticipanteRepository;
import br.jus.tre_pa.seven.service.ParticipanteService;

@RestController
@RequestMapping("/participantes")
public class ParticipanteRest {
	
	@Autowired
	private ParticipanteRepository participanteRespository;
	
	@Autowired
	private ParticipanteService participanteService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Participante> listar()
	{
		return participanteRespository.findAll();
		
	}
	
	@PostMapping
	public ResponseEntity<Participante> criar(@Valid @RequestBody Participante participante, HttpServletResponse response) {
		
		Participante participanteSalvo = participanteService.salvar(participante);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(participanteSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, participanteSalvo.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(participanteSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Participante> atualizar(@PathVariable Long id, @Valid @RequestBody Participante participante) {

		try {
			Participante participanteSalvo = participanteService.atualizar(id, participante);
			return ResponseEntity.ok(participanteSalvo);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarTipoDeParticipante(@PathVariable Long id)
	{
		Participante participanteRetornado = participanteRespository.findOne(id);
		
		return participanteRetornado != null ? ResponseEntity.ok(participanteRetornado) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id)
	{
		participanteRespository.delete(id);
	}

}
