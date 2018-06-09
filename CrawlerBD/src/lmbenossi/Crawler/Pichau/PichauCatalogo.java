package lmbenossi.Crawler.Pichau;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lmbenossi.Crawler.CrawlerCatalogo;
import lmbenossi.Crawler.CrawlersProduto;
import lmbenossi.Crawler.HtmlDoc;

public class PichauCatalogo extends CrawlerCatalogo {
	public PichauCatalogo(String url) {
		super(url);
	}

	@Override
	protected CrawlersProduto crawlPagesCatalogo() {
		CrawlersProduto crawlers = new CrawlersProduto();
		
		String url = super.url;
		while(url != null) {
			Document htmlDoc = HtmlDoc.getHtmlDoc(url);
			
			Element body = htmlDoc.body();
			Element boxProdutos = body.selectFirst("#content-main > div.wrapper.clearfix > div.col-right > article > section");
			Elements hrefs = boxProdutos.select("ul.clearfix.linha-produtos > li.item > h4.title > a");
			for(Element href : hrefs) {
				String value = href.attr("href");
				crawlers.add(new PichauProduto(value));
				System.out.println(value);
			}
			
			url = getNextPage(htmlDoc);
		}
		
		return crawlers;
	}

	@Override
	protected String getNextPage(Document htmlPage) {
		Element nextButton = htmlPage.body().selectFirst("#content-main > div.wrapper.clearfix > div.col-right > article > header > div.toolbar > div.pager > ul > li > a.bt-next");
		
		if(nextButton != null) {
			return nextButton.attr("href");
		}
		return null;
	}
}
