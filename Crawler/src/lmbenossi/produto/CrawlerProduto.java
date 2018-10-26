package lmbenossi.produto;

import lmbenossi.crawler.Crawler;

public abstract class CrawlerProduto implements Crawler<Produto> {
	protected String url;
	
	public CrawlerProduto(String url) {
		this.url = url;
	}
	
	@Override
	public abstract Produto crawl();
}
