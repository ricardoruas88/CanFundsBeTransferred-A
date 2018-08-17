package com.solucao.entity;

public class StatusPosicao {

	private String posicao;

	private boolean status;

	public StatusPosicao(String posicao, boolean status) {
		this.posicao = posicao;
		this.status = status;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
