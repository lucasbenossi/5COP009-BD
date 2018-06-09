package lmbenossi.Crawler;

public class CrawlerThreads implements CrawlerCatalogo {
	private CrawlersProduto crawlers;
	private Produtos produtos;
	private Thread[] threads;
	
	public CrawlerThreads(CrawlersProduto crawlers, int n) {
		this.crawlers = crawlers;
		this.threads = new Thread[n];
	}
	
	public Produtos crawl() {
		produtos = new Produtos();
		
		for(int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new CrawlerRunnable());
			threads[i].start();
		}
		
		for(int i = 0; i < threads.length; i++) {
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
