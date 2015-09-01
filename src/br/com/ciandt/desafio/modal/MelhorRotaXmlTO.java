package br.com.ciandt.desafio.modal;

import java.math.BigDecimal;

public class MelhorRotaXmlTO {
	private String origem;
	private String destino;
	private BigDecimal autonomia;
	private BigDecimal valorLitro;

	public String getOrigem() {
		return origem;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public BigDecimal getAutonomia() {
		return autonomia;
	}
	public void setAutonomia(BigDecimal autonomia) {
		this.autonomia = autonomia;
	}
	public BigDecimal getValorLitro() {
		return valorLitro;
	}
	public void setValorLitro(BigDecimal valorLitro) {
		this.valorLitro = valorLitro;
	}
	

}
