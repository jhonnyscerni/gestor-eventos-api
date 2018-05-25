package br.jus.tre_pa.seven.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.jus.tre_pa.seven.domain.Inscricao;
import br.jus.tre_pa.seven.mail.Mailer;
import br.jus.tre_pa.seven.repository.InscricaoRepository;
import net.glxn.qrgen.javase.QRCode;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoRest {
	
	@Autowired
	private InscricaoRepository inscricaoRepository;
	
	
	@Autowired
	private Mailer mailer;
	
	@GetMapping
	public List<Inscricao> listar() {
		return inscricaoRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Inscricao> buscarPeloId(@PathVariable Long id) {
		Inscricao inscricao = inscricaoRepository.findOne(id);
		return inscricao != null ? ResponseEntity.ok(inscricao) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("minhas-inscricoes")
	public List<Inscricao> buscarInscricoesDoParticipante(@RequestParam Long idParticipante) {
		return inscricaoRepository.findAllInscricaoByParticipante(idParticipante);
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
	
	@GetMapping("gerar-qrcode/{id}")
	public ResponseEntity<byte[]> qr(@PathVariable Long id) {

		Inscricao inscricao = inscricaoRepository.findOne(id);
		String template = "mail/evento";
			
		byte[] bytes = QRCode.from(inscricao.getCodigoQrCode().toString()).withSize(120, 120).stream()
				.toByteArray();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		headers.setContentLength(bytes.length);
		
		Map<String, Object> variaveis = new HashMap<>();
		variaveis.put("inscricao", inscricao);
		variaveis.put("qrcoe", headers);

		mailer.enviarEmail("jhonnyscerni", Arrays.asList("siberiusapp@gmail.com"), "TESTE", template, variaveis);
		
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
		

	}

	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		inscricaoRepository.delete(id);
	}


}
