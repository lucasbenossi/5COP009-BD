package lmbenossi.Crawler.Pichau;

import lmbenossi.Crawler.Crawler;
import lmbenossi.Crawler.CrawlerThreads;
import lmbenossi.Crawler.Crawlers;
import lmbenossi.Crawler.Produto;
import lmbenossi.Crawler.Produtos;
import lmbenossi.Crawler.SyncList;

public class Pichau implements Crawler<Produtos> {
	private String url;
	
	public Pichau() {
		this.url = "https://www.pichau.com.br/todos-os-departamentos";
	}

	@Override
	public Produtos crawl() {
		SyncList<String> urlsProduto = new SyncList<>();
		new PichauCatalogo(url, urlsProduto).crawl();
		
		Crawlers<Produto> crawlersProduto = new Crawlers<>();
		for(String url : urlsProduto) {
			crawlersProduto.add(new PichauProduto(url));
		}
		Produtos produtos = new Produtos(new CrawlerThreads<Produto>(crawlersProduto, 32).crawl());
		
		return produtos;
	}
}
