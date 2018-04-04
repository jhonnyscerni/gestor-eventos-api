package br.jus.tre_pa.seven.domain.enums;

public enum StatusInscricao {
	
	DEFERIDO("deferido"),
	INDEFERIDO("indeferido"),
	CANCELADO("cancelado");
	

	private String status;

	private StatusInscricao(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
	
