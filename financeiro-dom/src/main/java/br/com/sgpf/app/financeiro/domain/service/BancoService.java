package br.com.sgpf.app.financeiro.domain.service;

import java.io.Serializable;
import java.util.List;

import br.com.sgpf.app.financeiro.domain.entity.Banco;

/**
 * Interface do serviço de Banco de Relacionamento.
 */
public interface BancoService extends Serializable {

	/**
	 * Recupera todos os Bancos de Relacionamento.
	 * 
	 * @return Todos os Bancos recuperados
	 */
	List<Banco> buscarBancos();
	
	/**
	 * Salva os dados de um Banco de Relacionamento.
	 * 
	 * @param banco Banco a ser salvo
	 * @return Banco salvo
	 */
	Banco salvarBanco(Banco banco);
	
	/**
	 * Remove um Banco de Relacionamento.
	 * 
	 * @param banco Banco a ser removido
	 */
	void removerBanco(Banco banco);
}
