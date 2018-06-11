package lmbenossi.Crawler.Londritech;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lmbenossi.Crawler.CrawlerCatalogo;
import lmbenossi.Crawler.CrawlersProduto;
import lmbenossi.Crawler.HtmlDoc;

public class LondritechCatalogo extends CrawlerCatalogo {
	public LondritechCatalogo(String url) {
		super(url);
	}

	@Override
	protected CrawlersProduto crawlPagesCatalogo() {
		CrawlersProduto crawlers = new CrawlersProduto();
		
		String url = super.url;
		while(url != null) {
			Document htmlDoc = HtmlDoc.getHtmlDoc(url);
			
			Elements hrefs = htmlDoc.body()
					.select("body > div.wrap > div > div > div> div > section > div > div > div > article > header > a.showcase-product__link_title");
			for(Element href : hrefs) {
				String value = "http://londritech.com.br" + href.attr("href");
				crawlers.add(new LondritechProduto(value));
				System.out.println(value);
			}
			
			url = getNextPage(htmlDoc);
		}
		
		return crawlers;
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
