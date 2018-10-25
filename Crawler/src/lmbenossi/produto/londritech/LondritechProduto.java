package lmbenossi.produto.londritech;

import java.math.BigDecimal;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import lmbenossi.crawler.HtmlDoc;
import lmbenossi.produto.CrawlerProduto;
import lmbenossi.produto.Loja;
import lmbenossi.produto.Produto;

public class LondritechProduto extends CrawlerProduto {
	public LondritechProduto(String url) {
		super(url);
	}
	
	@Override
	public Produto crawl() {
		Document htmlDoc = HtmlDoc.getHtmlDoc(super.url);
		Element body = htmlDoc.body();
		
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
				return null;
			}
		}
		
		int parcelas = 10;
		
		Element elementValorParcela = articleProduto.selectFirst("div > div.row > div > div > div.product-price > div.product-price-secondary > p > small");
		BigDecimal valorParcela = null;
		if(elementValorParcela != null) {
			valorParcela = Produto.parsePreco(elementValorParcela.text().trim());
		}
		else {
			valorParcela = new BigDecimal(0);
		}
		
		boolean disponivel = false;
		String disponibilidade = articleProduto.selectFirst("div > div.row > div > div > div.product-stock > p > span").text().trim();
		if(disponibilidade.equals("Em estoque")) {
			disponivel = true;
		}
		
		Produto produto = new Produto(nome, preco, parcelas, valorParcela, disponivel, Loja.LONDRITECH);
		
		System.out.println(nome);
		
		return produto;
	}
}
