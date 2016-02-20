package br.com.sgpf.app.financeiro.domain.repository;

import java.util.List;

import br.com.sgpf.app.financeiro.domain.entity.Conta;
import br.com.sgpf.app.financeiro.domain.entity.SaldoMensalConta;
import br.com.sgpf.common.domain.repository.Repository;

/**
 * Repositório da entidade Conta de Relacionamento.
 */
public interface ContaRepository extends Repository<Conta, String> {

	/**
	 * Recupera todas as contas com seus saldos mensais mais recentes.
	 * 
	 * @return Tuplas contedo os seguintes elementos:<br>
	 *         0 - {@link Conta} de relacionamento<br>
	 *         1 - {@link SaldoMensalConta} mais recente da conta
	 */
	List<Object[]> buscarTodasComSaldosMensaisMaisRecentes();
}
