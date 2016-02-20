package br.com.sgpf.app.financeiro.domain.valueobject.conta;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.sgpf.app.financeiro.domain.entity.Conta;
import br.com.sgpf.app.financeiro.domain.entity.SaldoMensalConta;
import br.com.sgpf.app.financeiro.domain.valueobject.Mes;

/**
 * Value object com os dados de saldo de uma conta.
 */
public class SaldoConta implements Serializable {
	private static final long serialVersionUID = -8790968844305302869L;

	private Conta conta;
	
	private Integer ano;
	
	private Mes mes;
	
	private BigDecimal saldoMes;
	
	private BigDecimal saldoDisponivel;

	public SaldoConta() {
		super();
	}

	public SaldoConta(Conta conta) {
		this();
		this.conta = conta;
	}
	
	public SaldoConta(Conta conta, Integer ano, Mes mes) {
		this(conta);
		this.ano = ano;
		this.mes = mes;
	}

	public SaldoConta(Conta conta, Integer ano, Mes mes, BigDecimal saldoMes, BigDecimal saldoDisponivel) {
		this(conta, ano, mes);
		this.saldoMes = saldoMes;
		this.saldoDisponivel = saldoDisponivel;
	}

	public SaldoConta(Conta conta, SaldoMensalConta saldoMensalConta) {
		this(conta, saldoMensalConta.getAno(), saldoMensalConta.getMes(), saldoMensalConta.getValorFinal().subtract(saldoMensalConta.getValorInicial()) , saldoMensalConta.getValorFinal());
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public BigDecimal getSaldoMes() {
		return saldoMes;
	}

	public void setSaldoMes(BigDecimal saldoMes) {
		this.saldoMes = saldoMes;
	}

	public BigDecimal getSaldoDisponivel() {
		return saldoDisponivel;
	}

	public void setSaldoDisponivel(BigDecimal saldoDisponivel) {
		this.saldoDisponivel = saldoDisponivel;
	}
}
