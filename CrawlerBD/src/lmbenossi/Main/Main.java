package lmbenossi.Main;

import java.io.FileWriter;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lmbenossi.Crawler.Produto;
import lmbenossi.Crawler.ProdutoAdapter;
import lmbenossi.Crawler.Produtos;
import lmbenossi.Crawler.Londritech.Londritech;
import lmbenossi.Crawler.Pichau.Pichau;

public class Main {
	public static void main(String[] argv) {
		try {
			PrintWriter writer;
			Produtos produtos;
			Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Produto.class, new ProdutoAdapter()).create();
			
			writer = new PrintWriter(new FileWriter("pichau.json"), true);
			Pichau pichau = new Pichau();
			produtos = pichau.crawl();
			for(Produto produto : produtos) {
				String json = gson.toJson(produto);
				writer.println(json);
			}
			writer.close();
			
			writer = new PrintWriter(new FileWriter("londritech.json"), true);
			Londritech londritech = new Londritech();
			produtos = londritech.crawl();
			for(Produto produto : produtos) {
				String json = gson.toJson(produto);
				writer.println(json);
			}
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
