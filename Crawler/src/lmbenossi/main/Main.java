package lmbenossi.main;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import lmbenossi.londritech.Londritech;
import lmbenossi.model.Produto;
import lmbenossi.model.ProdutoAdapter;
import lmbenossi.pichau.Pichau;

public class Main {
	public static void main(String[] argv) {
		try {
			PrintWriter writer;
			JsonArray array;
			LinkedList<Produto> produtos;
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
			
//			LondritechProduto crawler = new LondritechProduto("http://londritech.com.br/computador-londritech-intel-i7-7700-360ghz-placa-b250-memoria-16gb-2x8gb-ssd-120gb--hd-1tb-placa-de-vdeo-gtx1060-3gb-fonte-500w-80plus-bronze-30907.html");
//			Produto produto = crawler.crawl();
//			produto.print();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
