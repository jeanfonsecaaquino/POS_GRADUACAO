package br.com.infnet.questionario.dto;

public enum PERFIL {

	P("ROLE_PROFESSOR","Professor"),A("ROLE_ALUNO","Aluno"),S("ROLE_SECRETARIA","Secretaria");
	
	private String stringValue;
	private String labelValue;
	
	private PERFIL(String perfil, String labelValue) {
		this.stringValue = perfil;
		this.labelValue = labelValue;
	}

	public String getStringValue() {
		return this.stringValue;
	}
	
	public String getLabelValue(){
		return this.labelValue;
	}
	
}
