package br.com.sgpf.app.financeiro.domain.dao;

import javax.enterprise.context.ApplicationScoped;

import br.com.sgpf.app.financeiro.domain.entity.GrupoOperacao;
import br.com.sgpf.app.financeiro.domain.repository.GrupoOperacaoRepository;
import br.com.sgpf.common.domain.dao.AbstractDAO;

/**
 * DAO da entidade Grupos de Operação.
 */
@ApplicationScoped
public class GrupoOperacaoDAO extends AbstractDAO<GrupoOperacao, String> implements GrupoOperacaoRepository {
	private static final long serialVersionUID = 3383352271698070591L;

}
