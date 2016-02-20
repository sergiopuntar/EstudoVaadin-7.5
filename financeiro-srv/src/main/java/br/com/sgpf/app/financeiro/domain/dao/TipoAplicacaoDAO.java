package br.com.sgpf.app.financeiro.domain.dao;

import javax.enterprise.context.ApplicationScoped;

import br.com.sgpf.app.financeiro.domain.entity.TipoAplicacao;
import br.com.sgpf.app.financeiro.domain.repository.TipoAplicacaoRepository;
import br.com.sgpf.common.domain.dao.AbstractDAO;

/**
 * DAO da entidade Tipo de Aplicação.
 */
@ApplicationScoped
public class TipoAplicacaoDAO extends AbstractDAO<TipoAplicacao, String> implements TipoAplicacaoRepository {
	private static final long serialVersionUID = 6592186994087555477L;

}
