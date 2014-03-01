package br.com.infnet.questionario.dto;

public enum PERFIL {

	P("PROFESSOR"),A("ALUNO"),S("SECRETARIA");
	
	private String stringValue;
	
	private PERFIL(String perfil) {
		this.stringValue = perfil;
	}

	public String getStringValue() {
		return stringValue;
	}
	
}
