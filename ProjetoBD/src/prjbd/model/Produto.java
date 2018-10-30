package prjbd.model;

import java.math.BigDecimal;

public class Produto {
	private int id;
	private String nome;
	private BigDecimal preco;
	private int parcelas;
	private BigDecimal valorParcela;
	private int idLoja;
	private String url;
	
	public Produto(int id, String nome, BigDecimal preco, int parcelas, BigDecimal valorParcela, int idLoja, String url) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.parcelas = parcelas;
		this.valorParcela = valorParcela;
		this.idLoja = idLoja;
		this.url = url;
	}
	
	public int id() {
		return this.id;
	}
	public void id(int id) {
		this.id = id;
	}
	
	public String nome() {
		return this.nome;
	}
	public void nome(String nome) {
		this.nome = nome;
	}
	
	public BigDecimal preco() {
		return this.preco;
	}
	public void preco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public int parcelas() {
		return this.parcelas;
	}
	public void parcelas(int parcelas) {
		this.parcelas = parcelas;
	}
	
	public BigDecimal valorParcela() {
		return this.valorParcela;
	}
	public void valorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}
	
	public int idLoja() {
		return this.idLoja;
	}
	public void idLoja(int idLoja) {
		this.idLoja = idLoja;
	}
	
	public String url() {
		return this.url;
	}
}
