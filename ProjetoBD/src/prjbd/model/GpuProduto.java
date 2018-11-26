package prjbd.model;

import java.math.BigDecimal;

public class GpuProduto {
	private Produto produto;
	private Gpu gpu;
	private BigDecimal precoPorPerformance;
	private String nomeLoja;
	
	public GpuProduto(Produto produto, Gpu gpu, BigDecimal precoPorPerformance, String nomeLoja) {
		this.produto = produto;
		this.gpu = gpu;
		this.precoPorPerformance = precoPorPerformance;
		this.nomeLoja = nomeLoja;
	}
	
	public BigDecimal getPrecoPorPerformance() {
		return precoPorPerformance;
	}
	public void setPrecoPorPerformance(BigDecimal precoPorPerformance) {
		this.precoPorPerformance = precoPorPerformance;
	}

	public Produto getProduto() {
		return produto;
	}

	public Gpu getGpu() {
		return gpu;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}
}
