package lmbenossi.Crawler;

public class CrawlerThreads {
	private CrawlersProduto crawlers;
	private Produtos produtos;
	private int n;
	
	public CrawlerThreads(CrawlersProduto crawlers, int n) {
		this.crawlers = crawlers;
		this.n = n;
	}
	
	public Produtos crawl() {
		produtos = new Produtos();
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
		
		return produtos;
	}
	
	private class CrawlerRunnable implements Runnable {
		@Override
		public void run() {
			while(true) {
				CrawlerProduto crawler = crawlers.next();
				
				if(crawler == null) {
					break;
				}
				
				produtos.add(crawler.crawl());
			}
		}
	}
}
