package lmbenossi.Crawler.Londritech;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lmbenossi.Crawler.Crawler;
import lmbenossi.Crawler.CrawlerThreads;
import lmbenossi.Crawler.HtmlDoc;
import lmbenossi.Crawler.Produto;
import lmbenossi.Crawler.SyncList;

public class Londritech implements Crawler<SyncList<Produto>> {
	private String url;
	
	public Londritech() {
		this.url = "http://www.londritech.com.br";
	}
	
	@Override
	public SyncList<Produto> crawl() {
		SyncList<String> urlsCaralogo = new SyncList<>();
		Document htmlDoc = HtmlDoc.getHtmlDoc(this.url);
		Elements items = htmlDoc.select("#navbar-collapse-target > ul > li.nav-main__item.nav-main__item_all.dropdown.pull-left > div > div > div > ul > li > a");
		for(Element a : items) {
			String href = "https://www.londritech.com.br/" + a.attr("href");
			System.out.println(href);
			urlsCaralogo.add(href);
		}
		
		SyncList<Crawler<Void>> crawlersCatalogo = new SyncList<>();
		SyncList<String> urlsProduto = new SyncList<>();
		for(String url : urlsCaralogo) {
			crawlersCatalogo.add(new LondritechCatalogo(url, urlsProduto));
		}
		new CrawlerThreads<>(crawlersCatalogo, 8).crawl();
		
		SyncList<Crawler<Produto>> crawlersProduto = new SyncList<>();
		for(String url : urlsProduto) {
			crawlersProduto.add(new LondritechProduto(url));
		}

		return new SyncList<>(new CrawlerThreads<>(crawlersProduto, 32).crawl());
	}
}
