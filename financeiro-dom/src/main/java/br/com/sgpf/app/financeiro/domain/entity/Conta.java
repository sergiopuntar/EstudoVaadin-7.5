package br.com.sgpf.app.financeiro.domain.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.sgpf.common.domain.entity.AbstractEntity;
import br.com.sgpf.common.domain.entity.AbstractUUIDEntity;

/**
 * Conta de relacionamento do usuário.
 */
@Entity
@Table(schema = "SGPF_FNC", name="CONTA")
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "CONT_ID_CONTA", unique = true, nullable = false, length = 36, columnDefinition = "CHAR")),
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "CONT_DT_CRIACAO", nullable = false)),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "CONT_DT_ATUALIZACAO", nullable = true)),
		@AttributeOverride(name = "versao", column = @Column(name = "CONT_NR_VERSAO", nullable = false, precision = 38))
})
public class Conta extends AbstractUUIDEntity {
	private static final long serialVersionUID = 1772884410184064139L;
	
	@NotNull(message = "{conta.nome.not_null}")
	@Size(message = "{conta.nome.size}", min = 1,  max = 100)
	@Column(name = "CONT_NM_CONTA", nullable = false, length = 100)
	private String nome;

	@NotNull(message = "{conta.descricao.not_null}")
	@Size(message = "{conta.descricao.size}", min = 1,  max = 4000)
	@Column(name = "CONT_DS_CONTA", nullable = true, length = 4000)
	private String descricao;

	@NotNull(message = "{conta.numero_agencia.not_null}")
	@Size(message = "{conta.numero_agencia.size}", min = 1,  max = 15)
	@Column(name = "CONT_NR_AGENCIA", nullable = false, length = 15)
	private String numeroAgencia;

	@NotNull(message = "{conta.numero_conta.not_null}")
	@Size(message = "{conta.numero_conta.size}", min = 1,  max = 15)
	@Column(name = "CONT_NR_CONTA", nullable = false, length = 15)
	private String numeroConta;
	
	@NotNull(message = "{conta.banco.not_null}")
	@ManyToOne
	@JoinColumn(name = "BANC_ID_BANCO", nullable = false, columnDefinition = "CHAR")
	private Banco banco;
	
	@OneToMany(mappedBy = "conta")
	private Set<SaldoMensalConta> saldos;
	
	@OneToMany
	@JoinColumn(name = "CONT_ID_CONTA", referencedColumnName = "CONT_ID_CONTA", columnDefinition = "CHAR")
	private Set<OperacaoConta> operacoes;

	@OneToMany(mappedBy = "conta")
	private Set<Aplicacao> aplicacoes;

	public Conta() {
		super();
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getNumeroAgencia() {
		return this.numeroAgencia;
	}
	
	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}
	
	public String getNumeroConta() {
		return this.numeroConta;
	}
	
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	
	public Banco getBanco() {
		return this.banco;
	}
	
	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	public Set<SaldoMensalConta> getSaldos() {
		return this.saldos;
	}
	
	public void setSaldos(Set<SaldoMensalConta> saldos) {
		this.saldos = saldos;
	}
	
	public SaldoMensalConta addSaldo(SaldoMensalConta saldo) {
		getSaldos().add(saldo);
		saldo.setConta(this);
		
		return saldo;
	}
	
	public SaldoMensalConta removeSaldo(SaldoMensalConta saldo) {
		getSaldos().remove(saldo);
		saldo.setConta(null);
		
		return saldo;
	}

	public Set<OperacaoConta> getOperacoes() {
		return this.operacoes;
	}

	public void setOperacoes(Set<OperacaoConta> operacoes) {
		this.operacoes = operacoes;
	}

	public OperacaoConta addOperacoe(OperacaoConta operacoe) {
		getOperacoes().add(operacoe);
		operacoe.setConta(this);

		return operacoe;
	}

	public OperacaoConta removeOperacoe(OperacaoConta operacoe) {
		getOperacoes().remove(operacoe);
		operacoe.setConta(null);

		return operacoe;
	}

	public Set<Aplicacao> getAplicacoes() {
		return this.aplicacoes;
	}

	public void setAplicacoes(Set<Aplicacao> aplicacoes) {
		this.aplicacoes = aplicacoes;
	}

	public Aplicacao addAplicacoe(Aplicacao aplicacoe) {
		getAplicacoes().add(aplicacoe);
		aplicacoe.setConta(this);

		return aplicacoe;
	}

	public Aplicacao removeAplicacoe(Aplicacao aplicacoe) {
		getAplicacoes().remove(aplicacoe);
		aplicacoe.setConta(null);

		return aplicacoe;
	}

	@Override
	protected boolean canEqual(AbstractEntity<?> entity) {
		return entity instanceof Conta;
	}
}
