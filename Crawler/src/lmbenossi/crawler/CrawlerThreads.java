package lmbenossi.crawler;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonWriter;

public class CrawlerThreads<T> {
	private Iterator<Crawler<T>> iterator;
	private int n;
	private LinkedList<T> queue;
	
	public CrawlerThreads(List<Crawler<T>> crawlers, int n) {
		this.n = n;
		this.iterator = crawlers.iterator();
		this.queue = new LinkedList<T>();
	}
	
	public LinkedList<T> crawl() {
		this.run();
		return this.queue;
	}
	
	public void crawl(Gson gson, JsonWriter writer) {
		Thread thread = new Thread(new JsonRunnable(gson, writer));
		thread.start();
		
		this.run();
		putNull();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void run() {
		Thread[] threads = new Thread[this.n];
		
		for(int i = 0; i < this.n; i++) {
			threads[i] = new Thread(new CrawlerRunnable());
			threads[i].start();
		}
		
		for(int i = 0; i < this.n; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void put(T obj) {
		synchronized(this.queue) {
			if(obj != null) {
				this.queue.add(obj);
				this.queue.notify();
			}
		}
	}
	
	private void putNull() {
		synchronized (this.queue) {
			this.queue.add(null);
			this.queue.notify();
		}
	}
	
	private T take() {
		synchronized(queue) {
			while(queue.isEmpty()) {
				try {
					queue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return queue.remove();
		}
	}
	
	private Crawler<T> nextCrawler() {
		synchronized (this.iterator) {
			if(this.iterator.hasNext()) {
				return this.iterator.next();
			}
			return null;
		}
	}
	
	private class CrawlerRunnable implements Runnable {
		@Override
		public void run() {
			while(true) {
				Crawler<T> crawler = nextCrawler();
				
				if(crawler == null) {
					break;
				}
				
				put(crawler.crawl());
			}
		}
	}
	
	private class JsonRunnable implements Runnable {
		private Gson gson;
		private JsonWriter writer;
		
		public JsonRunnable(Gson gson, JsonWriter writer) {
			this.gson = gson;
			this.writer = writer;
		}

		@Override
		public void run() {
			try {
				writer.beginArray().flush();
				
				while(true) {
					Object obj = take();
					if(obj == null) {
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
