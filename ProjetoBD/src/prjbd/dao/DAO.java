
package prjbd.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import prjbd.jdbc.ConnectionFactory;

public abstract class DAO<T> {

	protected Connection connection;

	public DAO() throws ClassNotFoundException, IOException, SQLException {
		this.connection = ConnectionFactory.getInstance().getConnection();
	}

	public abstract void create(T t) throws SQLException;

	public abstract T read(Integer id) throws SQLException;

	public abstract void update(T t) throws SQLException;

	public abstract void delete(Integer id) throws SQLException;

	public abstract List<T> all() throws SQLException;
	
	public abstract void clean() throws SQLException;
}
