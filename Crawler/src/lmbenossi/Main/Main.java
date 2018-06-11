package lmbenossi.Main;

import java.io.FileWriter;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import lmbenossi.Crawler.Produto;
import lmbenossi.Crawler.ProdutoAdapter;
import lmbenossi.Crawler.SyncList;
import lmbenossi.Crawler.Londritech.Londritech;
import lmbenossi.Crawler.Pichau.Pichau;

public class Main {
	public static void main(String[] argv) {
		try {
			PrintWriter writer;
			JsonArray array;
			SyncList<Produto> produtos;
			Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Produto.class, new ProdutoAdapter()).create();
			
			Pichau pichau = new Pichau();
			array = new JsonArray();
			produtos = pichau.crawl();
			for(Produto produto : produtos) {
				array.add(gson.toJsonTree(produto));
			}
			writer = new PrintWriter(new FileWriter("pichau.json"), true);
			writer.println(gson.toJson(array));
			writer.close();
			
			Londritech londritech = new Londritech();
			array = new JsonArray();
			produtos = londritech.crawl();
			for(Produto produto : produtos) {
				array.add(gson.toJsonTree(produto));
			}
			writer = new PrintWriter(new FileWriter("londritech.json"), true);
			writer.println(gson.toJson(array));
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
