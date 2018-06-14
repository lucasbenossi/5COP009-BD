package lmbenossi.Crawler;

import org.jsoup.nodes.Document;

public abstract class CrawlerCatalogo implements Crawler<Void> {
	protected String url;
	protected SyncList<String> urls;
	
	public CrawlerCatalogo(String url, SyncList<String> urls) {
		this.url = url;
		this.urls = urls;
	}
	
	public Void crawl() {
		crawlPagesCatalogo();
		return null;
	}
	
	protected abstract void crawlPagesCatalogo();
	protected abstract String getNextPage(Document currentPage);
}
