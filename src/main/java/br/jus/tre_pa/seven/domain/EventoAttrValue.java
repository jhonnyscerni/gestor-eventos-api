package br.jus.tre_pa.seven.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "EVENTO_ATTR_VALUE")
@DynamicInsert
@DynamicUpdate
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "uuid")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EventoAttrValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public EventoAttrValue() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PE_PESSOA_ATTR_VALUE")
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "SEQ_PE_PESSOA_ATTR_VALUE", sequenceName = "SEQ_PE_PESSOA_ATTR_VALUE")
	private Long id;

	@Column(length = 4000, nullable = true)
	@JsonProperty("default")
	private String value;

	@ManyToOne(optional = false)
	@JsonUnwrapped
	private EventoAttrConf pessoaAttrConf;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public EventoAttrConf getPessoaAttrConf() {
		return pessoaAttrConf;
	}

	public void setPessoaAttrConf(EventoAttrConf pessoaAttrConf) {
		this.pessoaAttrConf = pessoaAttrConf;
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
		EventoAttrValue other = (EventoAttrValue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
