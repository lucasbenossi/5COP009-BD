package lmbenossi.cpu.geekbench;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import lmbenossi.cpu.Cpu;
import lmbenossi.cpu.CpuAdapter;
import lmbenossi.crawler.Crawler;
import lmbenossi.crawler.CrawlerThreads;

public class Geekbench {

	public void crawl() {
		GeekbenchCharts charts = new GeekbenchCharts();
		String[] urls = charts.crawl();
		System.out.println(urls.length);

		LinkedList<Crawler<Cpu>> crawlers = new LinkedList<>();
		for(String url : urls) {
			crawlers.add(new GeekbenchCpu(url));
		}
		
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Cpu.class, new CpuAdapter()).create();
			JsonWriter writer = gson.newJsonWriter(new FileWriter("geekbench.json"));
			
			new CrawlerThreads<>(crawlers, 4).crawl(gson, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Geekbench geekbench = new Geekbench();
		geekbench.crawl();
	}
}