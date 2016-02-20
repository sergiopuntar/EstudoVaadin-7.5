package br.com.sgpf.app.financeiro.domain.service.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.sgpf.app.financeiro.domain.entity.Conta;
import br.com.sgpf.app.financeiro.domain.entity.SaldoMensalConta;
import br.com.sgpf.app.financeiro.domain.repository.ContaRepository;
import br.com.sgpf.app.financeiro.domain.service.ContaService;
import br.com.sgpf.app.financeiro.domain.valueobject.conta.SaldoConta;

/**
 * EJB do serviço de Conta de Relacionamento.
 */
@Stateless
public class ContaServiceEjb implements ContaService {
	private static final long serialVersionUID = 4226705782237904612L;

	@Inject
	private ContaRepository contaRepository;

	@Override
	public Conta salvarConta(Conta conta) {
		return contaRepository.merge(conta);
	}

	@Override
	public void removerConta(Conta conta) {
		contaRepository.remove(conta);
	}
	
	@Override
	public List<SaldoConta> buscarSaldosMaisRecentes() {
		List<Object[]> saldosMaisRecentes = contaRepository.buscarTodasComSaldosMensaisMaisRecentes();
		List<SaldoConta> saldos = new ArrayList<>();
		
		for (Object[] saldoMaisRecente : saldosMaisRecentes) {
			saldos.add(buildSaldoConta((Conta) saldoMaisRecente[0], (SaldoMensalConta) saldoMaisRecente[1]));
		}
		
		return saldos;
	}
	
	/**
	 * Constrói o VO com os dados de saldo do mês de uma conta.
	 * 
	 * @param conta Conta de relacinamento
	 * @param saldoMensalConta Dados de saldo de um mês dessa conta
	 * @return VO construído
	 */
	private SaldoConta buildSaldoConta(Conta conta, SaldoMensalConta saldoMensalConta) {
		return saldoMensalConta != null ? new SaldoConta(conta, saldoMensalConta) : new SaldoConta(conta);
	}
}
