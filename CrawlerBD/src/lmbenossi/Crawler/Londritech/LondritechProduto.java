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
		
		Element campoProduto = body
				.selectFirst("body > div.wrap > div > div.row > div.col-sm-12 > div.product-main > article.product");
		
		String nome = campoProduto
				.selectFirst("header.product-header").text().trim();
		
		BigDecimal preco = Produto.parsePreco(campoProduto
				.selectFirst("div > div.row > div > div > div.product-price > div.product-price-primary").text().trim());
		
		int parcelas = 10;
		
		BigDecimal valorParcela = Produto.parsePreco(campoProduto
				.selectFirst("div > div.row > div > div > div.product-price > div.product-price-secondary > p > small").text().trim());
		
		boolean disponivel = false;
		if(campoProduto.selectFirst("div > div.row > div > div > div.product-stock > p > span").text().equals("Em estoque")) {
			disponivel = true;
		}
		
		System.out.println(nome);
		
		return new Produto(nome, preco, parcelas, valorParcela, disponivel, Loja.LONDRITECH);
	}

}
