package lmbenossi.Crawler;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class HtmlDoc {
	public static Document getHtmlDoc(String url) {
		Connection connection = Jsoup.connect(url);
		try {
			return connection.get();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
