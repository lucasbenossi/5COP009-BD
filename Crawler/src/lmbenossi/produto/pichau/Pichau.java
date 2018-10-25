package lmbenossi.produto.pichau;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import lmbenossi.crawler.Crawler;
import lmbenossi.crawler.CrawlerThreads;
import lmbenossi.produto.Produto;
import lmbenossi.produto.ProdutoAdapter;

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
	
	public static void main(String[] argv) throws Exception {
		Pichau pichau = new Pichau();
		LinkedList<Produto> produtos = pichau.crawl();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Produto.class, new ProdutoAdapter()).create();
		JsonArray array = new JsonArray();
		for(Produto produto : produtos) {
			array.add(gson.toJsonTree(produto));
		}
		
		PrintWriter writer = new PrintWriter(new FileWriter("pichau.json"), true);
		writer.println(gson.toJson(array));
		writer.close();
	}
}
