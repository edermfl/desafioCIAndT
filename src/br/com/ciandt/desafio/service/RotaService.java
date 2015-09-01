package br.com.ciandt.desafio.service;

import java.math.BigDecimal;

public interface RotaService {
	public static final String MENSAGEM_ERRO_ROTA_NAO_ENCONTRADA = "<erro>Não foi encontrado rota para a origem %s e destino %s informado.</erro>";
	public static final String MENSAGEM_ERRO_VALOR_LITRO_INVALIDO = "<erro>Valor litro combustível informado %s inválid, valor deve ser maior que 0.</erro>";
	public static final String MENSAGEM_ERRO_AUTONOMIA_INVALIDA = "<erro>Autonomia informada %s inválida, valor deve ser maior que 0.</erro>";
	public static final String MENSAGEM_SUCESSO = "<melhorRota><rota>%s</rota><custo>%s</custo></melhorRota>";

	/**
	 * Gera rota
	 * 
	 * @return confirmação de inclusão
	 * @throws IllegalAccessException
	 */
	public String registrarRotas(String pListaRotas) throws IllegalArgumentException;

	/**
	 * 
	 * @param pOrigem
	 * @param pDestino
	 * @param pDistancia
	 * @return confirmação de inclusão
	 * @throws IllegalArgumentException
	 */
	public String registrarRota(String pOrigem, String pDestino, BigDecimal pDistancia) throws IllegalArgumentException;


	/**
	 * 
	 * @param pOrigem
	 * @param pDestino
	 * @param pAutonomia
	 * @param pValorLitro
	 * @return xml melhor rota
	 */
	public String buscarMelhorRota(String pOrigem, String pDestino, BigDecimal pAutonomia, BigDecimal pValorLitro);

	/**
	 * 
	 * @return XML de todas as rotas
	 */
	public String obterTodasAsRotas();

	/**
	 * 
	 * @param pXml
	 * @return xml melhor rota
	 */
	public String buscarMelhorRota(String pXml);

	/**
	 * Apaga todas as rotas criadas
	 */
	public void limparTodasRotas();

	/**
	 * Exclui uma rota por origem e destino
	 * 
	 * @param pOrigem
	 * @param pDestino
	 */
	public void excluirRota(String pOrigem, String pDestino);

}
