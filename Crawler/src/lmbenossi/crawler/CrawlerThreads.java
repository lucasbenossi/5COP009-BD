package lmbenossi.crawler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CrawlerThreads<T> {
	private LinkedList<T> list;
	private Iterator<Crawler<T>> iterator;
	private int n;
	
	public CrawlerThreads(List<Crawler<T>> crawlers, int n) {
		this.n = n;
		this.iterator = crawlers.iterator();
	}
	
	public LinkedList<T> crawl() {
		list = new LinkedList<>();
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
		
		return list;
	}
	
	private void add(T obj) {
		synchronized(this.list){
			if(!(obj instanceof Void) && obj != null) {
				this.list.add(obj);
			}
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
				
				add(crawler.crawl());
			}
		}
	}
}
