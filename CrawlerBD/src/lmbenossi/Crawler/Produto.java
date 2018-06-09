package lmbenossi.Crawler;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Produto {
	private String nome;
	private BigDecimal preco;
	private int parcelas;
	private BigDecimal valorParcela;
	private boolean disponivel;
	private Loja loja;
	
	public Produto(String nome, BigDecimal preco, int parcelas, BigDecimal valorParcela, boolean disponivel, Loja loja) {
		this.nome = nome;
		this.preco = preco;
		this.parcelas = parcelas;
		this.valorParcela = valorParcela;
		this.disponivel = disponivel;
		this.loja = loja;
	}
	
	public String nome() {
		return this.nome;
	}
	
	public BigDecimal preco() {
		return this.preco;
	}
	
	public int parcelas() {
		return this.parcelas;
	}
	
	public BigDecimal valorParcela() {
		return this.valorParcela;
	}
	
	public boolean disponivel() {
		return this.disponivel;
	}
	
	public Loja loja() {
		return this.loja;
	}
	
	public static BigDecimal parsePreco(String input) {
		Pattern regex = Pattern.compile("[0-9.]*,[0-9]{2}");
		Matcher matcher = regex.matcher(input);
		
		String val = null;
		if(matcher.find()) {
			val = matcher.group();
			val = val.replace(".", "").replace(',', '.');
		}
		return new BigDecimal(val);
	}
	
	public void print(PrintStream out) {
		out.println(nome);
		out.println(preco);
		out.println(parcelas);
		out.println(valorParcela);
		out.println(disponivel);
		out.println(loja.nome());
	}
	public void print() {
		print(System.out);
	}
}
