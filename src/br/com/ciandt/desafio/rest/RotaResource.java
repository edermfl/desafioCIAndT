package br.com.ciandt.desafio.rest;

import java.math.BigDecimal;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.ciandt.desafio.service.RotaService;
import br.com.ciandt.desafio.service.impl.RotaServiceImpl;

@Path("/rotas")
public class RotaResource {

	RotaService service = new RotaServiceImpl();

	@PUT
	@Path("/melhorRota")
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.TEXT_XML)
	public Response getBetterRota(String pXml) {
		return Response.ok(service.buscarMelhorRota(pXml)).build();
	}

	@DELETE
	@Path("/limpar")
	public Response getLimparRotas() {
		service.limparTodasRotas();
		return Response.ok("<sucesso/>").build();
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public Response getAllRotas() {
		return Response.ok(service.obterTodasAsRotas()).build();
	}

	@PUT
	@Consumes(MediaType.TEXT_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public Response putDados(String pRotasXml) {
		try {
			String mensagem = service.registrarRotas(pRotasXml);
			return Response.ok(mensagem).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Erro: " + e.toString())
					.build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addRota(@FormParam("origem") String pOrigem, @FormParam("destino") String pDestino,
			@FormParam("distancia") String pDistancia) {

		try {
			String mensagem = service.registrarRota(pOrigem, pDestino, new BigDecimal(pDistancia));
			return Response.ok(mensagem).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Erro: " + e.toString())
					.build();
		}
	}
}
