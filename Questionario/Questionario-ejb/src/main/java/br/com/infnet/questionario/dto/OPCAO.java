package br.com.infnet.questionario.dto;

public enum OPCAO {

	CT("Concordo Totalmente"),CO("Concordo"), NN("Não Concordo nem Discordo"), DI("Discordo"),DT("Discordo Totalmente"),NS("Não sei avaliar");
	
	private String stringValue;
	
	private OPCAO(String opcao) {
		this.stringValue = opcao;
	}

	public String getStringValue() {
		return stringValue;
	}
	
}
