package lmbenossi.pichau;

import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lmbenossi.crawler.CrawlerCatalogo;
import lmbenossi.crawler.HtmlDoc;

public class PichauCatalogo extends CrawlerCatalogo {
	public PichauCatalogo(String url) {
		super(url);
	}

	@Override
	public String[] crawl() {
		LinkedList<String> urls = new LinkedList<>();
		String url = super.url;
		while(url != null) {
			Document htmlDoc = HtmlDoc.getHtmlDoc(url);
			Element body = htmlDoc.body();
			Element boxProdutos = body.selectFirst("#content-main > div.wrapper.clearfix > div.col-right > article > section");
			Elements hrefs = boxProdutos.select("ul.clearfix.linha-produtos > li.item > h4.title > a");
			for(Element href : hrefs) {
				String value = href.attr("href");
				urls.add(value);
				System.out.println(value);
			}
			
			url = getNextPage(htmlDoc);
		}
		
		return urls.toArray(new String[urls.size()]);
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
