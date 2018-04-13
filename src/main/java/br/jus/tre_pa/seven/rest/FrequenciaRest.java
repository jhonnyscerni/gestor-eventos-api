package br.jus.tre_pa.seven.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.tre_pa.seven.domain.Frequencia;
import br.jus.tre_pa.seven.service.FrequenciaService;

@RestController
@RequestMapping("/frequencia")
public class FrequenciaRest {
	
	@Autowired
	private FrequenciaService frequenciaService;

	@PostMapping("/create/{uuid}")
	public Frequencia criar(@PathVariable String uuid) {
		return frequenciaService.salvar(uuid);
	}
}
