package prjbd.dao;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import prjbd.model.PrecoPorPerformance;

public class CpuPrecoPorPerformanceDAO extends DAO<PrecoPorPerformance> {

	public CpuPrecoPorPerformanceDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(PrecoPorPerformance t) throws SQLException {
		throw new SQLException("Método não implementado.");
	}

	@Override
	public PrecoPorPerformance read(Integer id) throws SQLException {
		throw new SQLException("Método não implementado.");
	}

	@Override
	public void update(PrecoPorPerformance t) throws SQLException {
		throw new SQLException("Método não implementado.");
	}

	@Override
	public void delete(Integer id) throws SQLException {
		throw new SQLException("Método não implementado.");
	}

	@Override
	public LinkedList<PrecoPorPerformance> all() throws SQLException {
		LinkedList<PrecoPorPerformance> cpus = new LinkedList<>();
		String query = "SELECT p.id, c.name, p.url, p.preco, c.scoreMultiCore, l.nome AS nomeLoja, (c.scoreMultiCore / p.preco) AS precoPorPerformance " + 
				"FROM prjbd.produto AS p INNER JOIN prjbd.loja AS l ON p.idLoja = l.id INNER JOIN prjbd.cpu as c ON (p.nomeTratado ILIKE c.name) " + 
				"ORDER BY precoPorPerformance;";
		try(PreparedStatement stmt = connection.prepareStatement(query);
				ResultSet result = stmt.executeQuery();) {
			while(result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");                    
				String url = result.getString("url");
				BigDecimal preco = result.getBigDecimal("preco");
				int scoreMultiCore = result.getInt("scoreMultiCore");
				String nomeLoja = result.getString("nomeLoja");
				BigDecimal precoPorPerformance = result.getBigDecimal("precoPorPerformance").round(new MathContext(2, RoundingMode.CEILING));
				
				cpus.add(new PrecoPorPerformance(id, name, url, preco, scoreMultiCore, nomeLoja, precoPorPerformance));
			}
		}
		return cpus;
	}

	@Override
	public void clean() throws SQLException {
		throw new SQLException("Método não implementado.");
	}

	@Override
	public LinkedList<PrecoPorPerformance> search(String pattern) throws SQLException {
		throw new SQLException("Método não implementado.");
	}
	
}
