package lmbenossi.Crawler;

import org.jsoup.nodes.Document;

public abstract class CrawlerCatalogo {
	protected String url;
	
	public CrawlerCatalogo(String url) {
		this.url = url;
	}
	
	public Produtos crawl() {
		CrawlerThreads threads = new CrawlerThreads(crawlPagesCatalogo(), 32);
		
		return threads.crawl();
	}
	
	protected abstract CrawlersProduto crawlPagesCatalogo();
	protected abstract String getNextPage(Document currentPage);
}
