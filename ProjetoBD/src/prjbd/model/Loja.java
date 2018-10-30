package prjbd.model;

public class Loja {
	private int id;
	private String nome;
	private String url;
	
	public Loja(int id, String nome, String url) {
		this.id = id;
		this.nome = nome;
		this.url = url;
	}
	
	public int id() {
		return this.id;
	}
	
	public String nome() {
		return this.nome;
	}
	
	public String url() {
		return this.url;
	}
}
