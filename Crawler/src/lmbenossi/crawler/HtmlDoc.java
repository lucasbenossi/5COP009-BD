package lmbenossi.crawler;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class HtmlDoc {
	public static Document getHtmlDoc(String url) {
		while(true) {
			Connection connection = Jsoup.connect(url);
			try {
				return connection.get();
			} catch (IOException e) {
				System.err.println("ERRO: " + url + " " + e);
			}
		}
	}
}
