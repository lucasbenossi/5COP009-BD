package lmbenossi.test;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import lmbenossi.crawler.Crawler;
import lmbenossi.crawler.CrawlerThreads;
import lmbenossi.crawler.JsonCreator;

public class Test {
	public static void main(String[] args) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonWriter writer = gson.newJsonWriter(new OutputStreamWriter(System.out));
		
		LinkedList<Crawler<String>> crawlers = new LinkedList<>();
		
		for(int i = 0; i < 1000; i++) {
			crawlers.add(new SimpleCrawler());
		}
		
		JsonCreator jsonCreator = new JsonCreator(gson, writer, new CrawlerThreads<>(crawlers, 1000));
		
		jsonCreator.execute();
	}
	
	private static class SimpleCrawler implements Crawler<String> {
		private static int counter = 0;
		private int i = counter++;
		@Override
		public String crawl() {
			try {
				Thread.sleep(500 + new Random().nextInt(200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return Thread.currentThread().getName() + " Crawler-" + i;
		}
	}
}
