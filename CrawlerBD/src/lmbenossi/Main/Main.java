package lmbenossi.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import lmbenossi.Crawler.Produto;
import lmbenossi.Crawler.Produtos;
import lmbenossi.Crawler.Pichau.PichauCatalogo;

public class Main {
	public static void main(String[] argv) {
		File file = new File("/home/lucas/Desktop/pichau.txt");
		PrintStream out = null;
		try {
			out = new PrintStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Produtos produtos = new PichauCatalogo("https://www.pichau.com.br/todos-os-departamentos?limit=48").crawl();
		for(Produto produto : produtos) {
			produto.print(out);
		}
		
		out.close();
	}
}
