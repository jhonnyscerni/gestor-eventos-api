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

import br.jus.tre_pa.seven.domain.Certificado;
import br.jus.tre_pa.seven.repository.CertificadoRepository;

@RestController
@RequestMapping("/certificados")
public class CertificadoRest {

	
	@Autowired
	private CertificadoRepository certificadoRepository;
	
	@GetMapping
	public List<Certificado> listar() {
		return certificadoRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Certificado> buscarPeloId(@PathVariable Long id) {
		Certificado certificado = certificadoRepository.findOne(id);
		return certificado != null ? ResponseEntity.ok(certificado) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		certificadoRepository.delete(id);
	}
}
