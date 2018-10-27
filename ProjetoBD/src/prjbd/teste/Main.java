package prjbd.teste;

import prjbd.dao.ProdutoDAO;

public class Main {
	public static void main(String[] args) throws Exception {
		ProdutoDAO dao = new ProdutoDAO();
		dao.delete(10);
		try {
			dao.clean();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
}
