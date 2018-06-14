package lmbenossi.Crawler.Londritech;

import java.math.BigDecimal;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import lmbenossi.Crawler.CrawlerProduto;
import lmbenossi.Crawler.HtmlDoc;
import lmbenossi.Crawler.Loja;
import lmbenossi.Crawler.Produto;

public class LondritechProduto extends CrawlerProduto {
	public LondritechProduto(String url) {
		super(url);
	}
	
	@Override
	public Produto crawl() {
		Document htmlDoc = HtmlDoc.getHtmlDoc(super.url);
		Element body = htmlDoc.body();
		
		Produto produto = null;
		
		try {
			Element articleProduto = body.selectFirst("body > div.wrap > div > div.row > div > div > article.product");
			
			String nome = articleProduto.selectFirst("header.product-header").text().trim();
			
			Element elementPreco = articleProduto.selectFirst("div > div.row > div > div > div.product-price > div.product-price-primary");
			BigDecimal preco = null;
			if(elementPreco != null) {
				preco = Produto.parsePreco(elementPreco.text().trim());
			}
			else {
				elementPreco = articleProduto.selectFirst("div > div.row > div > div > div.product-price > div.showcase-prices-unavailable");
				if(elementPreco.text().trim().equals("Sob consulta")) {
					throw new Exception("Produto sob consulta");
				}
			}
			
			int parcelas = 10;
			BigDecimal valorParcela = Produto.parsePreco(articleProduto.selectFirst("div > div.row > div > div > div.product-price > div.product-price-secondary > p > small").text().trim());
			
			boolean disponivel = true;
			String disponibilidade = articleProduto.selectFirst("div > div.row > div > div > div.product-stock > p > span").text().trim();
			if(disponibilidade.equals("Em estoque")) {
				disponivel = true;
			}
			
			produto = new Produto(nome, preco, parcelas, valorParcela, disponivel, Loja.LONDRITECH);
			
			System.out.println(nome);
		} catch (Exception e) {
			System.err.println("ERRO: " + super.url + " " + e);
		}
		
		return produto;
	}
}
