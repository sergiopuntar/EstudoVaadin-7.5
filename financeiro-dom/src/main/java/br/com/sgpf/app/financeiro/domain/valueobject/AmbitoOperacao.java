package br.com.sgpf.app.financeiro.domain.valueobject;

/**
 * Enumeração de âmbitos de uma operação.
 */
public enum AmbitoOperacao {
	
	/**
	 * Indica que a operação ocorreu internamente (entre contas do usuário).
	 */
	I ("Interno"),
	
	/**
	 * Indica que a operação ocorreu externamente (entre uma conta do usuário e uma conta externa).
	 */
	E ("Externo");
	
	private String nome;

	private AmbitoOperacao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
