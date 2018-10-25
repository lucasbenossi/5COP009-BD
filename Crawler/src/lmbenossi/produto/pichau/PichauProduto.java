package lmbenossi.produto.pichau;

import java.math.BigDecimal;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import lmbenossi.crawler.HtmlDoc;
import lmbenossi.produto.CrawlerProduto;
import lmbenossi.produto.Loja;
import lmbenossi.produto.Produto;

public class PichauProduto extends CrawlerProduto {
	public PichauProduto(String url) {
		super(url);
	}

	@Override
	public Produto crawl() {
		Document htmlDoc = HtmlDoc.getHtmlDoc(super.url);
		
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
