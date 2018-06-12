package lmbenossi.Main;

import lmbenossi.Crawler.Produto;
import lmbenossi.Crawler.Produtos;
import lmbenossi.Crawler.Londritech.Londritech;

public class Main {
	public static void main(String[] argv) {
		Produtos produtos = new Londritech().crawl();
		for(Produto produto : produtos) {
			produto.print(System.err);
		}
	}
}
