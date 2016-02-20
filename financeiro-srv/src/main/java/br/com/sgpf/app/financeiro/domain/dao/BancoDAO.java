package br.com.sgpf.app.financeiro.domain.dao;

import javax.enterprise.context.ApplicationScoped;

import br.com.sgpf.app.financeiro.domain.entity.Banco;
import br.com.sgpf.app.financeiro.domain.repository.BancoRepository;
import br.com.sgpf.common.domain.dao.AbstractDAO;

/**
 * DAO da entidade Banco de Relacionamento.
 */
@ApplicationScoped
public class BancoDAO extends AbstractDAO<Banco, String> implements BancoRepository {
	private static final long serialVersionUID = 4280959953339681667L;

}
