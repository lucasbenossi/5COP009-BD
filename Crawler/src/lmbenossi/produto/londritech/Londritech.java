package lmbenossi.produto.londritech;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import lmbenossi.crawler.Crawler;
import lmbenossi.crawler.CrawlerThreads;
import lmbenossi.crawler.HtmlDoc;
import lmbenossi.crawler.JsonCreator;
import lmbenossi.produto.Loja;
import lmbenossi.produto.Produto;
import lmbenossi.produto.ProdutoAdapter;

public class Londritech {
	public void crawl() throws IOException {
		LinkedList<String> urlsCatalogo = new LinkedList<>();
		
		Document htmlDoc = HtmlDoc.getHtmlDoc(Loja.LONDRITECH.getUrl());
		Elements items = htmlDoc.select("#navbar-collapse-target > ul > li.nav-main__item.nav-main__item_all.dropdown.pull-left > div > div > div > ul > li > a");
		for(Element a : items) {
			String href = Loja.LONDRITECH.getUrl() + a.attr("href");
			System.out.println(href);
			urlsCatalogo.add(href);
		}
		
		LinkedList<Crawler<String[]>> crawlersCatalogo = new LinkedList<>();
		for(String url : urlsCatalogo) {
			crawlersCatalogo.add(new LondritechCatalogo(url));
		}
		
		LinkedList<String[]> urls = new CrawlerThreads<String[]>(crawlersCatalogo, 8).crawl();
		
		LinkedList<Crawler<Produto>> crawlersProduto = new LinkedList<>();
		for(String[] urlArray : urls) {
			for(String url : urlArray) {
				crawlersProduto.add(new LondritechProduto(url));
			}
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Produto.class, new ProdutoAdapter()).create();
		JsonWriter writer = gson.newJsonWriter(new FileWriter("londritech.json"));
		
		CrawlerThreads<Produto> crawler = new CrawlerThreads<Produto>(crawlersProduto, 64);
		JsonCreator creator = new JsonCreator(gson, writer, crawler);
		creator.execute();
	}
	
	public static void main(String[] argv) throws IOException {
		Londritech londritech = new Londritech();
		londritech.crawl();
	}
}
