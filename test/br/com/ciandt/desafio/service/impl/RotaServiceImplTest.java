package br.com.ciandt.desafio.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.ciandt.desafio.infra.HibernateUtil;
import br.com.ciandt.desafio.modal.Rota;
import br.com.ciandt.desafio.service.RotaService;

public class RotaServiceImplTest {

	private Set<Rota> objetosCriado = new HashSet<Rota>();

	private RotaService service = new RotaServiceImpl();

	private String xml = "<list>" 
			+ "<rota><origem>A</origem><destino>B</destino><distancia>10</distancia></rota>"
	        + "<rota><origem>B</origem><destino>D</destino><distancia>15</distancia></rota>"
	        + "<rota><origem>A</origem><destino>C</destino><distancia>20</distancia></rota>"
	        + "<rota><origem>C</origem><destino>D</destino><distancia>30</distancia></rota>"
	        + "<rota><origem>B</origem><destino>E</destino><distancia>50</distancia></rota>"
	        + "<rota><origem>D</origem><destino>E</destino><distancia>30</distancia></rota>" 
	        + "</list>";

	@Test(expected = IllegalArgumentException.class)
	public void test_registrarRotas_porXml_xml_nulo() throws IllegalArgumentException {
		String xmlRota = null;
		service.registrarRotas(xmlRota);
	}
	
	@Test
	public void test_registrarRota_origem_igual_destino() throws IllegalArgumentException {
		String mensagem = service.registrarRota("X","X",BigDecimal.TEN);
		Assert.assertEquals("<erro>Rota [null]: X - X (10).</erro>",mensagem);
	}
	
	@Test
	public void test_registrarRota_origem_igual_destino_2() throws IllegalArgumentException {
		service.registrarRota("X","Y",BigDecimal.TEN);
		String mensagem = service.registrarRota("Y","X",BigDecimal.TEN);
		Assert.assertEquals("<erro>Rota [Y X]: Y - X (10).</erro>",mensagem);
	}
	
	@Test()
	public void test_registrarRota_com_sucesso() throws IllegalAccessException {
		service.registrarRota("X","Y",BigDecimal.TEN);
		service.registrarRota("A","X",BigDecimal.TEN);
		List<Rota> listaRotas = buscarTodasRotas();
		Assert.assertEquals(listaRotas.toString(),3, listaRotas.size());
	}

	@Test
	public void test_registrarRotas_porXml_sucesso() throws IllegalAccessException {
		service.limparTodasRotas();
		System.out.println(xml);
		service.registrarRotas(xml);
		List<Rota> listaRotas = buscarTodasRotas();
		objetosCriado.addAll(listaRotas);
		System.out.println(listaRotas);

		Map<String, BigDecimal> mapaEsperado = obterMapaEsperado();
		Assert.assertEquals(listaRotas.toString(), mapaEsperado.size(), listaRotas.size());

		for (Rota rota : listaRotas) {
			BigDecimal distancia = mapaEsperado.get(rota.getSteps());
			Assert.assertNotNull(rota.toString(), distancia);
			Assert.assertTrue(
			        rota.toString() + " " + distancia + " <> "
			                + rota.getDistancia(),
			        distancia.compareTo(rota.getDistancia()) == 0);
		}

	}

	@Test
	public void test_registrarRotas_com_merge_com_sucesso() throws IllegalAccessException {
		service.limparTodasRotas();
		String xmlTest = "<list>" 
				+ "<rota><origem>A</origem><destino>B</destino><distancia>10</distancia></rota>"
		        + "<rota><origem>B</origem><destino>D</destino><distancia>15</distancia></rota>"
		        + "<rota><origem>F</origem><destino>A</destino><distancia>20</distancia></rota>"
		        +"</list>";
		
		service.registrarRotas(xmlTest);
		
		List<Rota> listaRotas = buscarTodasRotas();
		objetosCriado.addAll(listaRotas);
		System.out.println(listaRotas);

		Map<String, BigDecimal> mapaEsperado = new HashMap<String, BigDecimal>();
		mapaEsperado.put("A B", new BigDecimal(10));
		mapaEsperado.put("B D", new BigDecimal(15));
		mapaEsperado.put("A B D", new BigDecimal(25));
		mapaEsperado.put("F A", new BigDecimal(20));
		mapaEsperado.put("F A B", new BigDecimal(30));
		mapaEsperado.put("F A B D", new BigDecimal(45));
		
		Assert.assertEquals(listaRotas.toString(), mapaEsperado.size(), listaRotas.size());

		for (Rota rota : listaRotas) {
			BigDecimal distancia = mapaEsperado.get(rota.getSteps());
			Assert.assertNotNull(rota.toString(), distancia);
			Assert.assertTrue(
			        rota.toString() + " " + distancia + " <> "
			                + rota.getDistancia(),
			        distancia.compareTo(rota.getDistancia()) == 0);
		}

	}
	
	@Test
	public void test_buscarMelhorRota_autonomia_invalida() {
		// registra rotas padrão
		service.registrarRotas(xml);
		List<Rota> listaRotas = buscarTodasRotas();
		objetosCriado.addAll(listaRotas);

		BigDecimal autonomia = null;
		String erro = service.buscarMelhorRota("A", "B", autonomia,
		        BigDecimal.ONE);
		Assert.assertEquals(String.format(
		        RotaService.MENSAGEM_ERRO_AUTONOMIA_INVALIDA, autonomia), erro);

		autonomia = BigDecimal.ZERO;
		erro = service.buscarMelhorRota("A", "B", autonomia, BigDecimal.ONE);
		Assert.assertEquals(String.format(
		        RotaService.MENSAGEM_ERRO_AUTONOMIA_INVALIDA, autonomia), erro);

		autonomia = new BigDecimal(-1);
		erro = service.buscarMelhorRota("A", "B", autonomia, BigDecimal.ONE);
		Assert.assertEquals(String.format(
		        RotaService.MENSAGEM_ERRO_AUTONOMIA_INVALIDA, autonomia), erro);
	}

	@Test
	public void test_buscarMelhorRota_valor_invalida() {
		// registra rotas padrão
		service.registrarRotas(xml);
		List<Rota> listaRotas = buscarTodasRotas();
		objetosCriado.addAll(listaRotas);

		BigDecimal valor = null;
		String erro = service.buscarMelhorRota("A", "B", BigDecimal.ONE, valor);
		Assert.assertEquals(String.format(
		        RotaService.MENSAGEM_ERRO_VALOR_LITRO_INVALIDO, valor), erro);

		valor = BigDecimal.ZERO;
		erro = service.buscarMelhorRota("A", "B", BigDecimal.ONE, valor);
		Assert.assertEquals(String.format(
		        RotaService.MENSAGEM_ERRO_VALOR_LITRO_INVALIDO, valor), erro);

		valor = new BigDecimal(-1);
		erro = service.buscarMelhorRota("A", "B", BigDecimal.ONE, valor);
		Assert.assertEquals(String.format(
		        RotaService.MENSAGEM_ERRO_VALOR_LITRO_INVALIDO, valor), erro);
	}

	@Test
	public void test_buscarMelhorRota_rota_nao_encontrada() {
		// registra rotas padrão
		service.registrarRotas(xml);
		List<Rota> listaRotas = buscarTodasRotas();
		System.out.println(listaRotas);
		objetosCriado.addAll(listaRotas);

		String origem = "X";
		String destino = "Y";
		String erro = service.buscarMelhorRota(origem, destino, BigDecimal.ONE,
		        BigDecimal.ONE);
		Assert.assertEquals(
		        String.format(RotaService.MENSAGEM_ERRO_ROTA_NAO_ENCONTRADA,
		                origem, destino), erro);
	}

	@Test
	public void test_buscarMelhorRota_sucesso() {
		// registra rotas padrão
		service.registrarRotas(xml);
		List<Rota> listaRotas = buscarTodasRotas();
		objetosCriado.addAll(listaRotas);

		// cenário 1
		String origem = "A";
		String destino = "D";
		BigDecimal autonomia = new BigDecimal(10);
		BigDecimal valorLitro = new BigDecimal(2.5);
		BigDecimal custo = new BigDecimal(6.25);
		String steps = "A B D";
		String xml = service.buscarMelhorRota(origem, destino, autonomia,
		        valorLitro);
		Assert.assertEquals(
		        String.format(RotaService.MENSAGEM_SUCESSO, steps, custo), xml);

		// cenário 2
		destino = "E";
		valorLitro = new BigDecimal("2.999");
		custo = new BigDecimal("16.49");
		steps = "A B D E";
		xml = service.buscarMelhorRota(origem, destino, autonomia, valorLitro);
		Assert.assertEquals(
		        String.format(RotaService.MENSAGEM_SUCESSO, steps, custo), xml);

	}

	@SuppressWarnings("unchecked")
	private List<Rota> buscarTodasRotas() {
		return HibernateUtil.getSession().createCriteria(Rota.class).list();
	}

	private Map<String, BigDecimal> obterMapaEsperado() {
		Map<String, BigDecimal> mapaEsperado = new HashMap<String, BigDecimal>();
		mapaEsperado.put("A B", new BigDecimal(10));
		mapaEsperado.put("B D", new BigDecimal(15));
		mapaEsperado.put("A B D", new BigDecimal(25));
		mapaEsperado.put("A B E", new BigDecimal(60));
		mapaEsperado.put("A B D E", new BigDecimal(55));
		mapaEsperado.put("A C", new BigDecimal(20));
		mapaEsperado.put("A C D", new BigDecimal(50));
		mapaEsperado.put("A C D E", new BigDecimal(80));
		mapaEsperado.put("C D", new BigDecimal(30));
		mapaEsperado.put("B E", new BigDecimal(50));
		mapaEsperado.put("D E", new BigDecimal(30));
		mapaEsperado.put("B D E", new BigDecimal(45));
		mapaEsperado.put("C D E", new BigDecimal(60));
		return mapaEsperado;
	}

	@Before
	public void teardown() {
		for (Rota rota : objetosCriado) {
			HibernateUtil.getSession().delete(rota);
		}
		service.limparTodasRotas();
	}


//	@Test
//	public void test_gerarRotas_sucesso2() throws IllegalAccessException {
//		System.out.println(gerarXmlAleatorios(50));
//	}
	
//	private String gerarXmlAleatorios(int quantidade) {
//		List<RotaXmlTO> rotas = new ArrayList<RotaXmlTO>();
//		for (int i = 0; i < quantidade; i++) {
//			Random random = new Random();
//			int valor = random.nextInt(99);
//			char origem = (char) (65 + random.nextInt(25));
//			char destino = (char) (65 + random.nextInt(25));
//			destino = (char) (destino == origem ? destino + 1 : destino);
//			rotas.add(new RotaXmlTO(origem + "", destino + "", new BigDecimal(
//			        valor)));
//		}
//		String xml = new XmlUtil().adicionarAlias("rota", RotaXmlTO.class).gerarXmlApartirObjeto(rotas);
//		System.out.println(xml);
//		return xml;
//	}

}
