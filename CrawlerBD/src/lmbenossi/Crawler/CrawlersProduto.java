package lmbenossi.Crawler;

import java.util.Iterator;
import java.util.LinkedList;

public class CrawlersProduto {
	private LinkedList<CrawlerProduto> queue;
	private Iterator<CrawlerProduto> iterator;
	
	public CrawlersProduto() {
		this.queue = new LinkedList<>();
		this.iterator = null;
	}
	
	public synchronized void add(CrawlerProduto crawler) {
		this.queue.add(crawler);
	}
	
	public synchronized CrawlerProduto next() {
		CrawlerProduto crawler = null;
		
		if(iterator == null) {
			iterator = queue.iterator();
		}
		synchronized (iterator) {
			if (iterator.hasNext()) {
				crawler = iterator.next();
			}
		}
		return crawler;
	}
}
