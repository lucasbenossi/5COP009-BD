package lmbenossi.londritech;

import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lmbenossi.crawler.Crawler;
import lmbenossi.crawler.CrawlerThreads;
import lmbenossi.crawler.HtmlDoc;
import lmbenossi.model.Produto;

public class Londritech implements Crawler<LinkedList<Produto>> {
	private String url;
	
	public Londritech() {
		this.url = "http://www.londritech.com.br";
	}
	
	@Override
	public LinkedList<Produto> crawl() {
		LinkedList<String> urlsCatalogo = new LinkedList<>();
		
		Document htmlDoc = HtmlDoc.getHtmlDoc(this.url);
		Elements items = htmlDoc.select("#navbar-collapse-target > ul > li.nav-main__item.nav-main__item_all.dropdown.pull-left > div > div > div > ul > li > a");
		for(Element a : items) {
			String href = "https://www.londritech.com.br/" + a.attr("href");
			System.out.println(href);
			urlsCatalogo.add(href);
		}
		
		LinkedList<Crawler<String[]>> crawlersCatalogo = new LinkedList<>();
		for(String url : urlsCatalogo) {
			crawlersCatalogo.add(new LondritechCatalogo(url));
		}
		
		LinkedList<String[]> listUrlsProduto = new CrawlerThreads<>(crawlersCatalogo, 16).crawl();
		
		LinkedList<Crawler<Produto>> crawlersProduto = new LinkedList<>();
		for(String[] urlsProduto : listUrlsProduto) {
			for(String url : urlsProduto) {
				crawlersProduto.add(new LondritechProduto(url));
			}
		}
		
		return new CrawlerThreads<>(crawlersProduto, 64).crawl();
	}
}
