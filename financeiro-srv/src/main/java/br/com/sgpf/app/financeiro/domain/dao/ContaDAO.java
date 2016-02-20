package br.com.sgpf.app.financeiro.domain.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.sgpf.app.financeiro.domain.entity.Conta;
import br.com.sgpf.app.financeiro.domain.repository.ContaRepository;
import br.com.sgpf.common.domain.dao.AbstractDAO;

/**
 * DAO da entidade Conta de Relacionamento.
 */
@ApplicationScoped
public class ContaDAO extends AbstractDAO<Conta, String> implements ContaRepository {
	private static final long serialVersionUID = 3077405096898511696L;
	
	private static final String QUERY_BUSCAR_TODAS_COM_SALDOS_MENSAIS_MAIS_RECENTES = "Contas.buscarTodasComSaldosMensaisMaisRecentes";

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> buscarTodasComSaldosMensaisMaisRecentes() {
		return em.createNamedQuery(QUERY_BUSCAR_TODAS_COM_SALDOS_MENSAIS_MAIS_RECENTES).getResultList();
	}
}
