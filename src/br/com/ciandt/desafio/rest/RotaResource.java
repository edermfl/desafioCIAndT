package br.com.ciandt.desafio.rest;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import br.com.ciandt.desafio.modal.RotaXmlTO;
import br.com.ciandt.desafio.service.RotaService;
import br.com.ciandt.desafio.service.impl.RotaServiceImpl;

@Path("/rotas")
public class RotaResource {

	RotaService service = new RotaServiceImpl();

//	@GET
//	@Produces(MediaType.TEXT_XML)
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	public Response getAllRotas(
//			@FormParam("origem") String pOrigem,
//	        @FormParam("destino") String pDestino,
//	        @FormParam("autonomia") String pAutonomia,
//	        @FormParam("valorLitro") String pValorLitro) {
//		BigDecimal autonomia = new BigDecimal(pAutonomia);
//		BigDecimal valorLitro = new BigDecimal(pValorLitro);
//		return Response.ok(service.buscarMelhorRota(pOrigem, pDestino, autonomia, valorLitro)).build();
//	}
	
	@PUT
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.TEXT_XML)
	public Response getBetterRota(String pXml) {
		return Response.ok(service.buscarMelhorRota(pXml)).build();
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public Response getAllRotas() {

		return Response.ok(service.obterTodasAsRotas()).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String putDados(JAXBElement<RotaXmlTO> pRotaXml) {
		RotaXmlTO rota = pRotaXml.getValue();
		return addRota(rota.getOrigem(), rota.getDestino(), rota.getDistancia()
		        .toString());
	}

	@PUT
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public Response putDados(String pRotasXml) {
		String mensagem = service.registrarRotas(pRotasXml);
		return Response.ok("Sucesso: " + mensagem).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addRota(@FormParam("origem") String pOrigem,
	        @FormParam("destino") String pDestino,
	        @FormParam("distancia") String pDistancia) {
		return "Rota: " + pOrigem + "-" + pDestino + " (" + pDistancia
		        + ") - criado com sucesso!";
	}
}
