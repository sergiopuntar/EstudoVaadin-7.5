package br.com.sgpf.app.financeiro.domain.service;

import java.io.Serializable;
import java.util.List;

import br.com.sgpf.app.financeiro.domain.entity.TipoAplicacao;

/**
 * Interface do serviço de Tipo de Aplicação.
 */
public interface TipoAplicacaoService extends Serializable {

	/**
	 * Recupera todos os Tipos de Aplicação de Relacionamento.
	 * 
	 * @return Todos os Tipos de Aplicação recuperados
	 */
	List<TipoAplicacao> buscarTiposAplicacao();
	
	/**
	 * Salva os dados de um Tipo de Aplicação.
	 * 
	 * @param tipoAplicacao Tipo de Aplicação a ser salvo
	 * @return Tipo de Aplicação salvo
	 */
	TipoAplicacao salvarTipoAplicacao(TipoAplicacao tipoAplicacao);
	
	/**
	 * Remove um Tipo de Aplicação.
	 * 
	 * @param tipoAplicacao Tipo de Aplicação a ser removido
	 */
	void removerTipoAplicacao(TipoAplicacao tipoAplicacao);
}
