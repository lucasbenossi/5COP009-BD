package prjbd.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import prjbd.model.Produto;

public class ProdutoDAO extends DAO<Produto> {

	public ProdutoDAO() throws ClassNotFoundException, IOException, SQLException {
		super();
	}

	@Override
	public void create(Produto produto) throws SQLException {
		String query = "INSERT INTO prjbd.produto (nome, preco, parcelas, valorparcela, idLoja, url) VALUES (?,?,?,?,?,?);";
		try (PreparedStatement stmt = connection.prepareStatement(query);) {
			stmt.setString(1, produto.nome());
			stmt.setBigDecimal(2, produto.preco());
			stmt.setInt(3, produto.parcelas());
			stmt.setBigDecimal(4, produto.valorParcela());
			stmt.setInt(5, produto.idLoja());
			stmt.setString(6, produto.url());
			stmt.execute();
        }
	}

	@Override
	public Produto read(Integer id) throws SQLException {
		String query = "SELECT * FROM prjbd.produto WHERE id = ?;";
		Produto produto = null;
		try (PreparedStatement stmt = connection.prepareStatement(query);) {
			stmt.setInt(1, id);
			try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    produto = new Produto(result.getInt("id"), 
                    		result.getString("nome"), 
                    		result.getBigDecimal("preco"), 
                    		result.getInt("parcelas"), 
                    		result.getBigDecimal("valorParcela"), 
                    		result.getInt("idLoja"),
                    		result.getString("url"));
                } else {
                    throw new SQLException("Erro ao visualizar: produto não encontrado.");
                }
            }
		}
		return produto;
	}

	@Override
	public void update(Produto produto) throws SQLException {
		String query = "UPDATE prjbd.produto SET nome = ?, preco = ?, parcelas = ?, valorparcela = ?, idLoja = ?, url = ? WHERE id = ?;";
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, produto.nome());
			statement.setBigDecimal(2, produto.preco());
			statement.setInt(3, produto.parcelas());
			statement.setBigDecimal(4, produto.valorParcela());
			statement.setInt(5, produto.idLoja());
			statement.setString(6, produto.url());
			statement.setInt(7, produto.id());
			if(statement.executeUpdate() < 1) {
				throw new SQLException("Erro ao editar: produto não encontrado.");
			}
		}
	}

	@Override
	public void delete(Integer id) throws SQLException {
		String query = "DELETE FROM prjbd.produto WHERE id = ?;";
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setInt(1, id);
			if(statement.executeUpdate() < 1) {
				 throw new SQLException("Erro ao excluir: produto não encontrado.");
			}
		}
	}

	@Override
	public List<Produto> all() throws SQLException {
		String query = "SELECT * FROM prjbd.produto ORDER BY id;";
		LinkedList<Produto> produtos = new LinkedList<>();
		try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
            	Produto produto = new Produto(result.getInt("id"),
                		result.getString("nome"), 
                		result.getBigDecimal("preco"), 
                		result.getInt("parcelas"), 
                		result.getBigDecimal("valorparcela"), 
                		result.getInt("idLoja"),
                		result.getString("url"));
            	produtos.add(produto);
            }
        }
		return produtos;
	}
	
	@Override
	public void clean() throws SQLException {
		String query = "DELETE FROM prjbd.produto;";
		try (PreparedStatement statement = connection.prepareStatement(query);){
			statement.execute();
		}
	}
	
	public LinkedList<Produto> search(String pattern) throws SQLException {
		String query = "SELECT * FROM prjbd.produto WHERE LOWER(nome) SIMILAR TO ?;";
		LinkedList<Produto> produtos = new LinkedList<>();
		try (PreparedStatement stmt = connection.prepareStatement(query);) {
			stmt.setString(1, pattern);
			try(ResultSet result = stmt.executeQuery();) {
				while(result.next()) {
					Produto produto = new Produto(result.getInt("id"), 
							result.getString("nome"), 
							result.getBigDecimal("preco"), 
							result.getInt("parcelas"), 
							result.getBigDecimal("valorParcela"), 
							result.getInt("idLoja"), 
							result.getString("url"));
					produtos.add(produto);
				}
			}
		}
		return produtos;
	}
}
