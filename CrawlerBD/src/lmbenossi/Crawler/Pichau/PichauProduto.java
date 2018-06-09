package lmbenossi.Crawler.Pichau;

import java.io.IOException;
import java.math.BigDecimal;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import lmbenossi.Crawler.CrawlerProduto;
import lmbenossi.Crawler.Loja;
import lmbenossi.Crawler.Produto;

public class PichauProduto implements CrawlerProduto {
	private String url;
	
	public PichauProduto(String url) {
		this.url = url;
	}

	@Override
	public Produto crawl() {
		Connection connection = Jsoup.connect(url);
		Document htmlDoc = null;
		try {
			htmlDoc = connection.get();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		Element infosProduto = htmlDoc.body().selectFirst("#content-main > article.wrapper > div.product-details.clearfix > div.col-right");
		String nome = infosProduto.select("h2").text();
		BigDecimal preco = Produto.parsePreco(infosProduto.selectFirst("div.product-payment > ul > li.boleto > span.valor").text());
		int parcelas = 10;
		BigDecimal valorParcela = Produto.parsePreco(infosProduto.selectFirst("div.product-payment > ul > li.other > span.pricex").text());
		boolean disponivel = true;
		
		if(infosProduto.selectFirst("div.product-stock.clearfix > span.availability.disponivel") != null) {
			disponivel = true;
		}
		else if(infosProduto.selectFirst("div.product-stock.clearfix > span.availability.indisponivel") != null) {
			disponivel = false;
		}
		
		System.out.println(nome);
		
		return new Produto(nome, preco, parcelas, valorParcela, disponivel, Loja.PICHAU);
	}
}
