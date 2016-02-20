package br.com.sgpf.app.financeiro.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Chave primária da entidade OperacaoConta.
 */
@Embeddable
public class OperacaoContaPK implements Serializable {
	private static final long serialVersionUID = 5685208078078385552L;

	@ManyToOne
	@JoinColumn(name = "CONT_ID_CONTA", nullable = false, columnDefinition = "CHAR")
	private Conta conta;

	@ManyToOne
	@JoinColumn(name = "OPER_ID_OPERACAO", nullable = false, columnDefinition = "CHAR")
	private Operacao operacao;

	public OperacaoContaPK() {
		super();
	}

	public Conta getConta() {
		return this.conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Operacao getOperacao() {
		return this.operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getConta()).append(getOperacao()).toHashCode();
	}

	public boolean equals(Object obj) {
		boolean result = false;
		
		if (obj instanceof OperacaoContaPK) {
			OperacaoContaPK pk = (OperacaoContaPK) obj;
			
			result = new EqualsBuilder().append(getConta(), pk.getConta())
					.append(getOperacao(), ((OperacaoContaPK) obj).getOperacao()).isEquals();
		}
		
		return result;
	}
}
