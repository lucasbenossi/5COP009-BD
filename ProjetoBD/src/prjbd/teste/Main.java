package prjbd.teste;

import java.math.BigDecimal;

import prjbd.dao.Loja;
import prjbd.dao.Produto;
import prjbd.dao.ProdutoDAO;
import prjbd.jdbc.ConnectionFactory;

public class Main {
	public static void main(String[] args) throws Exception {
		Produto produto = new Produto("nome", new BigDecimal("1000.00"), 3, new BigDecimal("333.33"), true, Loja.LONDRITECH);
		
		ProdutoDAO produtoDAO = new ProdutoDAO(ConnectionFactory.getInstance().getConnection());
		
		produtoDAO.create(produto);
		produtoDAO.create(produto);
		produtoDAO.create(produto);
	}
}
