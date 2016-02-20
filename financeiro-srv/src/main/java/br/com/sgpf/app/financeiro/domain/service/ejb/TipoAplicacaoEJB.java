package br.com.sgpf.app.financeiro.domain.service.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.sgpf.app.financeiro.domain.entity.TipoAplicacao;
import br.com.sgpf.app.financeiro.domain.repository.TipoAplicacaoRepository;
import br.com.sgpf.app.financeiro.domain.service.TipoAplicacaoService;

/**
 * EJB do serviço de Tipo de Aplicação.
 */
@Stateless
public class TipoAplicacaoEJB implements TipoAplicacaoService {
	private static final long serialVersionUID = 1692113257658114180L;
	
	@Inject
	private TipoAplicacaoRepository tipoAplicacaoRepository;

	@Override
	public List<TipoAplicacao> buscarTiposAplicacao() {
		return tipoAplicacaoRepository.findAll();
	}

	@Override
	public TipoAplicacao salvarTipoAplicacao(TipoAplicacao tipoAplicacao) {
		return tipoAplicacaoRepository.merge(tipoAplicacao);
	}

	@Override
	public void removerTipoAplicacao(TipoAplicacao tipoAplicacao) {
		tipoAplicacaoRepository.remove(tipoAplicacao);
	}
}
