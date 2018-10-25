package lmbenossi.cpu.geekbench;

import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lmbenossi.crawler.Crawler;
import lmbenossi.crawler.HtmlDoc;

public class GeekbenchCharts implements Crawler<String[]> {
	private String url;
	
	public GeekbenchCharts() {
		this.url = "https://browser.geekbench.com/processor-benchmarks";
	}

	@Override
	public String[] crawl() {
		Document htmlDoc = HtmlDoc.getHtmlDoc(this.url);
		Element body = htmlDoc.body();
		
		Element tbody = body.selectFirst("div#1 > div.table-responsive > table#pc > tbody");
		
		Elements trs = tbody.select("tr");
		
		LinkedList<String> urls = new LinkedList<>();
		for(Element tr : trs) {
			String url = "https://browser.geekbench.com" + tr.selectFirst("a").attr("href").trim();
			urls.add(url);
		}
		
		return urls.toArray(new String[urls.size()]);
	}
}
