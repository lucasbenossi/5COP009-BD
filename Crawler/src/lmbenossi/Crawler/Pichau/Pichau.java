package lmbenossi.Crawler.Pichau;

import lmbenossi.Crawler.Crawler;
import lmbenossi.Crawler.CrawlerThreads;
import lmbenossi.Crawler.Produto;
import lmbenossi.Crawler.SyncList;

public class Pichau implements Crawler<SyncList<Produto>> {
	private String url;
	
	public Pichau() {
		this.url = "https://www.pichau.com.br/todos-os-departamentos";
	}

	@Override
	public SyncList<Produto> crawl() {
		SyncList<String> urlsProduto = new SyncList<>();
		new PichauCatalogo(url, urlsProduto).crawl();
		
		SyncList<Crawler<Produto>> crawlersProduto = new SyncList<>();
		for(String url : urlsProduto) {
			crawlersProduto.add(new PichauProduto(url));
		}
		SyncList<Produto> produtos = new SyncList<>(new CrawlerThreads<Produto>(crawlersProduto, 32).crawl());
		
		return produtos;
	}
}
