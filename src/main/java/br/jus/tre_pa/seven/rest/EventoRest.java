package br.jus.tre_pa.seven.rest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.jus.tre_pa.seven.domain.CategoriaParticipanteEvento;
import br.jus.tre_pa.seven.domain.Certificado;
import br.jus.tre_pa.seven.domain.Evento;
import br.jus.tre_pa.seven.domain.Facilitador;
import br.jus.tre_pa.seven.domain.Frequencia;
import br.jus.tre_pa.seven.domain.Inscricao;
import br.jus.tre_pa.seven.event.RecursoCriadoEvent;
import br.jus.tre_pa.seven.repository.CategoriaParticipanteEventoRepository;
import br.jus.tre_pa.seven.repository.CertificadoRepository;
import br.jus.tre_pa.seven.repository.EventoRepository;
import br.jus.tre_pa.seven.repository.FacilitadorRepository;
import br.jus.tre_pa.seven.repository.FrequenciaRepository;
import br.jus.tre_pa.seven.repository.InscricaoRepository;
import br.jus.tre_pa.seven.repository.filter.EventoFilter;
import br.jus.tre_pa.seven.service.CategoriaParticipanteEventoService;
import br.jus.tre_pa.seven.service.CertificadoService;
import br.jus.tre_pa.seven.service.EventoService;
import br.jus.tre_pa.seven.service.FacilitadorService;
import br.jus.tre_pa.seven.service.InscricaoService;

@RestController
@RequestMapping("/eventos")
public class EventoRest {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private EventoService eventoService;

	@Autowired
	private FacilitadorService facilitadorService;
	
	@Autowired
	private InscricaoService inscricaoService;


	@Autowired
	private CategoriaParticipanteEventoService categoriaParticipanteEventoService;
	
	@Autowired
	private CertificadoService certificadoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private CategoriaParticipanteEventoRepository categoriaParticipanteEventoRepository;

	@Autowired
	private FacilitadorRepository facilitadorRepository;
	
	@Autowired
	private InscricaoRepository inscricaoRepository;
	
	@Autowired
	private FrequenciaRepository frequenciaRepository;
	
	@Autowired
	private CertificadoRepository certificadoRepository;
	

	@PostMapping("/anexo-imagem")
	public String uploadAnexoEvento(@RequestParam MultipartFile anexo) throws IOException {
		OutputStream out = new FileOutputStream(
				"/home/jhonnyscerni/Desktop/anexo--" + anexo.getOriginalFilename());
		out.write(anexo.getBytes());
		out.close();
		return "ok";
	}
	

	@GetMapping
	public Page<Evento> pesquisar(EventoFilter eventoFilter, Pageable pageable) {
		return eventoRepository.filtrar(eventoFilter, pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Evento> buscarPeloId(@PathVariable Long id) {
		Evento evento = eventoRepository.findOne(id);
		return evento != null ? ResponseEntity.ok(evento) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Evento> criar(@Valid @RequestBody Evento evento, HttpServletResponse response) {
		Evento eventoSalvo = eventoService.salvar(evento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, eventoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(eventoSalvo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		eventoRepository.delete(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Evento> atualizar(@PathVariable Long id, @Valid @RequestBody Evento evento) {

		try {
			Evento eventoSalvo = eventoService.atualizar(id, evento);
			return ResponseEntity.ok(eventoSalvo);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * CATEGORIA PARTICIPANTE
	 */

	@PostMapping("/{id}/categoria-participante-evento")
	public ResponseEntity<CategoriaParticipanteEvento> criar(@PathVariable Long id,
			@Valid @RequestBody CategoriaParticipanteEvento categoriaParticipanteEvento, HttpServletResponse response) {
		CategoriaParticipanteEvento categoriaParticipanteEventoSalvo = categoriaParticipanteEventoService
				.salvar(categoriaParticipanteEvento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaParticipanteEventoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaParticipanteEventoSalvo);
	}

	@PutMapping("/{id}/categoria-participante-evento/{idCategoriaParticipanteEvento}")
	public ResponseEntity<CategoriaParticipanteEvento> atualizar(@PathVariable Long id,
			@PathVariable Long idCategoriaParticipanteEvento,
			@Valid @RequestBody CategoriaParticipanteEvento categoriaParticipanteEvento) {

		try {
			CategoriaParticipanteEvento categoriaParticipanteEventoSalvo = categoriaParticipanteEventoService
					.atualizar(idCategoriaParticipanteEvento, categoriaParticipanteEvento);
			return ResponseEntity.ok(categoriaParticipanteEventoSalvo);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{idEvento}/categoria-participante-evento")
	public List<CategoriaParticipanteEvento> findCategoriaParticipanteEventobyEvento(@PathVariable Long idEvento) {
		return this.categoriaParticipanteEventoRepository.findAllByEventoId(idEvento);
	}

	/*
	 * FACILITADOR
	 */

	@PostMapping("/{idEvento}/facilitador")
	public ResponseEntity<Facilitador> criar(@PathVariable Long idEvento, @Valid @RequestBody Facilitador facilitador,
			HttpServletResponse response) {
		Facilitador facilitadorSalvo = facilitadorService.salvar(facilitador);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, facilitadorSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(facilitadorSalvo);
	}

	@PutMapping("/{idEvento}/facilitador/{idFacilitador}")
	public ResponseEntity<Facilitador> atualizar(@PathVariable Long idEvento, @PathVariable Long idFacilitador,
			@Valid @RequestBody Facilitador facilitador, HttpServletResponse response) {
		try {
			Facilitador facilitadorSalvo = facilitadorService.atualizar(idFacilitador, facilitador);
			return ResponseEntity.ok(facilitadorSalvo);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{idEvento}/facilitadores")
	public List<Facilitador> findFacilitadoresbyEvento(@PathVariable Long idEvento) {
		return this.facilitadorRepository.findAllByEventoId(idEvento);
	}
	
	/*
	 * INSCRICAO
	 * 
	 */
	
	@PostMapping("/{idEvento}/inscricao")
	public ResponseEntity<Inscricao> criar(@PathVariable Long idEvento, @Valid @RequestBody Inscricao inscricao,
			HttpServletResponse response) {
		Inscricao inscricaoSalvo = inscricaoService.salvar(inscricao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, inscricaoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(inscricaoSalvo);
	}
	
	@PutMapping("/{idEvento}/inscricao/{idInscricao}")
	public ResponseEntity<Inscricao> atualizar(@PathVariable Long idEvento, @PathVariable Long idInscricao,
			@Valid @RequestBody Inscricao inscricao, HttpServletResponse response) {
		try {
			Inscricao inscricaoSalvo = inscricaoService.atualizar(idInscricao, inscricao);
			return ResponseEntity.ok(inscricaoSalvo);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{idEvento}/inscricoes")
	public List<Inscricao> findIncricoesByEvento(@PathVariable Long idEvento) {
		return this.inscricaoRepository.findAllByEventoId(idEvento);
	}
	
	/*
	 * FREQUENCIA
	 * 
	 */
	
	@GetMapping("/{idEvento}/frequencia")
	public List<Frequencia> findAllFrequenciaByInscricaoByParticipanteByEnvento(@PathVariable Long idEvento) {
		return this.frequenciaRepository.findAllFrequenciaByInscricaoByParticipanteByEnvento(idEvento);
	}
	
	
	/*
	 * CERTIFICADO
	 */

	@PostMapping("/{idEvento}/certificado")
	public ResponseEntity<Certificado> criar(@PathVariable Long idEvento, @Valid @RequestBody Certificado certificado,
			HttpServletResponse response) {
		Certificado certificadoSalvo = certificadoService.salvar(certificado);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, certificadoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(certificadoSalvo);
	}

	@PutMapping("/{idEvento}/certificado/{idCertificado}")
	public ResponseEntity<Certificado> atualizar(@PathVariable Long idEvento, @PathVariable Long idCertificado,
			@Valid @RequestBody Certificado certificado, HttpServletResponse response) {
		try {
			Certificado certificadoSalvo = certificadoService.atualizar(idCertificado, certificado);
			return ResponseEntity.ok(certificadoSalvo);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{idEvento}/certificados")
	public List<Certificado> findCertificadosbyEvento(@PathVariable Long idEvento) {
		return this.certificadoRepository.findAllByEventoId(idEvento);
	}
}
