package br.jus.tre_pa.seven.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class EventoFilter {
	
	private String nome;
	
	private String sigla;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate inicioEventoDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate inicioEventoAte;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public LocalDate getInicioEventoDe() {
		return inicioEventoDe;
	}

	public void setInicioEventoDe(LocalDate inicioEventoDe) {
		this.inicioEventoDe = inicioEventoDe;
	}

	public LocalDate getInicioEventoAte() {
		return inicioEventoAte;
	}

	public void setInicioEventoAte(LocalDate inicioEventoAte) {
		this.inicioEventoAte = inicioEventoAte;
	}
	
	
	

}
