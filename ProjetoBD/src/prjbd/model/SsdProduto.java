package prjbd.model;

import java.math.BigDecimal;

public class SsdProduto {
	private Produto produto;
	private String nomeLoja;
	private BigDecimal precoPorGiga;
	
	public SsdProduto(Produto produto, String nomeLoja, BigDecimal precoPorGiga) {
		this.produto = produto;
		this.nomeLoja = nomeLoja;
		this.precoPorGiga = precoPorGiga;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public BigDecimal getPrecoPorGiga() {
		return precoPorGiga;
	}
	public void setPrecoPorGiga(BigDecimal precoPorGiga) {
		this.precoPorGiga = precoPorGiga;
	}
	
}
