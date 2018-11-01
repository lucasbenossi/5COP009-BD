package prjbd.model;

import java.math.BigDecimal;

public class CpuProduto {
	private Produto produto;
	private Cpu cpu;
	private BigDecimal precoPorPerformance;
	private String nomeLoja;
	
	public CpuProduto(Produto produto, Cpu cpu, BigDecimal precoPorPerformance, String nomeLoja) {
		this.produto = produto;
		this.cpu = cpu;
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

	public Cpu getCpu() {
		return cpu;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

}
