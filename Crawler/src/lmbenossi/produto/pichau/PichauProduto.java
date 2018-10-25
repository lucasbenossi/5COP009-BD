package lmbenossi.produto.pichau;

import java.math.BigDecimal;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lmbenossi.crawler.HtmlDoc;
import lmbenossi.produto.CrawlerProduto;
import lmbenossi.produto.Loja;
import lmbenossi.produto.Produto;
import lmbenossi.produto.ProdutoAdapter;

public class PichauProduto extends CrawlerProduto {
	public PichauProduto(String url) {
		super(url);
	}

	@Override
	public Produto crawl() {
		Document htmlDoc = HtmlDoc.getHtmlDoc(super.url);
		
		Element infosProduto = htmlDoc.body().selectFirst("#maincontent > div.columns > div > div.product-info-main");
		
		String nome = infosProduto.select("h1").text();
		
		BigDecimal preco = Produto.parsePreco(infosProduto.selectFirst("div.product-info-price > div.price-box.price-final_price > span.price-container.price-final_price.tax.weee > span.price-wrapper").text());
		
		int parcelas = 10;
		
		BigDecimal valorParcela = Produto.parsePreco(infosProduto.selectFirst("div.product-info-price > div.price-box.price-final_price > span.price-container.price-final_price.tax.weee > span.price-installments > span").text());
		
		boolean disponivel = true;
		
		if(infosProduto.selectFirst("div.product-info-price > div.product-info-stock-sku > div.stock.available") != null) {
			disponivel = true;
		}
		else if(infosProduto.selectFirst("div.product-info-price > div.product-info-stock-sku > div.stock.unavailable") != null) {
			disponivel = false;
		}
		
		System.out.println(nome);
		
		return new Produto(nome, preco, parcelas, valorParcela, disponivel, Loja.PICHAU);
	}
	
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Produto.class, new ProdutoAdapter()).create();
		for(String url : args) {
			PichauProduto crawler = new PichauProduto(url);
			Produto produto = crawler.crawl();
			System.out.println(gson.toJson(produto, Produto.class));
		}
	}
}
