package lmbenossi.produto.londritech;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import lmbenossi.crawler.Crawler;
import lmbenossi.crawler.CrawlerThreads;
import lmbenossi.crawler.JsonCreator;
import lmbenossi.main.Main;
import lmbenossi.produto.Produto;
import lmbenossi.produto.ProdutoAdapter;

public class LondritechSsdCpu {
	public void crawl() throws IOException {
		LinkedList<Crawler<String[]>> crawlersCatalogo = new LinkedList<>();
		crawlersCatalogo.add(new LondritechCatalogo("https://www.londritech.com.br/b?cn=HARDWARE%2FHARD+DISK+%2F+HD+%2F+SSD&cid=5032/5045/"));
		crawlersCatalogo.add(new LondritechCatalogo("https://www.londritech.com.br/b?cn=HARDWARE%2FPROCESSADOR&cid=5032/5046/"));
		
		LinkedList<String[]> urls = new CrawlerThreads<String[]>(crawlersCatalogo, 2).crawl();
		
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
		LondritechSsdCpu londritech = new LondritechSsdCpu();
		londritech.crawl();
		
		Main.printErros();
	}
}
