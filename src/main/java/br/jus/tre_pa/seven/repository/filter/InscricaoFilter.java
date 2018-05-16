package br.jus.tre_pa.seven.repository.filter;

import br.jus.tre_pa.seven.domain.CategoriaParticipante;
import br.jus.tre_pa.seven.domain.Participante;

public class InscricaoFilter {
	
	private Participante participante = new Participante();
	private CategoriaParticipante categoriaParticipante = new CategoriaParticipante();
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

	public CategoriaParticipante getCategoriaParticipante() {
		return categoriaParticipante;
	}

	public void setCategoriaParticipante(CategoriaParticipante categoriaParticipante) {
		this.categoriaParticipante = categoriaParticipante;
	}



}
