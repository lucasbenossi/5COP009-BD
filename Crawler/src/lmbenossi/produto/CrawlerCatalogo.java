package lmbenossi.produto;

import org.jsoup.nodes.Document;

import lmbenossi.crawler.Crawler;

public abstract class CrawlerCatalogo implements Crawler<String[]> {
	protected String url;
	
	public CrawlerCatalogo(String url) {
		this.url = url;
	}
	
	public abstract String[] crawl();
	protected abstract String getNextPage(Document currentPage);
}
