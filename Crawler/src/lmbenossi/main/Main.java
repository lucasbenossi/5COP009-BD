package lmbenossi.main;

import lmbenossi.cpu.geekbench.Geekbench;
import lmbenossi.produto.Loja;
import lmbenossi.produto.londritech.LondritechSsdCpu;
import lmbenossi.produto.pichau.PichauSsdCpu;

public class Main {
	public static void main(String[] args) {
		try {
			Loja.main(args);
			LondritechSsdCpu.main(args);
			PichauSsdCpu.main(args);
			Geekbench.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		printErros();
	}

	public static void printErros() {
		if(!Globals.urlErros.isEmpty()) {
			System.out.println();
			System.out.println("ERROS:");
			for(String url : Globals.urlErros) {
				System.out.println(url);
			}
		}
	}
}
