package lmbenossi.Crawler.Pichau;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lmbenossi.Crawler.CrawlerCatalogo;
import lmbenossi.Crawler.HtmlDoc;
import lmbenossi.Crawler.SyncList;

public class PichauCatalogo extends CrawlerCatalogo {
	public PichauCatalogo(String url, SyncList<String> urls) {
		super(url, urls);
	}

	@Override
	protected void crawlPagesCatalogo() {
		String url = super.url;
		while(url != null) {
			Document htmlDoc = HtmlDoc.getHtmlDoc(url);
			Element body = htmlDoc.body();
			Element boxProdutos = body.selectFirst("#content-main > div.wrapper.clearfix > div.col-right > article > section");
			Elements hrefs = boxProdutos.select("ul.clearfix.linha-produtos > li.item > h4.title > a");
			for(Element href : hrefs) {
				String value = href.attr("href");
				super.urls.add(value);
				System.out.println(value);
			}
			
			url = getNextPage(htmlDoc);
		}
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
