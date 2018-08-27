package prjbd.model;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Produto {
	private int id;
	private String nome;
	private BigDecimal preco;
	private int parcelas;
	private BigDecimal valorParcela;
	private boolean disponivel;
	private String loja;
	
	public Produto(int id, String nome, BigDecimal preco, int parcelas, BigDecimal valorParcela, boolean disponivel, String loja) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.parcelas = parcelas;
		this.valorParcela = valorParcela;
		this.disponivel = disponivel;
		this.loja = loja;
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
	
	public boolean disponivel() {
		return this.disponivel;
	}
	public void disponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	public String loja() {
		return this.loja;
	}
	public void loja(String loja) {
		this.loja = loja;
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
		out.println(id);
		out.println(nome);
		out.println(preco);
		out.println(parcelas);
		out.println(valorParcela);
		out.println(disponivel);
		out.println(loja);
	}
	public void print() {
		print(System.out);
	}
}
