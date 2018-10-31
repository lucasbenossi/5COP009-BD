package prjbd.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import prjbd.model.Produto;
import prjbd.model.SsdProduto;

public class SsdProdutoDAO extends DAO<SsdProduto> {

	public SsdProdutoDAO() throws ClassNotFoundException, IOException, SQLException {
		super();
	}

	@Override
	public void create(SsdProduto t) throws SQLException {
		throw new SQLException("método não implementado");
	}

	@Override
	public SsdProduto read(Integer id) throws SQLException {
		throw new SQLException("método não implementado");
	}

	@Override
	public void update(SsdProduto t) throws SQLException {
		throw new SQLException("método não implementado");
	}

	@Override
	public void delete(Integer id) throws SQLException {
		throw new SQLException("método não implementado");
	}

	@Override
	public List<SsdProduto> all() throws SQLException {
		LinkedList<SsdProduto> ssds = new LinkedList<>();
		String query = "SELECT p.id, p.nome, p.preco, p.parcelas, p.valorParcela, p.idLoja, p.url, l.nome as nomeLoja " + 
				"FROM prjbd.produto as p, prjbd.loja as l " + 
				"WHERE p.idLoja = l.id AND p.nome ILIKE 'ssd%';";
		try (PreparedStatement stmt = connection.prepareStatement(query);){
			try (ResultSet result = stmt.executeQuery();) {
				while(result.next()) {
					int id = result.getInt("id");
					String nome = result.getString("nome");
					BigDecimal preco = result.getBigDecimal("preco");
					int parcelas = result.getInt("parcelas");
					BigDecimal valorParcela = result.getBigDecimal("valorParcela");
					int idLoja = result.getInt("idLoja");
					String url = result.getString("url");
					String nomeLoja = result.getString("nomeLoja");
					
					ssds.add(new SsdProduto(new Produto(id, nome, "", preco, parcelas, valorParcela, idLoja, url), nomeLoja, null));
				}
			}
		}
		
		return ssds;
	}

	@Override
	public void clean() throws SQLException {
		throw new SQLException("método não implementado");
	}
	
}
