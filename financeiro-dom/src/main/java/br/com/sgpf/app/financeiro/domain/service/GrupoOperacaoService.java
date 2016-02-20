package br.com.sgpf.app.financeiro.domain.service;

import java.io.Serializable;
import java.util.List;

import br.com.sgpf.app.financeiro.domain.entity.GrupoOperacao;

/**
 * Interface do serviço de Grupo de Operação.
 */
public interface GrupoOperacaoService extends Serializable {

	/**
	 * Recupera todos os Grupos de Operação.
	 * 
	 * @return Todos os Grupos de Operação recuperados
	 */
	List<GrupoOperacao> buscarGruposOperacao();
	
	/**
	 * Salva os dados de um Grupo de Operação.
	 * 
	 * @param grupoOperacao Grupo de Operação a ser salvo
	 * @return Grupo de Operação salvo
	 */
	GrupoOperacao salvarGrupoOperacao(GrupoOperacao grupoOperacao);
	
	/**
	 * Remove um Grupo de Operação.
	 * 
	 * @param grupoOperacao Grupo de Operação a ser removido
	 */
	void removerGrupoOperacao(GrupoOperacao grupoOperacao);
}
