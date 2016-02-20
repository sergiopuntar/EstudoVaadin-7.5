package br.com.sgpf.app.financeiro.domain.service;

import java.io.Serializable;
import java.util.List;

import br.com.sgpf.app.financeiro.domain.entity.Conta;
import br.com.sgpf.app.financeiro.domain.valueobject.conta.SaldoConta;

/**
 * Interface do serviço de Conta de Relacionamento.
 */
public interface ContaService extends Serializable {

	/**
	 * Salva os dados de uma Conta de Relacionamento.
	 * 
	 * @param conta Conta a ser salva
	 * @return Conta salva
	 */
	Conta salvarConta(Conta conta);
	
	/**
	 * Remove uma Conta de Relacionamento.
	 * 
	 * @param conta Conta a ser removida
	 */
	void removerConta(Conta conta);
	
	/**
	 * Busca os saldos mais recentes de cada conta.
	 * 
	 * @return Saldos mais recentes de cada conta
	 */
	List<SaldoConta> buscarSaldosMaisRecentes();
}
