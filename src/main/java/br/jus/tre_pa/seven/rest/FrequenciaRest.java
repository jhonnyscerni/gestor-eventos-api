package br.jus.tre_pa.seven.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.jus.tre_pa.seven.domain.Frequencia;
import br.jus.tre_pa.seven.exception.FrequenciaException;
import br.jus.tre_pa.seven.repository.FrequenciaRepository;
import br.jus.tre_pa.seven.service.FrequenciaService;

@RestController
@RequestMapping("/frequencia")
public class FrequenciaRest {
	
	@Autowired
	private FrequenciaService frequenciaService;
	
	@Autowired
	private FrequenciaRepository frequenciaRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public List<Frequencia> listar() {
		return frequenciaRepository.findAll();
	}

	@PostMapping("/create/{uuid}")
	public Frequencia criar(@PathVariable String uuid) {
		return frequenciaService.salvar(uuid);
	}
	
	@ExceptionHandler(FrequenciaException.class)
	public ResponseEntity<Object> handlerValidaFrequencia(FrequenciaException ex) {
		String mensagemUsuario = messageSource.getMessage("frequencia.registrada", null, LocaleContextHolder.getLocale());
		return ResponseEntity.badRequest().body(mensagemUsuario);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		frequenciaRepository.delete(id);
	}
}
