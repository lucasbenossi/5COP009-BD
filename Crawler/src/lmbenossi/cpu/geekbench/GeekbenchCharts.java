package lmbenossi.cpu.geekbench;

import java.util.Hashtable;
import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import lmbenossi.cpu.Cpu;
import lmbenossi.crawler.Crawler;
import lmbenossi.crawler.HtmlDoc;

public class GeekbenchCharts implements Crawler<LinkedList<Cpu>> {
	private String url;
	
	public GeekbenchCharts() {
		this.url = "https://browser.geekbench.com/processor-benchmarks";
	}

	@Override
	public LinkedList<Cpu> crawl() {
		Document htmlDoc = HtmlDoc.getHtmlDoc(this.url);
		Element body = htmlDoc.body();
		
		Element tbodySC = body.selectFirst("div#1 > div.table-responsive > table#pc > tbody");
		Element tbodyMC = body.selectFirst("div#2 > div.table-responsive > table#pc > tbody");
		
		Elements trSC = tbodySC.select("tr");
		Elements trMC = tbodyMC.select("tr");
		
		Hashtable<String, Cpu> cpus = new Hashtable<>(trSC.size());
		
		for(Element tr : trSC) {
			Cpu cpu = new Cpu(tr.selectFirst("td.name").text());
			cpu.scoreSingleCore(Integer.parseInt(tr.selectFirst("td.score").text().trim()));
			cpus.put(cpu.name(), cpu);
		}
		
		for(Element tr : trMC) {
			String input = tr.selectFirst("td.name").text().trim();
			String name = Cpu.parseBrand(input) + " " + Cpu.parseModel(input);
			Cpu cpu = cpus.get(name);
			if(cpu == null) {
				continue;
			}
			System.out.println(cpu.name());
			cpu.scoreMultiCore(Integer.parseInt(tr.select("td.score").text().trim()));
		}
		
		return new LinkedList<>(cpus.values());
	}
}
