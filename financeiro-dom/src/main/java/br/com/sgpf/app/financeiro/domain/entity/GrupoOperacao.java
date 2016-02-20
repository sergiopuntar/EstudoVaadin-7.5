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
 * Grupo para organização de operação.
 */
@Entity
@Table(schema = "SGPF_FNC", name="GRUPO_OPERACAO")
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "GROP_ID_GRUPO_OPERACAO", unique = true, nullable = false, length = 36, columnDefinition = "CHAR")),
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "GROP_DT_CRIACAO", nullable = false)),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "GROP_DT_ATUALIZACAO", nullable = true)),
		@AttributeOverride(name = "versao", column = @Column(name = "GROP_NR_VERSAO", nullable = false, precision = 38))
})
public class GrupoOperacao extends AbstractUUIDEntity {
	private static final long serialVersionUID = 180963376734066039L;
	
	@NotNull(message = "{grupo_operacao.nome.not_null}")
	@Size(message = "{grupo_operacao.nome.size}", min = 1,  max = 100)
	@Column(name = "GROP_NM_GRUPO_OPERACAO", nullable = false, length = 100)
	private String nome;

	public GrupoOperacao() {
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
		return entity instanceof GrupoOperacao;
	}
}
