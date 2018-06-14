package lmbenossi.Crawler.Londritech;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lmbenossi.Crawler.Crawler;
import lmbenossi.Crawler.CrawlerThreads;
import lmbenossi.Crawler.Crawlers;
import lmbenossi.Crawler.HtmlDoc;
import lmbenossi.Crawler.Produto;
import lmbenossi.Crawler.Produtos;
import lmbenossi.Crawler.SyncList;

public class Londritech implements Crawler<Produtos> {
	private String url;
	
	public Londritech() {
		this.url = "http://www.londritech.com.br";
	}
	
	@Override
	public Produtos crawl() {
		SyncList<String> urlsCaralogo = new SyncList<>();
		Document htmlDoc = HtmlDoc.getHtmlDoc(this.url);
		Elements items = htmlDoc.select("#navbar-collapse-target > ul > li.nav-main__item.nav-main__item_all.dropdown.pull-left > div > div > div > ul > li > a");
		for(Element a : items) {
			String href = "https://www.londritech.com.br/" + a.attr("href");
			System.out.println(href);
			urlsCaralogo.add(href);
		}
		
		Crawlers<Void> crawlersCatalogo = new Crawlers<>();
		SyncList<String> urlsProduto = new SyncList<>();
		for(String url : urlsCaralogo) {
			crawlersCatalogo.add(new LondritechCatalogo(url, urlsProduto));
		}
		new CrawlerThreads<>(crawlersCatalogo, 8).crawl();
		
		Crawlers<Produto> crawlersProduto = new Crawlers<>();
		for(String url : urlsProduto) {
			crawlersProduto.add(new LondritechProduto(url));
		}
		Produtos produtos = new Produtos(new CrawlerThreads<>(crawlersProduto, 32).crawl());
		
		return produtos;
	}
}
