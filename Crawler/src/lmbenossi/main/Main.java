package lmbenossi.main;

import lmbenossi.cpu.geekbench.Geekbench;
import lmbenossi.gpu.passmark.PassMark;
import lmbenossi.produto.Loja;
import lmbenossi.produto.londritech.LondritechSsdCpuGpu;
import lmbenossi.produto.pichau.PichauSsdCpuGpu;

public class Main {
	public static void main(String[] args) {
		try {
			Loja.main(args);
			LondritechSsdCpuGpu.main(args);
			PichauSsdCpuGpu.main(args);
			Geekbench.main(args);
			PassMark.main(args);
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
