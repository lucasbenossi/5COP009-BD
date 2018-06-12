package lmbenossi.Crawler;

public class CrawlerThreads<T> {
	private Crawlers<T> crawlers;
	private SyncList<T> list;
	private int n;
	
	public CrawlerThreads(Crawlers<T> crawlers, int n) {
		this.crawlers = crawlers;
		this.n = n;
	}
	
	public SyncList<T> crawl() {
		list = new SyncList<>();
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
	
	private void addSyncList(T obj) {
		if(!(obj instanceof Void) && obj != null) {
			this.list.add(obj);
		}
	}
	
	private class CrawlerRunnable implements Runnable {
		@Override
		public void run() {
			while(true) {
				Crawler<T> crawler = crawlers.next();
				
				if(crawler == null) {
					break;
				}
				
				addSyncList(crawler.crawl());
			}
		}
	}
}
