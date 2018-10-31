package lmbenossi.produto.pichau;

import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lmbenossi.crawler.HtmlDoc;
import lmbenossi.produto.CrawlerCatalogo;

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
			Element boxProdutos = body.selectFirst("#maincontent > div.columns > div.column.main > div.products.wrapper.grid.products-grid");
			Elements hrefs = boxProdutos.select("ol > li > div > div > strong > a");
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
		Element nextButton = htmlPage.body().selectFirst("div.columns > div.column.main > div.toolbar.toolbar-products > div.pages > ul > li.item.pages-item-next > a");
		
		if(nextButton != null) {
			return nextButton.attr("href");
		}
		return null;
	}
}
