package br.com.sgpf.app.financeiro.domain.valueobject;

/**
 * Enumeração de meses.
 */
public enum Mes {
	
	/**
	 * Janeiro
	 */
	JANEIRO ("Janeiro"),
	
	/**
	 * Fevereiro
	 */
	FEVEREIRO ("Fevereiro"),
	
	/**
	 * Março
	 */
	MARCO ("Março"),
	
	/**
	 * Abril
	 */
	ABRIL ("Abril"),
	
	/**
	 * Maio
	 */
	MAIO ("Maio"),
	
	/**
	 * Junho
	 */
	JUNHO ("Junho"),
	
	/**
	 * Julho
	 */
	JULHO ("Julho"),
	
	/**
	 * Agosto
	 */
	AGOSTO ("Agosto"),
	
	/**
	 * Setembro
	 */
	SETEMBRO ("Setembro"),
	
	/**
	 * Outubro
	 */
	OUTUBRO ("Outubro"),
	
	/**
	 * Novembro
	 */
	NOVEMBRO ("Novembro"),
	
	/**
	 * Dezembro
	 */
	DEZEMBRO ("Dezembro");
	
	private String nome;

	private Mes(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
