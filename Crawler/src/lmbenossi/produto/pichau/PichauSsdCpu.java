package lmbenossi.produto.pichau;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import lmbenossi.crawler.Crawler;
import lmbenossi.crawler.CrawlerThreads;
import lmbenossi.crawler.JsonCreator;
import lmbenossi.produto.Produto;
import lmbenossi.produto.ProdutoAdapter;

public class PichauSsdCpu {
	public void crawl() throws IOException {
		LinkedList<Crawler<String[]>> crawlersCatalogos = new LinkedList<>();
		crawlersCatalogos.add(new PichauCatalogo("https://www.pichau.com.br/hardware/ssd"));
		crawlersCatalogos.add(new PichauCatalogo("https://www.pichau.com.br/hardware/processadores"));
		
		LinkedList<String[]> urlsProdutos = new CrawlerThreads<>(crawlersCatalogos, 2).crawl();
		
		LinkedList<Crawler<Produto>> crawlersProdutos = new LinkedList<>();
		for(String[] array : urlsProdutos) {
			for(String url : array) {
				crawlersProdutos.add(new PichauProduto(url));
			}
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Produto.class, new ProdutoAdapter()).create();
		JsonWriter writer = gson.newJsonWriter(new FileWriter("pichau.json"));
		
		CrawlerThreads<Produto> crawler = new CrawlerThreads<Produto>(crawlersProdutos, 64);
		JsonCreator creator = new JsonCreator(gson, writer, crawler);
		creator.execute();
	}
	
	public static void main(String[] argv) throws Exception {
		new PichauSsdCpu().crawl();
	}
}
