package lmbenossi.Crawler.Newegg;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lmbenossi.Crawler.CrawlerProduto;
import lmbenossi.Crawler.HtmlDoc;
import lmbenossi.Crawler.Produto;

public class NeweggProduto extends CrawlerProduto {
	public NeweggProduto(String url) {
		super(url);
	}

	@Override
	public Produto crawl() {
		Document htmlDoc = HtmlDoc.getHtmlDoc(super.url);
		
		Element body = htmlDoc.body();
		String nome = body.selectFirst("#synopsis > div.grpArticle > div > div.wrapper").text().trim();
		Elements price = body.select("price-current");
		
		System.out.println(nome + " " + price);
		
		return null;
	}
	
}
