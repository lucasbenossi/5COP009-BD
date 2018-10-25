package lmbenossi.main;

import lmbenossi.produto.londritech.Londritech;
import lmbenossi.produto.pichau.Pichau;

public class Main {
	public static void main(String[] argv) {
		try {
			Londritech.main(argv);
			Pichau.main(argv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
