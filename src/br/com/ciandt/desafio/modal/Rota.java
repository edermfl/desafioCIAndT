package br.com.ciandt.desafio.modal;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "rota")
public class Rota implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String codigo;
	private String origem;
	private String destino;
	private BigDecimal distancia;
	private String steps;

	/**
	 * Construtor default
	 */
	Rota() {
	}

	/**
	 * @param rotaXmlTo
	 */
	public Rota(RotaXmlTO rotaXmlTo) {
		this(rotaXmlTo.getOrigem(), rotaXmlTo.getDestino(), rotaXmlTo
		        .getDistancia(), null);
	}

	/**
	 * 
	 * @param pOrigem
	 * @param pDestino
	 * @param pDistancia
	 */
	public Rota(String pOrigem, String pDestino, BigDecimal pDistancia,
	        String pStep) {
		super();
		codigo = criarCodigo(pOrigem, pDestino);
		this.origem = pOrigem;
		this.destino = pDestino;
		this.distancia = pDistancia;
		this.steps = pStep;
	}

	@Override
	public String toString() {
		return String.format("Rota: %s - %s (%s); ", origem, destino,
		        distancia.toString());
	}

	public static String criarCodigo(String pOrigem, String pDestino) {
		return String.format("%s#%s", pOrigem, pDestino);
	}

	public String gerarSteps() {
		StringBuilder builder = new StringBuilder(steps == null ? "" : steps);
		if (steps == null) {
			builder.append(origem);
		}
		builder.append(" ").append(destino);
		steps = builder.toString();
		return steps;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getOrigem() {
		return origem;
	}

	public String getDestino() {
		return destino;
	}

	public BigDecimal getDistancia() {
		return distancia;
	}

	public String getSteps() {
		return steps;
	}

}
