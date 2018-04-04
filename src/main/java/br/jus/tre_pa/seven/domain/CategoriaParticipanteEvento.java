package br.jus.tre_pa.seven.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cat_participante_evento")
public class CategoriaParticipanteEvento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long vagas;

	@Column
	private Long espera;

	@ManyToOne
	private Evento evento;
	
	@ManyToOne
	private CategoriaParticipante categoriaParticipante;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVagas() {
		return vagas;
	}

	public void setVagas(Long vagas) {
		this.vagas = vagas;
	}

	public Long getEspera() {
		return espera;
	}

	public void setEspera(Long espera) {
		this.espera = espera;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	public CategoriaParticipante getCategoriaParticipante() {
		return categoriaParticipante;
	}

	public void setCategoriaParticipante(CategoriaParticipante categoriaParticipante) {
		this.categoriaParticipante = categoriaParticipante;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaParticipanteEvento other = (CategoriaParticipanteEvento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	

}
