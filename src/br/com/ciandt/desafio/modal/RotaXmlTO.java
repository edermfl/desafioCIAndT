package br.com.ciandt.desafio.modal;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

public class RotaXmlTO {
	
	private String origem;
	private String destino;
	private BigDecimal distancia;
	
	/**
	 * Construtor default
	 */
	public RotaXmlTO() {
	}
	
/**
 * 
 * @param origem
 * @param destino
 * @param distancia
 */
	public RotaXmlTO(String origem, String destino, BigDecimal distancia) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.distancia = distancia;
	}



	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public BigDecimal getDistancia() {
		return distancia;
	}
	public void setDistancia(BigDecimal distancia) {
		this.distancia = distancia;
	}
	
	
}
