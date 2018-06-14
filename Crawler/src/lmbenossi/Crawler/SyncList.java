package lmbenossi.Crawler;

import java.util.Iterator;
import java.util.LinkedList;

public class SyncList<T> implements Iterable<T> {
	private LinkedList<T> list;
	private Iterator<T> iterator;
	
	public SyncList() {
		this.list = new LinkedList<>();
		this.iterator = null;
	}
	
	public SyncList(SyncList<T> syncList) {
		this.list = syncList.list;
		this.iterator = null;
	}
	
	public synchronized void add(T obj) {
		this.list.add(obj);
	}
	
	@Override
	public Iterator<T> iterator(){
		return this.list.iterator();
	}
	
	public synchronized T next() {
		if(iterator == null) {
			iterator = list.iterator();
		}
		synchronized (iterator) {
			if(iterator.hasNext()) {
				return iterator.next();
			}
		}
		return null;
	}
}
