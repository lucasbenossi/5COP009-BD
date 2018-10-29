package lmbenossi.main;

import lmbenossi.cpu.geekbench.Geekbench;
import lmbenossi.produto.Loja;
import lmbenossi.produto.londritech.Londritech;
import lmbenossi.produto.pichau.Pichau;

public class Main {
	public static void main(String[] args) {
		try {
			Loja.main(args);
			Londritech.main(args);
			Pichau.main(args);
			Geekbench.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!Globals.urlErros.isEmpty()) {
			System.out.println();
			System.out.println("ERROS:");
			for(String url : Globals.urlErros) {
				System.out.println(url);
			}
		}
	}
}
