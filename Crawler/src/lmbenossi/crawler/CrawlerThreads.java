package lmbenossi.crawler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
		this.execute();
		return this.queue;
	}
	
	public void execute() {
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
			this.queue.add(obj);
		}
		synchronized (this) {
			this.notify();
		}
	}
	
	public T poll() {
		synchronized (this.queue) {
			if(this.queue.isEmpty()) {
				return null;
			}
			return this.queue.remove();
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
				
				T obj = crawler.crawl();
				if(obj != null) {
					put(obj);
				}
			}
		}
		
		private Crawler<T> nextCrawler() {
			synchronized (iterator) {
				if(iterator.hasNext()) {
					return iterator.next();
				}
				return null;
			}
		}
	}
}
