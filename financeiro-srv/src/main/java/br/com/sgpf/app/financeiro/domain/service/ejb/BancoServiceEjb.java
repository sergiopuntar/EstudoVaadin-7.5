package br.com.sgpf.app.financeiro.domain.service.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.sgpf.app.financeiro.domain.entity.Banco;
import br.com.sgpf.app.financeiro.domain.repository.BancoRepository;
import br.com.sgpf.app.financeiro.domain.service.BancoService;

/**
 * EJB do serviço de Banco de Relacionamento.
 */
@Stateless
public class BancoServiceEjb implements BancoService {
	private static final long serialVersionUID = -8071576128913241907L;

	@Inject
	private BancoRepository bancoRepository;

	@Override
	public List<Banco> buscarBancos() {
		return bancoRepository.findAll();
	}

	@Override
	public Banco salvarBanco(Banco banco) {
		return bancoRepository.merge(banco);
	}

	@Override
	public void removerBanco(Banco banco) {
		bancoRepository.remove(banco);
	}
}
