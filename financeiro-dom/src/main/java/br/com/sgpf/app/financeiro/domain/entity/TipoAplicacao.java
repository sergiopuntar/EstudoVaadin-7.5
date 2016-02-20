package br.com.sgpf.app.financeiro.domain.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.sgpf.common.domain.entity.AbstractEntity;
import br.com.sgpf.common.domain.entity.AbstractUUIDEntity;

/**
 * Tipo de aplicação financeira.
 */
@Entity
@Table(schema = "SGPF_FNC", name="TIPO_APLICACAO")
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "TIAP_ID_TIPO_APLICACAO", unique = true, nullable = false, length = 36, columnDefinition = "CHAR")),
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "TIAP_DT_CRIACAO", nullable = false)),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "TIAP_DT_ATUALIZACAO", nullable = true)),
		@AttributeOverride(name = "versao", column = @Column(name = "TIAP_NR_VERSAO", nullable = false, precision = 38))
})
public class TipoAplicacao extends AbstractUUIDEntity {
	private static final long serialVersionUID = 2124505339015646603L;
	
	@NotNull(message = "{tipo_aplicacao.nome.not_null}")
	@Size(message = "{tipo_aplicacao.nome.size}", min = 1,  max = 100)
	@Column(name = "TIAP_NM_TIPO_APLICACAO", nullable = false, length = 100)
	private String nome;

	public TipoAplicacao() {
		super();
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	protected boolean canEqual(AbstractEntity<?> entity) {
		return entity instanceof TipoAplicacao;
	}
}
