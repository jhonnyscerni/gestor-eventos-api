package br.jus.tre_pa.seven.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.jus.tre_pa.seven.domain.enums.StatusInscricao;

@Entity
@Table(name = "inscricao")
public class Inscricao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtInscricao;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtDeferimento;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCertificado;
	
	@Enumerated(EnumType.STRING)
	private StatusInscricao status;
	
	@Column
	private Boolean certificadoLiberado;
	
	@Column
	private String justificativaNaoCertificado;
	
	@Column
	private String justificativaNaoDeferimento;
	
	@Column
	private String codigoCertificado;
	
	@Column
	private String codigoCracha;
	
	@Column
	private String observacao;
	
	@ManyToOne
//	@JsonIgnoreProperties("inscricoes")
	private Evento evento;

	@ManyToOne(targetEntity = Participante.class)
	private Participante participante;
	
	@OneToMany(cascade = CascadeType.ALL)
	@BatchSize(size = 10)
	@JsonProperty("values")
	@JoinColumn(name="EVENTO_ID")
	private List<EventoAttrValue> eventoAttrValues;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDtInscricao() {
		return dtInscricao;
	}

	public void setDtInscricao(Date dtInscricao) {
		this.dtInscricao = dtInscricao;
	}

	public Date getDtDeferimento() {
		return dtDeferimento;
	}

	public void setDtDeferimento(Date dtDeferimento) {
		this.dtDeferimento = dtDeferimento;
	}

	public Date getDtCertificado() {
		return dtCertificado;
	}

	public void setDtCertificado(Date dtCertificado) {
		this.dtCertificado = dtCertificado;
	}

	public StatusInscricao getStatus() {
		return status;
	}

	public void setStatus(StatusInscricao status) {
		this.status = status;
	}

	public Boolean getCertificadoLiberado() {
		return certificadoLiberado;
	}

	public void setCertificadoLiberado(Boolean certificadoLiberado) {
		this.certificadoLiberado = certificadoLiberado;
	}

	public String getJustificativaNaoCertificado() {
		return justificativaNaoCertificado;
	}

	public void setJustificativaNaoCertificado(String justificativaNaoCertificado) {
		this.justificativaNaoCertificado = justificativaNaoCertificado;
	}

	public String getJustificativaNaoDeferimento() {
		return justificativaNaoDeferimento;
	}

	public void setJustificativaNaoDeferimento(String justificativaNaoDeferimento) {
		this.justificativaNaoDeferimento = justificativaNaoDeferimento;
	}

	public String getCodigoCertificado() {
		return codigoCertificado;
	}

	public void setCodigoCertificado(String codigoCertificado) {
		this.codigoCertificado = codigoCertificado;
	}

	public String getCodigoCracha() {
		return codigoCracha;
	}

	public void setCodigoCracha(String codigoCracha) {
		this.codigoCracha = codigoCracha;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
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
		Inscricao other = (Inscricao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<EventoAttrValue> getEventoAttrValues() {
		return eventoAttrValues;
	}

	public void setEventoAttrValues(List<EventoAttrValue> eventoAttrValues) {
		this.eventoAttrValues = eventoAttrValues;
	}
	
	

}
