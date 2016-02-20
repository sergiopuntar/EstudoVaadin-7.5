package br.com.sgpf.app.financeiro.domain.service.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.sgpf.app.financeiro.domain.entity.GrupoOperacao;
import br.com.sgpf.app.financeiro.domain.repository.GrupoOperacaoRepository;
import br.com.sgpf.app.financeiro.domain.service.GrupoOperacaoService;

/**
 * EJB do serviço de Grupo de Operação.
 */
@Stateless
public class GrupoOperacaoEjb implements GrupoOperacaoService {
	private static final long serialVersionUID = -2206185462850225914L;
	
	@Inject
	private GrupoOperacaoRepository grupoOperacaoRepository;

	@Override
	public List<GrupoOperacao> buscarGruposOperacao() {
		return grupoOperacaoRepository.findAll();
	}

	@Override
	public GrupoOperacao salvarGrupoOperacao(GrupoOperacao grupoOperacao) {
		return grupoOperacaoRepository.merge(grupoOperacao);
	}

	@Override
	public void removerGrupoOperacao(GrupoOperacao grupoOperacao) {
		grupoOperacaoRepository.remove(grupoOperacao);
	}
}
