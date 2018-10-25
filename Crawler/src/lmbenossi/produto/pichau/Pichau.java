package lmbenossi.produto.pichau;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import lmbenossi.crawler.Crawler;
import lmbenossi.crawler.CrawlerThreads;
import lmbenossi.produto.Produto;
import lmbenossi.produto.ProdutoAdapter;

public class Pichau {

	public void crawl() {
		LinkedList<Crawler<Produto>> crawlersProduto = new LinkedList<>();
		
		for(String url : new PichauCatalogo().crawl()) {
			crawlersProduto.add(new PichauProduto(url));
		}
		
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Produto.class, new ProdutoAdapter()).create();
			JsonWriter writer = gson.newJsonWriter(new FileWriter("pichau.json"));
			
			new CrawlerThreads<Produto>(crawlersProduto, 64).crawl(gson, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] argv) throws Exception {
		Pichau pichau = new Pichau();
		pichau.crawl();
	}
}
