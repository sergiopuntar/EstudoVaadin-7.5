package br.com.sgpf.app.financeiro.domain.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sgpf.common.domain.entity.AbstractEntity;
import br.com.sgpf.common.domain.entity.AbstractUUIDEntity;

/**
 * Aplicação financeira.
 */
@Entity
@Table(schema = "SGPF_FNC", name="APLICACAO")
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "APLI_ID_APLICACAO", unique = true, nullable = false, length = 36, columnDefinition = "CHAR")),
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "APLI_DT_CRIACAO", nullable = false)),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "APLI_DT_ATUALIZACAO", nullable = true)),
		@AttributeOverride(name = "versao", column = @Column(name = "APLI_NR_VERSAO", nullable = false, precision = 38))
})
public class Aplicacao extends AbstractUUIDEntity {
	private static final long serialVersionUID = -3822439842498056911L;

	@ManyToOne
	@JoinColumn(name = "CONT_ID_CONTA", nullable = false, columnDefinition = "CHAR")
	private Conta conta;
	
	@ManyToOne
	@JoinColumn(name = "TIAP_ID_TIPO_APLICACAO", nullable = false, columnDefinition = "CHAR")
	private TipoAplicacao tipo;
	
	@Column(name = "APLI_NM_APLICACAO", nullable = false, length = 100)
	private String nome;

	@Column(name = "APLI_DS_APLICACAO", nullable = true, length = 4000)
	private String descricao;

	@Temporal(TemporalType.DATE)
	@Column(name = "APLI_DT_VENCIMENTO", nullable = true)
	private Date dataVencimento;

	@OneToMany(mappedBy = "aplicacao")
	private Set<PosicaoMensalAplicacao> posicoes;

	public Aplicacao() {
		super();
	}

	public Conta getConta() {
		return this.conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public TipoAplicacao getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoAplicacao tipo) {
		this.tipo = tipo;
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

	public Date getDataVencimento() {
		return this.dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Set<PosicaoMensalAplicacao> getPosicoes() {
		return this.posicoes;
	}

	public void setPosicoes(Set<PosicaoMensalAplicacao> posicoes) {
		this.posicoes = posicoes;
	}

	public PosicaoMensalAplicacao addPosicoe(PosicaoMensalAplicacao posicoe) {
		getPosicoes().add(posicoe);
		posicoe.setAplicacao(this);

		return posicoe;
	}

	public PosicaoMensalAplicacao removePosicoe(PosicaoMensalAplicacao posicoe) {
		getPosicoes().remove(posicoe);
		posicoe.setAplicacao(null);

		return posicoe;
	}

	@Override
	protected boolean canEqual(AbstractEntity<?> entity) {
		return entity instanceof Aplicacao;
	}
}
