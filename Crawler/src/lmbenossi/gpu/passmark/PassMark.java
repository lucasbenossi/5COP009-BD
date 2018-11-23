package lmbenossi.gpu.passmark;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import lmbenossi.crawler.HtmlDoc;
import lmbenossi.gpu.Gpu;
import lmbenossi.gpu.GpuAdapter;

public class PassMark {

	public void crawl() throws IOException {
		Document htmlDoc = HtmlDoc.getHtmlDoc("https://www.videocardbenchmark.net/GPU_mega_page.html");
		Element body = htmlDoc.body();
		
		Element tbody = body.selectFirst("table#cputable > tbody");
		Elements trs = tbody.select("tr:not(.tablesorter-childRow)");
		
		LinkedList<Gpu> gpus = new LinkedList<>();
		for(Element tr : trs) {
			try {
				String name = tr.selectFirst("td:nth-child(1) > a:nth-child(2)").text();
				int g3dMark = Integer.parseInt(tr.selectFirst("td:nth-child(3)").text());
				int g2dMark = Integer.parseInt(tr.selectFirst("td:nth-child(5)").text());
				gpus.add(new Gpu(name, g3dMark, g2dMark));
			} catch (NullPointerException e) {
				System.err.println(tr);
			}
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Gpu.class, new GpuAdapter()).create();
		JsonWriter writer = gson.newJsonWriter(new FileWriter("passmark.json"));
		
		writer.beginArray().flush();
		
		for(Gpu gpu : gpus) {
			gson.toJson(gson.toJsonTree(gpu), writer);
			writer.flush();
		}
		
		writer.endArray().flush();
	}
	
	public static void main(String[] args) throws IOException {
		new PassMark().crawl();
	}
}
