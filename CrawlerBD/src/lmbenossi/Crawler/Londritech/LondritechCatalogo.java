package lmbenossi.Crawler.Londritech;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lmbenossi.Crawler.CrawlerCatalogo;
import lmbenossi.Crawler.HtmlDoc;
import lmbenossi.Crawler.SyncList;

public class LondritechCatalogo extends CrawlerCatalogo {
	public LondritechCatalogo(String url, SyncList<String> urls) {
		super(url, urls);
	}

	@Override
	protected void crawlPagesCatalogo() {
		String url = super.url;
		while(url != null) {
			Document htmlDoc = HtmlDoc.getHtmlDoc(url);
			
			Elements divsProductResult = htmlDoc.body().select("body > div.wrap > div.container > div.row > div.search-content > div.product-main.search-results > section > div > div > div.product-result");
			Elements hrefs = divsProductResult.select("a.showcase-product__link_title");
			for(Element href : hrefs) {
				String value = "http://londritech.com.br" + href.attr("href");
				super.urls.add(value);
				System.out.println(value);
			}
			
			url = getNextPage(htmlDoc);
		}
	}

	@Override
	protected String getNextPage(Document currentPage) {
		Element body = currentPage.body();
		Element nextButton = body.selectFirst("li.search-options-pagination__item.search-options-pagination__item_next > a");
		
		if(nextButton != null) {
			String url = nextButton.attr("href");
			if(! url.startsWith("javascript")) {
				return url;
			}
		}
		
		return null;
	}

}
