package com.solucao.entity;

public class ResponseNodes {

	String dados;

	boolean resultado;

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public ResponseNodes() {
		// TODO Auto-generated constructor stub
	}

	public ResponseNodes(String dados, boolean resultado) {
		this.dados = dados;
		this.resultado = resultado;
	}
}
