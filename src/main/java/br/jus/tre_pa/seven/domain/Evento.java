package br.jus.tre_pa.seven.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.jus.tre_pa.seven.domain.enums.StatusEvento;

@Entity
@Table(name = "EVENTO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nome;

	@Column
	private String sigla;
	
	@Column
	@Lob
	private String conteudo;
	
	@Column
	private String descricao;
	
	@Column
	private String publico;
	
	@Column
	private String local;
	
	@Column
	@Enumerated(EnumType.STRING)
	private StatusEvento status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date inicioEvento;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fimEvento;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date inicioInscricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fimInscricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPublicacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataLiberacaoCertificado;
	
	@Column
	private String email;
	
	@Column 
	private String cargaHoraria;
	
	@Column
	private String unidadeGestora;
	
	@Column
	private Boolean interno;
	
	@Column
	private Boolean homologacaoPublica;
	
	@Column
	private Boolean autoHomologado;
	
	@Column
	private BigDecimal vagas;
	
	@Column
	private String qtdAtividade;
	
	@ManyToOne
	@JoinColumn(name = "codigo_tipo_evento")
	private TipoDeEvento tipoDeEvento;
	
	@OneToMany
	private List<Inscricao> inscricoes = new ArrayList<Inscricao>();
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.EXTRA)
	@BatchSize(size = 10)
	@JsonIgnore
	private List<EventoAttrConf> eventoAttrConfs = new ArrayList<>();
	
	@OneToMany
	private List<CategoriaParticipanteEvento> categoriaParticipanteEvento = new ArrayList<CategoriaParticipanteEvento>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusEvento getStatus() {
		return status;
	}

	public void setStatus(StatusEvento status) {
		this.status = status;
	}

	public Date getInicioEvento() {
		return inicioEvento;
	}

	public void setInicioEvento(Date inicioEvento) {
		this.inicioEvento = inicioEvento;
	}

	public Date getFimEvento() {
		return fimEvento;
	}

	public void setFimEvento(Date fimEvento) {
		this.fimEvento = fimEvento;
	}

	public Date getInicioInscricao() {
		return inicioInscricao;
	}

	public void setInicioInscricao(Date inicioInscricao) {
		this.inicioInscricao = inicioInscricao;
	}

	public Date getFimInscricao() {
		return fimInscricao;
	}

	public void setFimInscricao(Date fimInscricao) {
		this.fimInscricao = fimInscricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Date getDataLiberacaoCertificado() {
		return dataLiberacaoCertificado;
	}

	public void setDataLiberacaoCertificado(Date dataLiberacaoCertificado) {
		this.dataLiberacaoCertificado = dataLiberacaoCertificado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getUnidadeGestora() {
		return unidadeGestora;
	}

	public void setUnidadeGestora(String unidadeGestora) {
		this.unidadeGestora = unidadeGestora;
	}

	public Boolean getInterno() {
		return interno;
	}

	public void setInterno(Boolean interno) {
		this.interno = interno;
	}

	public Boolean getHomologacaoPublica() {
		return homologacaoPublica;
	}

	public void setHomologacaoPublica(Boolean homologacaoPublica) {
		this.homologacaoPublica = homologacaoPublica;
	}

	public Boolean getAutoHomologado() {
		return autoHomologado;
	}

	public void setAutoHomologado(Boolean autoHomologado) {
		this.autoHomologado = autoHomologado;
	}
	
	public BigDecimal getVagas() {
		return vagas;
	}

	public void setVagas(BigDecimal vagas) {
		this.vagas = vagas;
	}

	public TipoDeEvento getTipoDeEvento() {
		return tipoDeEvento;
	}

	public void setTipoDeEvento(TipoDeEvento tipoDeEvento) {
		this.tipoDeEvento = tipoDeEvento;
	}

	public List<Inscricao> getInscricoes() {
		return inscricoes;
	}

	public void setInscricoes(List<Inscricao> inscricoes) {
		this.inscricoes = inscricoes;
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
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getQtdAtividade() {
		return qtdAtividade;
	}

	public void setQtdAtividade(String qtdAtividade) {
		this.qtdAtividade = qtdAtividade;
	}

	public List<EventoAttrConf> getEventoAttrConfs() {
		return eventoAttrConfs;
	}

	public void setEventoAttrConfs(List<EventoAttrConf> eventoAttrConfs) {
		this.eventoAttrConfs = eventoAttrConfs;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getPublico() {
		return publico;
	}

	public void setPublico(String publico) {
		this.publico = publico;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}


	
	
}
