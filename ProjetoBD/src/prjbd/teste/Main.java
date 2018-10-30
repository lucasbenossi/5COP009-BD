package prjbd.teste;

import java.math.BigDecimal;

import prjbd.dao.DAO;
import prjbd.dao.LojaDAO;
import prjbd.dao.ProdutoDAO;
import prjbd.model.Loja;
import prjbd.model.Produto;

public class Main {
	public static void main(String[] args) throws Exception {
		DAO<Produto> dao = new ProdutoDAO();
		
		System.out.println(dao.all().size());
	}
}
