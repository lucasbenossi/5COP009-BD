package lmbenossi.Crawler;

public class Crawlers<T> extends SyncList<Crawler<T>> {
	public Crawlers() {
		super();
	}
	
	public Crawlers(SyncList<Crawler<T>> syncList) {
		super(syncList);
	}
}
