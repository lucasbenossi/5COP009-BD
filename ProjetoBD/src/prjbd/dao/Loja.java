package prjbd.dao;

public enum Loja {
	PICHAU("Pichau"),
	LONDRITECH("Londritech");
	
	private String nome;
	
	private Loja(String nome) {
		this.nome = nome;
	}
	
	public String nome() {
		return this.nome;
	}
}
