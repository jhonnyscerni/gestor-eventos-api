package br.jus.tre_pa.seven.repository.filter;

import br.jus.tre_pa.seven.domain.Participante;

public class InscricaoFilter {
	
	private Participante participante = new Participante();
	private String codigoCertificado;



	public String getCodigoCertificado() {
		return codigoCertificado;
	}

	public void setCodigoCertificado(String codigoCertificado) {
		this.codigoCertificado = codigoCertificado;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}


}
