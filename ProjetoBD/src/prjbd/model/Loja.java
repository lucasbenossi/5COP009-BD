package prjbd.model;

public class Loja {
	private int id;
	private String nome;
	
	public Loja(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public int id() {
		return this.id;
	}
	
	public String nome() {
		return this.nome;
	}
}
