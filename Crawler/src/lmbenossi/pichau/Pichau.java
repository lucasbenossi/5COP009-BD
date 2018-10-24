package lmbenossi.pichau;

import java.util.LinkedList;

import lmbenossi.crawler.Crawler;
import lmbenossi.crawler.CrawlerThreads;
import lmbenossi.model.Produto;

public class Pichau implements Crawler<LinkedList<Produto>> {
	private String url;
	
	public Pichau() {
		this.url = "https://www.pichau.com.br/todos-os-departamentos";
	}

	@Override
	public LinkedList<Produto> crawl() {
		LinkedList<Crawler<Produto>> crawlersProduto = new LinkedList<>();
		
		for(String url : new PichauCatalogo(this.url).crawl()) {
			crawlersProduto.add(new PichauProduto(url));
		}
		
		return new CrawlerThreads<Produto>(crawlersProduto, 64).crawl();
	}
}
