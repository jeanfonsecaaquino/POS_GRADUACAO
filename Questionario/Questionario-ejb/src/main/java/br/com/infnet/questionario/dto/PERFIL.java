package br.com.infnet.questionario.dto;

public enum PERFIL {

	P("ROLE_PROFESSOR"),A("ROLE_ALUNO"),S("ROLE_SECRETARIA");
	
	private String stringValue;
	
	private PERFIL(String perfil) {
		this.stringValue = perfil;
	}

	public String getStringValue() {
		return stringValue;
	}
	
}
