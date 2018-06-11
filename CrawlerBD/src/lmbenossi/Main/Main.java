package lmbenossi.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import lmbenossi.Crawler.Produto;
import lmbenossi.Crawler.Produtos;
import lmbenossi.Crawler.Londritech.LondritechCatalogo;
import lmbenossi.Crawler.Londritech.LondritechProduto;
import lmbenossi.Crawler.Newegg.NeweggProduto;
import lmbenossi.Crawler.Pichau.PichauCatalogo;

public class Main {
	public static void main(String[] argv) {
//		File file = new File("/home/lucas/Desktop/pichau.txt");
//		PrintStream out = null;
//		try {
//			out = new PrintStream(file);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
//		Produtos produtos = new PichauCatalogo("https://www.pichau.com.br/todos-os-departamentos?limit=48").crawl();
//		for(Produto produto : produtos) {
//			produto.print();
//		}
		
//		out.close();
		new LondritechProduto("https://www.londritech.com.br/processador-intel-kaby-lake-i3-7100-390ghz-3mb-cache-7-geracao-lga1151bx80677i37100-15821.html").crawl();
		new LondritechProduto("https://www.londritech.com.br/processador-amd-ryzen-7-1700x-38ghz-20mb-cache-am4-95w-sem-cooleryd170xbcaewof-16346.html").crawl();
		new LondritechCatalogo("https://www.londritech.com.br/b?cid=5032%2F5046%2F&cn=HARDWARE%2FPROCESSADOR&pg=1").crawl();
	}
}
