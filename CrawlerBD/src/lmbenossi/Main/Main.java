package lmbenossi.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import lmbenossi.Crawler.Produto;
import lmbenossi.Crawler.Produtos;
import lmbenossi.Crawler.Pichau.PichauCatalogo;

public class Main {
	public static void main(String[] argv) {
//		new PichauBusca("core i5 8400").crawl().print(System.out);;
//		new PichauBusca("memoria kingston ddr4 16gb 1x16").crawl().print();
//		new PichauBusca("core i5 7400").crawl().print();
//		new PichauCategoria("https://www.pichau.com.br/hardware/processadores").crawl();
		
		File file = new File("/home/lucas/Desktop/pichau.txt");
		PrintStream out = null;
		try {
			out = new PrintStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Produtos produtos = new PichauCatalogo("https://www.pichau.com.br/todos-os-departamentos/").crawl();
		for(Produto produto : produtos) {
			produto.print(out);
		}
		
		out.close();
	}
}
