package br.com.infnet.questionario.dto;

public enum TURNO {
	
	M("TURNO_MANHA","Manhã"),T("TURNO_TARDE","Tarde"),N("TURNO_NOITE","Noite");
	
	private String stringValue;
	private String labelValue;
	
	private TURNO(String stringValue, String labelValue) {
		this.stringValue = stringValue;
		this.labelValue = labelValue;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public String getLabelValue() {
		return labelValue;
	}
	public void setLabelValue(String labelValue) {
		this.labelValue = labelValue;
	}
	
	
}
