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
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public BigDecimal getPreco() {
		return this.preco;
	}
	
	public int getParcelas() {
		return this.parcelas;
	}
	
	public BigDecimal getValorParcela() {
		return this.valorParcela;
	}
	
	public int getIdLoja() {
		return this.idLoja;
	}
	
	public String getUrl() {
		return this.url;
	}
}
