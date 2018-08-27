package prjbd.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import prjbd.jdbc.ConnectionFactory;

public abstract class DAOFactory<T> {
	protected Connection connection;
	
	protected DAOFactory() throws ClassNotFoundException, IOException, SQLException{
		this.connection = ConnectionFactory.getInstance().getConnection();
	}
	
	public abstract DAO<T> getDAO();
}
