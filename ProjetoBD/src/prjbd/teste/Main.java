package prjbd.teste;

import prjbd.dao.DAO;
import prjbd.dao.ProdutoDAO;
import prjbd.model.Produto;

public class Main {
	public static void main(String[] args) throws Exception {
		DAO<Produto> dao = new ProdutoDAO();
		
		for(Produto produto : ((ProdutoDAO)dao).search("ssd%")) {
			System.out.println(produto.getNome());
		}
	}
}
