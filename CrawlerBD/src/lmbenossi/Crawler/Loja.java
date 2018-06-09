package lmbenossi.Crawler;

public enum Loja {
	PICHAU("Pichau");
	
	private String nome;
	
	private Loja(String nome) {
		this.nome = nome;
	}
	
	public String nome() {
		return this.nome;
	}
}
