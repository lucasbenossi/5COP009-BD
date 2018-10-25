package lmbenossi.produto.londritech;

import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lmbenossi.crawler.HtmlDoc;
import lmbenossi.produto.CrawlerCatalogo;

public class LondritechCatalogo extends CrawlerCatalogo {
	public LondritechCatalogo(String url) {
		super(url);
	}

	@Override
	public String[] crawl() {
		LinkedList<String> urls = new LinkedList<>();
		String url = super.url;
		while(url != null) {
			Document htmlDoc = HtmlDoc.getHtmlDoc(url);
			
			Elements divsProductResult = htmlDoc.body().select("body > div.wrap > div.container > div.row > div.search-content > div.product-main.search-results > section > div > div > div.product-result");
			Elements hrefs = divsProductResult.select("a.showcase-product__link_title");
			for(Element href : hrefs) {
				String value = "http://londritech.com.br" + href.attr("href");
				urls.add(value);
				System.out.println(value);
			}
			
			url = getNextPage(htmlDoc);
		}
		
		return urls.toArray(new String[urls.size()]);
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
