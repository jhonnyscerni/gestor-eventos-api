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

import br.jus.tre_pa.seven.domain.Inscricao;
import br.jus.tre_pa.seven.repository.InscricaoRepository;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoRest {
	
	@Autowired
	private InscricaoRepository inscricaoRepository;
	
	@GetMapping
	public List<Inscricao> listar() {
		return inscricaoRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Inscricao> buscarPeloId(@PathVariable Long id) {
		Inscricao inscricao = inscricaoRepository.findOne(id);
		return inscricao != null ? ResponseEntity.ok(inscricao) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("gerar-cracha/{idInscricao}")
	public ResponseEntity<Inscricao> buscarParticipantePelaInscricao(@PathVariable Long idInscricao) {
		Inscricao inscricao = inscricaoRepository.findOneParticipanteByInscricao(idInscricao);
		return inscricao != null ? ResponseEntity.ok(inscricao) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("gerar-certificado/{idInscricao}")
	public ResponseEntity<Inscricao> findOneCertificadoParticipanteByInscricaoByEvento(@PathVariable Long idInscricao) {
		Inscricao inscricao = inscricaoRepository.findOneParticipanteByInscricao(idInscricao);
		return inscricao != null ? ResponseEntity.ok(inscricao) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		inscricaoRepository.delete(id);
	}


}
