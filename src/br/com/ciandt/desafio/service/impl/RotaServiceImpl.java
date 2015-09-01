package br.com.ciandt.desafio.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;

import br.com.ciandt.desafio.infra.XmlUtil;
import br.com.ciandt.desafio.modal.MelhorRotaXmlTO;
import br.com.ciandt.desafio.modal.Rota;
import br.com.ciandt.desafio.modal.RotaXmlTO;
import br.com.ciandt.desafio.repositoy.RotaRepository;
import br.com.ciandt.desafio.repositoy.impl.RotaRepositoyImpl;
import br.com.ciandt.desafio.service.RotaService;

public class RotaServiceImpl implements RotaService {

	private RotaRepository repository = new RotaRepositoyImpl();

	@Override
	@SuppressWarnings("unchecked")
	public String registrarRotas(String pXmlRotas)
	        throws IllegalArgumentException {
		if (pXmlRotas == null || pXmlRotas.isEmpty()) {
			throw new IllegalArgumentException(
			        "XML (pXmlRotas) não pode ser nula ou vazia");
		}
		StringBuilder builder = new StringBuilder();
		Object objeto = new XmlUtil().adicionarAlias("rota", RotaXmlTO.class)
		        .gerarObjetoApatirXml(pXmlRotas);
		if (objeto instanceof Collection) {
			Collection<RotaXmlTO> listaRotas = (Collection<RotaXmlTO>) objeto;

			for (RotaXmlTO rotaXmlTo : listaRotas) {
				Rota rota = new Rota(rotaXmlTo);
				builder.append(rota.toString());
				gravarRota(rota);
			}
		}
		return builder.toString();

	}

	private void gravarRota(Rota pRota) {
		pRota.gerarSteps();
		Rota rotaBanco = repository.buscarPorSteps(pRota.getSteps());
		if (rotaBanco == null) {
			rotaBanco = pRota;
			repository.incluir(rotaBanco);
			System.out.println(rotaBanco.getSteps());

			List<Rota> listaRotas = repository.buscarPorDestino(rotaBanco
			        .getOrigem());
			for (Rota rotaEncontrada : listaRotas) {
				String origem = rotaEncontrada.getOrigem();
				String destino = rotaBanco.getDestino();
				String step = rotaEncontrada.getSteps();
				BigDecimal distancia = rotaBanco.getDistancia().add(
				        rotaEncontrada.getDistancia());
				gravarRota(new Rota(origem, destino, distancia, step));
			}
		}

	}

	@Override
	public String buscarMelhorRota(String pOrigem, String pDestino,
	        BigDecimal pAutonomia, BigDecimal pValorLitro) {
		if (pAutonomia == null || pAutonomia.compareTo(BigDecimal.ZERO) <= 0) {
			return String.format(RotaService.MENSAGEM_ERRO_AUTONOMIA_INVALIDA,
			        pAutonomia);
		}
		if (pValorLitro == null || pValorLitro.compareTo(BigDecimal.ZERO) <= 0) {
			return String
			        .format(RotaService.MENSAGEM_ERRO_VALOR_LITRO_INVALIDO,
			                pValorLitro);
		}

		String codigo = Rota.criarCodigo(pOrigem, pDestino);
		Rota melhorRota = repository.buscarPorCodigoEMenorDistancia(codigo);
		if (melhorRota == null) {
			return String.format(RotaService.MENSAGEM_ERRO_ROTA_NAO_ENCONTRADA,
			        pOrigem, pDestino);
		}

		// custo = (distancia / autonomia) * litro
		BigDecimal custo = melhorRota.getDistancia().divide(pAutonomia)
		        .multiply(pValorLitro).setScale(2, RoundingMode.HALF_UP);

		return String.format(RotaService.MENSAGEM_SUCESSO,
		        melhorRota.getSteps(), custo);

	}

	@Override
	public String obterTodasAsRotas() {
		List<Rota> rotas = repository.buscarTodas();
		return new XmlUtil().adicionarAlias("rota", Rota.class)
		        .gerarXmlApartirObjeto(rotas);
	}

	@Override
	public String buscarMelhorRota(String pXml) {
		MelhorRotaXmlTO melhorRota = (MelhorRotaXmlTO) new XmlUtil()
		        .adicionarAlias("melhorRota", MelhorRotaXmlTO.class)
		        .gerarObjetoApatirXml(pXml);
		return buscarMelhorRota(melhorRota.getOrigem(),
		        melhorRota.getDestino(), melhorRota.getAutonomia(),
		        melhorRota.getValorLitro());
	}

}
