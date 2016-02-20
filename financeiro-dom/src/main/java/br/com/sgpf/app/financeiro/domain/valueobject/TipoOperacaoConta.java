package br.com.sgpf.app.financeiro.domain.valueobject;

/**
 * Enumeração de tipos de operação em uma conta.
 */
public enum TipoOperacaoConta {
	
	/**
	 * Indica um crédito realizado na conta.
	 */
	C ("Crédito"),
	
	/**
	 * Indica um débito realizado na conta.
	 */
	D ("Débito");
	
	private String nome;

	private TipoOperacaoConta(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
