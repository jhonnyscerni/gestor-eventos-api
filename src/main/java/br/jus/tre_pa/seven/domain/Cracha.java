package br.jus.tre_pa.seven.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cracha")
public class Cracha {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nomeModelo;
	
	@ManyToOne
	private Evento evento;
	
	@Lob
	@Column
	private String imagemTopo;
	
	@Lob
	@Column
	private String imagemRodape;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeModelo() {
		return nomeModelo;
	}

	public void setNomeModelo(String nomeModelo) {
		this.nomeModelo = nomeModelo;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public String getImagemTopo() {
		return imagemTopo;
	}

	public void setImagemTopo(String imagemTopo) {
		this.imagemTopo = imagemTopo;
	}

	public String getImagemRodape() {
		return imagemRodape;
	}

	public void setImagemRodape(String imagemRodape) {
		this.imagemRodape = imagemRodape;
	}
	
	
	

}
