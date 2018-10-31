package lmbenossi.crawler;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonWriter;

public class JsonCreator {
	Gson gson;
	JsonWriter writer;
	CrawlerThreads<?> crawler;
	boolean finished = false;
	
	public JsonCreator(Gson gson, JsonWriter writer, CrawlerThreads<?> crawler) {
		this.gson = gson;
		this.writer = writer;
		this.crawler = crawler;
	}
	
	public void execute() {
		Thread thread = new Thread(new JsonCreatorRunnable());
		thread.start();
		
		crawler.execute();
		this.finished = true;
		synchronized (crawler) {
			this.crawler.notify();
		}
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private class JsonCreatorRunnable implements Runnable {
		@Override
		public void run() {
			try {
				writer.beginArray().flush();
				
				while(!finished) {
					Object obj = crawler.poll();
					if (obj != null){
						gson.toJson(gson.toJsonTree(obj), writer);
						writer.flush();
					}
					else {
						synchronized (crawler) {
							try {
								crawler.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
				
				writer.endArray().flush();
			} catch (JsonIOException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
