package prjbd.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import prjbd.jdbc.ConnectionFactory;
import prjbd.model.Produto;

public class DAOFactory implements AutoCloseable {
	private Connection connection;
	
	public DAOFactory() throws ClassNotFoundException, IOException, SQLException {
		this.connection = ConnectionFactory.getInstance().getConnection();
	}
	
	public void begin() throws SQLException {
		connection.setAutoCommit(false);
	}
	
	public void commit() throws SQLException {
		connection.commit();
	}
	
	public void rollback() throws SQLException {
		connection.rollback();
	}
	
	public void end() throws SQLException {
		connection.setAutoCommit(true);
	}

	@Override
	public void close() throws SQLException {
		connection.close();		
	}

	public CpuDAO getCpuDAO() {
		return new CpuDAO(this.connection);
	}
	
	public GpuDAO getGpuDAO() {
		return new GpuDAO(this.connection);
	}
	
	public LojaDAO getLojaDAO() {
		return new LojaDAO(this.connection);
	}

	public DAO<Produto> getProdutoDAO() {
		return new ProdutoDAO(this.connection);
	}

	public CpuProdutoDAO getCpuProdutoDAO() {
		return new CpuProdutoDAO(this.connection);
	}

	public SsdProdutoDAO getSsdProdutoDAO() {
		return new SsdProdutoDAO(this.connection);
	}
}
