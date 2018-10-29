package lmbenossi.cpu.geekbench;

import java.util.Hashtable;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lmbenossi.cpu.Cpu;
import lmbenossi.cpu.CpuAdapter;
import lmbenossi.crawler.Crawler;
import lmbenossi.crawler.HtmlDoc;
import lmbenossi.main.Globals;

public class GeekbenchCpu implements Crawler<Cpu> {
	String url = null;
	
	public GeekbenchCpu(String url) {
		this.url = url;
	}
	
	@Override
	public Cpu crawl() {
		try {
			Document htmlDoc = HtmlDoc.getHtmlDoc(this.url);
			Element body = htmlDoc.body();
			
			Element tbodyCpuInfo = body.selectFirst("#wrap > div > div.col-md-9 > div.processor-benchmark > div.benchmark-box-wrapper > div > table > tbody");
			
			Hashtable<String, String> table = new Hashtable<>();
			
			for(Element tr : tbodyCpuInfo.children()) {
				String name = tr.child(0).text();
				String value = tr.child(1).text();
				table.put(name, value);
			}
			
			String name = table.get("Processor");
			int cores = Integer.parseInt(table.get("Cores"));
			int threads = Integer.parseInt(table.get("Threads"));
			int frequency = Integer.parseInt(table.get("Frequency").split(" ")[0]);
			
			int maxFrequency = -1;
			String maxFrequencyString = table.get("Maximum Frequency");
			if(maxFrequencyString != null) {
				maxFrequency = Integer.parseInt(maxFrequencyString.split(" ")[0]);
			}
				
			int scoreSingleCore = Integer.parseInt(body.selectFirst("#wrap > div > div.col-md-9 > div.processor-benchmark > div:nth-child(1) > div > div:nth-child(1) > div.score").text());
			int scoreMultiCore = Integer.parseInt(body.selectFirst("#wrap > div > div.col-md-9 > div.processor-benchmark > div:nth-child(1) > div > div:nth-child(2) > div.score").text());
			
			System.out.println(name);
			
			return new Cpu(name, cores, threads, frequency, maxFrequency, scoreSingleCore, scoreMultiCore, this.url);
		} catch (Exception e) {
			Globals.urlErros.add(this.url);
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] argv) {
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Cpu.class, new CpuAdapter()).create();
		for(String url : argv) {
			Cpu cpu = new GeekbenchCpu(url).crawl();
			System.out.println(gson.toJson(gson.toJsonTree(cpu)));
		}
	}

}
