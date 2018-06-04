package lmbenossi.Main;

import lmbenossi.Crowler.Imdb;

public class Main {
	public static void main(String[] argv) {
		Imdb imdb = new Imdb();
		
		imdb.crawl();
	}
}
