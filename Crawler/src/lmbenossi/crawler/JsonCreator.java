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
	
	private Object take() {
		while(!finished) {
			Object obj = crawler.poll();
			if(obj != null) {
				return obj;
			}
			synchronized (crawler) {
				try {
					crawler.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	private class JsonCreatorRunnable implements Runnable {
		@Override
		public void run() {
			try {
				writer.beginArray().flush();
				
				while(true) {
					Object obj = take();
					if(finished) {
						break;
					}
					gson.toJson(gson.toJsonTree(obj), writer);
					writer.flush();
				}
				
				writer.endArray().flush();
			} catch (JsonIOException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
