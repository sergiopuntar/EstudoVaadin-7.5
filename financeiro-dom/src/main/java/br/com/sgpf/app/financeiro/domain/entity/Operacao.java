package br.com.sgpf.app.financeiro.domain.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sgpf.app.financeiro.domain.valueobject.AmbitoOperacao;
import br.com.sgpf.common.domain.entity.AbstractEntity;
import br.com.sgpf.common.domain.entity.AbstractUUIDEntity;

/**
 * Operação financeira de um valor em um dia.
 */
@Entity
@Table(schema = "SGPF_FNC", name="OPERACAO")
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "OPER_ID_OPERACAO", unique = true, nullable = false, length = 36, columnDefinition = "CHAR")),
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "OPER_DT_CRIACAO", nullable = false)),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "OPER_DT_ATUALIZACAO", nullable = true)),
		@AttributeOverride(name = "versao", column = @Column(name = "OPER_NR_VERSAO", nullable = false, precision = 38))
})
public class Operacao extends AbstractUUIDEntity {
	private static final long serialVersionUID = -4837690337859611033L;
	
	@ManyToOne
	@JoinColumn(name = "GROP_ID_GRUPO_OPERACAO", nullable = false, columnDefinition = "CHAR")
	private GrupoOperacao grupo;

	@Column(name = "OPER_IN_AMBITO", nullable = false, length = 1, columnDefinition = "CHAR")
	@Enumerated(EnumType.STRING)
	private AmbitoOperacao ambito;
	
	@Column(name = "OPER_VL_OPERACAO", nullable = false, precision = 38, scale = 4)
	private BigDecimal valor;

	@Temporal(TemporalType.DATE)
	@Column(name = "OPER_DT_OPERACAO", nullable = false)
	private Date dataOperacao;
	
	@Column(name = "OPER_DS_OPERACAO", nullable = true, length = 4000)
	private String descricao;

	@OneToMany
	@JoinColumn(name = "OPER_ID_OPERACAO", referencedColumnName = "OPER_ID_OPERACAO", columnDefinition = "CHAR")
	private Set<OperacaoConta> contas;

	public Operacao() {
		super();
	}

	public GrupoOperacao getGrupo() {
		return this.grupo;
	}

	public void setGrupo(GrupoOperacao grupo) {
		this.grupo = grupo;
	}
	
	public AmbitoOperacao getAmbito() {
		return this.ambito;
	}
	
	public void setAmbito(AmbitoOperacao ambito) {
		this.ambito = ambito;
	}
	
	public BigDecimal getValor() {
		return this.valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataOperacao() {
		return this.dataOperacao;
	}

	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<OperacaoConta> getContas() {
		return this.contas;
	}

	public void setContas(Set<OperacaoConta> contas) {
		this.contas = contas;
	}

	public OperacaoConta addConta(OperacaoConta conta) {
		getContas().add(conta);
		conta.setOperacao(this);

		return conta;
	}

	public OperacaoConta removeConta(OperacaoConta conta) {
		getContas().remove(conta);
		conta.setOperacao(null);

		return conta;
	}

	@Override
	protected boolean canEqual(AbstractEntity<?> entity) {
		return entity instanceof Operacao;
	}
}
