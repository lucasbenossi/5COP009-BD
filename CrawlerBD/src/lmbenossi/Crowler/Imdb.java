package lmbenossi.Crowler;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lmbenossi.Main.Globals;

public class Imdb {
	private String url;
	private Document htmlDoc;
	
	public Imdb() {
		this.url = "https://www.imdb.com/chart/top";
		Connection connection = Jsoup.connect(this.url).userAgent(Globals.USER_AGENT);
		try {
			this.htmlDoc = connection.get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void crawl() {
		Element table = this.htmlDoc.getElementsByAttributeValue("data-caller-name", "chart-top250movie").first();
		Elements rows = table.select("tbody tr");
		Pattern regex = Pattern.compile("[0-9]{4}");
		for(Element row : rows) {
			Element td = row.child(1);
			String name = td.child(0).text();
			
			Matcher matcher = regex.matcher(td.child(1).text());
			String year = "";
			if(matcher.find()) {
				year = matcher.group();
			}
			
			System.out.println(name + " - " + year);
		}
	}
}
