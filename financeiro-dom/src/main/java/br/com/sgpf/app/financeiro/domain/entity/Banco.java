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
 * Banco de relacionamento do usuário.
 */
@Entity
@Table(schema = "SGPF_FNC", name = "BANCO")
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "BANC_ID_BANCO", unique = true, nullable = false, length = 36, columnDefinition = "CHAR")),
		@AttributeOverride(name = "dataCriacao", column = @Column(name = "BANC_DT_CRIACAO", nullable = false)),
		@AttributeOverride(name = "dataAtualizacao", column = @Column(name = "BANC_DT_ATUALIZACAO", nullable = true)),
		@AttributeOverride(name = "versao", column = @Column(name = "BANC_NR_VERSAO", nullable = false, precision = 38))
})
public class Banco extends AbstractUUIDEntity {
	private static final long serialVersionUID = 6932821423485459712L;
	
	@NotNull(message = "{banco.sigla.not_null}")
	@Size(message = "{banco.sigla.size}", min = 1,  max = 10)
	@Column(name = "BANC_SG_BANCO", nullable = false, length = 10)
	private String sigla;

	@NotNull(message = "{banco.nome.not_null}")
	@Size(message = "{banco.nome.size}", min = 1,  max = 100)
	@Column(name = "BANC_NM_BANCO", nullable = false, length = 100)
	private String nome;

	public Banco() {
		super();
	}
	
	public String getSigla() {
		return this.sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	protected boolean canEqual(AbstractEntity<?> entity) {
		return entity instanceof Banco;
	}
}
