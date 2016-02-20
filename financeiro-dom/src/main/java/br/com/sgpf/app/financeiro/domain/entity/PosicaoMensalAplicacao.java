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
 * Posição em um mês de uma aplicação financeira.
 */
@Entity
@Table(schema = "SGPF_FNC", name="POSICAO_MENSAL_APLICACAO")
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "POMA_ID_EXTRATO_APLICACAO", unique = true, nullable = false, length = 36, columnDefinition = "CHAR")),
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "POMA_DT_CRIACAO", nullable = false)),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "POMA_DT_ATUALIZACAO", nullable = true)),
		@AttributeOverride(name = "versao", column = @Column(name = "POMA_NR_VERSAO", nullable = false, precision = 38))
})
public class PosicaoMensalAplicacao extends AbstractUUIDEntity {
	private static final long serialVersionUID = -5185329049607984356L;

	@ManyToOne
	@JoinColumn(name = "APLI_ID_APLICACAO", nullable = false, columnDefinition = "CHAR")
	private Aplicacao aplicacao;
	
	@Column(name = "POMA_NR_ANO", nullable = false, precision = 4)
	private Integer ano;
	
	@Column(name = "POMA_IN_MES", nullable = false, precision = 2)
	@Enumerated(EnumType.ORDINAL)
	private Mes mes;
	
	@Column(name = "POMA_VL_APLICADO", nullable = true, precision = 38, scale = 4)
	private BigDecimal valorAplicado;
	
	@Column(name = "POMA_VL_SALDO_ANTERIOR", nullable = false, precision = 38, scale = 4)
	private BigDecimal valorSaldoAnterior;
	
	@Column(name = "POMA_VL_SALDO_BRUTO_FINAL", nullable = false, precision = 38, scale = 4)
	private BigDecimal valorSaldoBrutoFinal;

	@Column(name = "POMA_VL_IR", nullable = false, precision = 38, scale = 4)
	private BigDecimal valorIr;

	@Column(name = "POMA_VL_IOF", nullable = false, precision = 38, scale = 4)
	private BigDecimal valorIof;

	@Column(name = "POMA_VL_COME_COTA", nullable = false, precision = 38, scale = 4)
	private BigDecimal valorComeCota;

	public PosicaoMensalAplicacao() {
		super();
	}

	public Aplicacao getAplicacao() {
		return this.aplicacao;
	}

	public void setAplicacao(Aplicacao aplicacao) {
		this.aplicacao = aplicacao;
	}

	public Integer getAno() {
		return this.ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Mes getMes() {
		return this.mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public BigDecimal getValorAplicado() {
		return this.valorAplicado;
	}

	public void setValorAplicado(BigDecimal valorAplicado) {
		this.valorAplicado = valorAplicado;
	}

	public BigDecimal getValorSaldoAnterior() {
		return this.valorSaldoAnterior;
	}

	public void setValorSaldoAnterior(BigDecimal valorSaldoAnterior) {
		this.valorSaldoAnterior = valorSaldoAnterior;
	}

	public BigDecimal getValorSaldoBrutoFinal() {
		return this.valorSaldoBrutoFinal;
	}

	public void setValorSaldoBrutoFinal(BigDecimal valorSaldoBrutoFinal) {
		this.valorSaldoBrutoFinal = valorSaldoBrutoFinal;
	}

	public BigDecimal getValorIr() {
		return this.valorIr;
	}

	public void setValorIr(BigDecimal valorIr) {
		this.valorIr = valorIr;
	}

	public BigDecimal getValorIof() {
		return this.valorIof;
	}

	public void setValorIof(BigDecimal valorIof) {
		this.valorIof = valorIof;
	}

	public BigDecimal getValorComeCota() {
		return this.valorComeCota;
	}

	public void setValorComeCota(BigDecimal valorComeCota) {
		this.valorComeCota = valorComeCota;
	}

	@Override
	protected boolean canEqual(AbstractEntity<?> entity) {
		return entity instanceof PosicaoMensalAplicacao;
	}
}
