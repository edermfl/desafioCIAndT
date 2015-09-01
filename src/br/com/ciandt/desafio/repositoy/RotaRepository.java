package br.com.ciandt.desafio.repositoy;

import java.io.Serializable;
import java.util.List;

import br.com.ciandt.desafio.modal.Rota;

public interface RotaRepository {
	
	public Rota buscarPorCodigoEMenorDistancia(String pCodigo);
	
	public List<Rota> buscarPorDestino(String pDestino);
	
	public List<Rota> buscarPorOrigem(String pOrigem);
	
	public Serializable incluir(Serializable pIdRota);

	public Rota buscarPorSteps(String steps);

	public List<Rota> buscarTodas();

}
