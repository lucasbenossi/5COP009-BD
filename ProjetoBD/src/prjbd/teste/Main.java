package prjbd.teste;

import prjbd.dao.ProdutoDAO;
import prjbd.jdbc.ConnectionFactory;

public class Main {
	public static void main(String[] args) throws Exception {
		ProdutoDAO dao = new ProdutoDAO(ConnectionFactory.getInstance().getConnection());
		dao.clean();
	}
}
