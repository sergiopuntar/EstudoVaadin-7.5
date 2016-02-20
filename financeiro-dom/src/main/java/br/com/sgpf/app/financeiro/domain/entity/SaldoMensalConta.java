package br.com.sgpf.app.financeiro.domain.entity;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sgpf.app.financeiro.domain.valueobject.Mes;
import br.com.sgpf.common.domain.entity.AbstractEntity;
import br.com.sgpf.common.domain.entity.AbstractUUIDEntity;

/**
 * Saldo de início e de fim de um mês em uma conta de relacionamento.
 */
@Entity
@Table(schema = "SGPF_FNC", name="SALDO_MENSAL_CONTA")
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "SAMC_ID_SALDO_MES", unique = true, nullable = false, length = 36, columnDefinition = "CHAR")),
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "SAMC_DT_CRIACAO", nullable = false)),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "SAMC_DT_ATUALIZACAO", nullable = true)),
		@AttributeOverride(name = "versao", column = @Column(name = "SAMC_NR_VERSAO", nullable = false, precision = 38))
})
public class SaldoMensalConta extends AbstractUUIDEntity {
	private static final long serialVersionUID = 3278495785967377594L;
	
	@ManyToOne
	@JoinColumn(name="CONT_ID_CONTA", nullable = false, columnDefinition = "CHAR")
	private Conta conta;

	@Column(name = "SAMC_IN_MES", nullable = false, precision = 2)
	@Enumerated(EnumType.ORDINAL)
	private Mes mes;

	@Column(name = "SAMC_NR_ANO", nullable = false, precision = 4)
	private Integer ano;

	@Column(name = "SAMC_VL_FINAL", nullable = true, precision = 38, scale = 4)
	private BigDecimal valorFinal;

	@Column(name = "SAMC_VL_INICIAL", nullable = false, precision = 38, scale = 4)
	private BigDecimal valorInicial;

	public SaldoMensalConta() {
		super();
	}

	public Conta getConta() {
		return this.conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Mes getMes() {
		return this.mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return this.ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public BigDecimal getValorFinal() {
		return this.valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	public BigDecimal getValorInicial() {
		return this.valorInicial;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}

	@Override
	protected boolean canEqual(AbstractEntity<?> entity) {
		return entity instanceof SaldoMensalConta;
	}
}
