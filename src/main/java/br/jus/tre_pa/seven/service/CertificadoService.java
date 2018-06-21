package br.jus.tre_pa.seven.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.jus.tre_pa.seven.domain.Certificado;
import br.jus.tre_pa.seven.repository.CertificadoRepository;
import br.jus.tre_pa.seven.storage.StorageService;

@Service
public class CertificadoService {

	@Autowired
	private CertificadoRepository certificadoRepository;
	
	@Autowired
	private StorageService storageService;
	
	public Certificado salvar(Certificado certificado){
		
		if(StringUtils.hasText(certificado.getImagem()))
		{
//			certificado.setImagem(storageService.));
		}
		
		return certificadoRepository.save(certificado);
	}
	
	public Certificado atualizar(Long id, Certificado certificado){
		Certificado certificadoSalvo = buscarCertificadoExistente(id);
		
		BeanUtils.copyProperties(certificado, certificadoSalvo, "id");
		
		return certificadoRepository.save(certificadoSalvo);
	}
	
	
	public Certificado buscarCertificadoExistente(Long id){
		Certificado certificadoSalvo = certificadoRepository.findOne(id);
		
		if (certificadoSalvo == null) {
			throw new IllegalArgumentException();
		}
		
		return certificadoSalvo;
	}
}
