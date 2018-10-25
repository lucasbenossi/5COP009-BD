package lmbenossi.main;

import lmbenossi.cpu.geekbench.Geekbench;
import lmbenossi.produto.londritech.Londritech;
import lmbenossi.produto.pichau.Pichau;

public class Main {
	public static void main(String[] args) {
		try {
			Londritech.main(args);
			Pichau.main(args);
			Geekbench.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
