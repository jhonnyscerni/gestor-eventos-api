package br.jus.tre_pa.seven.domain.enums;

public enum FrequenciaTurno {

	MANHA("Manha"),
	TARDE("Tarde"),
	NOITE("Noite");

	private String turno;

	private FrequenciaTurno(String turno) {
		this.turno = turno;
	}
	
	public String getTurno() {
		return turno;
	}

}
