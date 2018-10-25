package lmbenossi.cpu.geekbench;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import lmbenossi.cpu.Cpu;
import lmbenossi.cpu.CpuAdapter;
import lmbenossi.crawler.Crawler;

public class Geekbench implements Crawler<LinkedList<Cpu>> {

	@Override
	public LinkedList<Cpu> crawl() {
		return new GeekbenchCharts().crawl();
	}

	public static void main(String[] args) throws Exception {
		Geekbench geekbench = new Geekbench();
		LinkedList<Cpu> cpus = geekbench.crawl();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Cpu.class, new CpuAdapter()).create();
		JsonArray array = new JsonArray();
		for(Cpu cpu : cpus) {
			array.add(gson.toJsonTree(cpu));
		}
		
		PrintWriter writer = new PrintWriter(new FileWriter("geekbench.json"), true);
		writer.println(gson.toJson(array));
		writer.close();
	}
}
