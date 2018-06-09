package lmbenossi.Crawler.Pichau;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lmbenossi.Crawler.CrawlerCatalogo;
import lmbenossi.Crawler.CrawlersProduto;
import lmbenossi.Crawler.Produtos;
import lmbenossi.Crawler.CrawlerThreads;
import lmbenossi.Main.Globals;

public class PichauCatalogo implements CrawlerCatalogo {
	private String url;
	
	public PichauCatalogo(String url) {
		this.url = url;
	}

	@Override
	public Produtos crawl() {
		CrawlersProduto crawlers = crawlPaginasCategoria();
		CrawlerThreads threads = new CrawlerThreads(crawlers, 32);
		
		return threads.crawl();
	}
	
	private CrawlersProduto crawlPaginasCategoria() {
		CrawlersProduto crawlers = new CrawlersProduto();
		boolean continua = true;
		String url = this.url;
		
		while(continua) {
			Connection connection = Jsoup.connect(url).userAgent(Globals.USER_AGENT);
			
			Document htmlDoc = null;
			try {
				htmlDoc = connection.get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Element body = htmlDoc.body();
			
			Element boxProdutos = body.selectFirst("#content-main > div.wrapper.clearfix > div.col-right > article > section");
			Elements hrefs = boxProdutos.select("ul.clearfix.linha-produtos > li.item > h4.title > a");
			for(Element href : hrefs) {
				String value = href.attr("href");
				crawlers.add(new PichauProduto(value));
				System.out.println(value);
			}
			
			continua = checkIfContinua(body);
			
			if(continua) {
				url = body.selectFirst("#content-main > div.wrapper.clearfix > div.col-right > article > header > div.toolbar > div.pager > ul > li > a.bt-next").attr("href");
			}
		}
		
		return crawlers;
	}
	
	private boolean checkIfContinua(Element body) {
		Pattern regex = Pattern.compile("Página ([0-9]*) de ([0-9]*)");
		Matcher matcher = regex.matcher(body.selectFirst("#content-main > div.wrapper.clearfix > div.col-right > article > header > div > div.pager > p").text());
		
		int total = 0;
		if(matcher.find()) {
			total = Integer.parseInt(matcher.group(2));
		}
		
		if(total == 1) {
			return false;
		}
		
		int atual = Integer.parseInt(body.selectFirst("#content-main > div.wrapper.clearfix > div.col-right > article > header > div > div.pager > ul > li > span.active").text());
		
		return atual != total;
	}
}
