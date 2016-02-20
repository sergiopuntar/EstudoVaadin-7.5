package br.com.sgpf.app.financeiro.domain.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.com.sgpf.app.financeiro.domain.valueobject.TipoOperacaoConta;
import br.com.sgpf.common.domain.entity.AbstractEntity;

/**
 * Operação financeira realizada em uma conta de relacionamento.
 */
@Entity
@Table(schema = "SGPF_FNC", name="OPERACAO_CONTA")
@AttributeOverrides(value = {
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "OPCO_DT_CRIACAO", nullable = false)),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "OPCO_DT_ATUALIZACAO", nullable = true)),
		@AttributeOverride(name = "versao", column = @Column(name = "OPCO_NR_VERSAO", nullable = false, precision = 38))
})
public class OperacaoConta extends AbstractEntity<OperacaoContaPK> {
	private static final long serialVersionUID = 2690564741095966840L;

	@EmbeddedId
	private OperacaoContaPK id;

	@Column(name = "OPCO_IN_TIPO", nullable = false, length = 1, columnDefinition = "CHAR")
	@Enumerated(EnumType.STRING)
	private TipoOperacaoConta tipo;

	public OperacaoConta() {
		super();
		this.id = new OperacaoContaPK();
	}

	public OperacaoContaPK getId() {
		return this.id;
	}

	public void setId(OperacaoContaPK id) {
		this.id = id;
	}

	public TipoOperacaoConta getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoOperacaoConta tipo) {
		this.tipo = tipo;
	}

	public Conta getConta() {
		return this.getId().getConta();
	}

	public void setConta(Conta conta) {
		this.getId().setConta(conta);
	}

	public Operacao getOperacao() {
		return this.getId().getOperacao();
	}

	public void setOperacao(Operacao operacao) {
		this.getId().setOperacao(operacao);
	}

	@Override
	protected boolean canEqual(AbstractEntity<?> entity) {
		return entity instanceof OperacaoConta;
	}
}
