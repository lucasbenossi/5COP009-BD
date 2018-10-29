package lmbenossi.produto.londritech;

import java.math.BigDecimal;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lmbenossi.crawler.HtmlDoc;
import lmbenossi.main.Globals;
import lmbenossi.produto.CrawlerProduto;
import lmbenossi.produto.Loja;
import lmbenossi.produto.Produto;
import lmbenossi.produto.ProdutoAdapter;

public class LondritechProduto extends CrawlerProduto {
	public LondritechProduto(String url) {
		super(url);
	}
	
	@Override
	public Produto crawl() {
		try {
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
			
			Element elementParcelas = articleProduto.selectFirst("div > div.row > div > div > div.product-price > div.product-price-secondary > p > small");
			int parcelas = -1;
			BigDecimal valorParcela = new BigDecimal(-1);
			if(elementParcelas != null) {
				String stringParcelas = elementParcelas.text();
				parcelas = Produto.parseParcelas(stringParcelas);
				valorParcela = Produto.parsePreco(stringParcelas);
			}
			
			Produto produto = new Produto(nome, preco, parcelas, valorParcela, Loja.LONDRITECH.ordinal(), this.url);
			
			System.out.println(nome);
			
			return produto;
		} catch (Exception e) {
			Globals.urlErros.add(this.url);
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Produto.class, new ProdutoAdapter()).create();
		for(String url : args) {
			LondritechProduto crawler = new LondritechProduto(url);
			Produto produto = crawler.crawl();
			System.out.println(gson.toJson(produto, Produto.class));
		}
	}
}
