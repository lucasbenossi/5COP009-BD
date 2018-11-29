package prjbd.model;

import java.math.BigDecimal;

public class PrecoPorPerformance {
	private int id;
	private String name;
	private String url;
	private BigDecimal preco;
	private int score;
	private String nomeLoja;
	private BigDecimal precoPorPerformance;
	
	public PrecoPorPerformance(int id, String name, String url, BigDecimal preco, int score, String nomeLoja,
			BigDecimal precoPorPerformance) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.preco = preco;
		this.score = score;
		this.nomeLoja = nomeLoja;
		this.precoPorPerformance = precoPorPerformance;
	}
	
	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public int getScore() {
		return score;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public BigDecimal getPrecoPorPerformance() {
		return precoPorPerformance;
	}
}
