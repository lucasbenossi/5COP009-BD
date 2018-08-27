package prjbd.dao;

import java.io.IOException;
import java.sql.SQLException;

import prjbd.model.Produto;

public class ProdutoDAOFactory extends DAOFactory<Produto> {
	public ProdutoDAOFactory() throws ClassNotFoundException, IOException, SQLException {
		super();
	}

	@Override
	public DAO<Produto> getDAO() {
		return new ProdutoDAO(connection);
	}
	
}
