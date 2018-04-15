package br.jus.tre_pa.seven.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.jus.tre_pa.seven.domain.enums.FrequenciaTurno;

@Entity
public class Frequencia implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private LocalDateTime dataFrequencia;
	
	@Column
	@Enumerated(EnumType.STRING)
	private FrequenciaTurno frequenciaTurno;
	
	@ManyToOne
	private Inscricao inscricao;


	public Inscricao getInscricao() {
		return inscricao;
	}

	public void setInscricao(Inscricao inscricao) {
		this.inscricao = inscricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		Frequencia other = (Frequencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public LocalDateTime getDataFrequencia() {
		return dataFrequencia;
	}

	public void setDataFrequencia(LocalDateTime dataFrequencia) {
		this.dataFrequencia = dataFrequencia;
	}

	public FrequenciaTurno getFrequenciaTurno() {
		return frequenciaTurno;
	}

	public void setFrequenciaTurno(FrequenciaTurno frequenciaTurno) {
		this.frequenciaTurno = frequenciaTurno;
	}

	
}
