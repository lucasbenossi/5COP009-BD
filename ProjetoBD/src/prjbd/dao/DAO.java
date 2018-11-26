
package prjbd.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public abstract class DAO<T> {

	protected Connection connection;

	public DAO(Connection connection) {
		this.connection = connection;
	}

	public abstract void create(T t) throws SQLException;

	public abstract T read(Integer id) throws SQLException;

	public abstract void update(T t) throws SQLException;

	public abstract void delete(Integer id) throws SQLException;

	public abstract LinkedList<T> all() throws SQLException;
	
	public abstract void clean() throws SQLException;
	
	public abstract LinkedList<T> search(String pattern) throws SQLException;
}
