package lmbenossi.Crawler;

import java.util.Iterator;
import java.util.LinkedList;

public class Produtos implements Iterable<Produto>{
	private LinkedList<Produto> produtos;
	
	public Produtos() {
		this.produtos = new LinkedList<>();
	}
	
	public synchronized void add(Produto produto) {
		this.produtos.add(produto);
	}

	@Override
	public Iterator<Produto> iterator() {
		return this.produtos.iterator();
	}
}
