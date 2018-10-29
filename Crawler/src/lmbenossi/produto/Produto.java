package lmbenossi.produto;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Produto {
	private String nome;
	private BigDecimal preco;
	private int parcelas;
	private BigDecimal valorParcela;
	private int idLoja;
	private String url;
	
	public Produto(String nome, BigDecimal preco, int parcelas, BigDecimal valorParcela, int idLoja, String url) {
		this.nome = nome;
		this.preco = preco;
		this.parcelas = parcelas;
		this.valorParcela = valorParcela;
		this.idLoja = idLoja;
		this.url = url;
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
	
	public int idLoja() {
		return this.idLoja;
	}
	
	public String url() {
		return this.url;
	}
	
	public static BigDecimal parsePreco(String input) {
		Pattern regex1 = Pattern.compile("[0-9.]*,[0-9]{2}");
		Pattern regex2 = Pattern.compile("[0-9]*\\.[0-9]{2}");

		Matcher matcher1 = regex1.matcher(input);
		Matcher matcher2 = regex2.matcher(input);
		
		String val = null;
		if(matcher1.find()) {
			val = matcher1.group();
			val = val.replace(".", "").replace(',', '.');
		}
		else if(matcher2.find()) {
			val = matcher2.group();
		}
		return new BigDecimal(val);
	}
	
	public static int parseParcelas(String input) {
		Pattern regex = Pattern.compile("([0-9]{1,2})x de");
		Matcher matcher = regex.matcher(input);
		
		if(matcher.find()) {
			return Integer.parseInt(matcher.group(1));
		}
		return -1;
	}
}
