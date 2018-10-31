package lmbenossi.produto.pichau;

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

public class PichauProduto extends CrawlerProduto {
	public PichauProduto(String url) {
		super(url);
	}

	@Override
	public Produto crawl() {
		try {
			Document htmlDoc = HtmlDoc.getHtmlDoc(super.url);
			
			Element infosProduto = htmlDoc.body().selectFirst("#maincontent > div.columns > div > div.product-info-main");
			
			String nome = infosProduto.select("h1").text();
			
			Element precoBox = infosProduto.selectFirst("div.product-info-price > div.price-box.price-final_price");
			
			Element precoAVista = precoBox.selectFirst("span.price-boleto > span");
			if(precoAVista == null) {
				precoAVista = precoBox.selectFirst("span.special-price > span.price-boleto > span");
			}
			
			BigDecimal preco = Produto.parsePreco(precoAVista.text());
			
			Element precoAPrazo = precoBox.selectFirst("span.price-container.price-final_price.tax.weee > span.price-installments > span");
			if(precoAPrazo == null) {
				precoAPrazo = precoBox.selectFirst("span.special-price > span.price-container.price-final_price.tax.weee > span.price-installments > span");
			}
			
			int parcelas = Produto.parseParcelas(precoAPrazo.text());
			
			BigDecimal valorParcela = Produto.parsePreco(precoAPrazo.text());
			
			System.out.println(nome);
			
			return new Produto(nome, preco, parcelas, valorParcela, Loja.PICHAU.ordinal(), this.url);
		} catch (Exception e) {
			Globals.urlErros.add(this.url);
			e.printStackTrace();
		}
		return null;
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
