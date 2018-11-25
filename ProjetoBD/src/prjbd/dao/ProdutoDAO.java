package prjbd.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import prjbd.model.Produto;

public class ProdutoDAO extends DAO<Produto> {

	public ProdutoDAO() throws ClassNotFoundException, IOException, SQLException {
		super();
	}

	@Override
	public void create(Produto produto) throws SQLException {
		String query = "INSERT INTO prjbd.produto (nome, nomeTratado, preco, parcelas, valorparcela, idLoja, url) VALUES (?,?,?,?,?,?,?);";
		try (PreparedStatement stmt = connection.prepareStatement(query);) {
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getNomeTratado());
			stmt.setBigDecimal(3, produto.getPreco());
			stmt.setInt(4, produto.getParcelas());
			stmt.setBigDecimal(5, produto.getValorParcela());
			stmt.setInt(6, produto.getIdLoja());
			stmt.setString(7, produto.getUrl());
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
                    		result.getString("nomeTratado"),
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
		String query = "UPDATE prjbd.produto SET nome = ?, nomeTratado = ?, preco = ?, parcelas = ?, valorparcela = ?, idLoja = ?, url = ? WHERE id = ?;";
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, produto.getNome());
			statement.setString(2, produto.getNomeTratado());
			statement.setBigDecimal(3, produto.getPreco());
			statement.setInt(4, produto.getParcelas());
			statement.setBigDecimal(5, produto.getValorParcela());
			statement.setInt(6, produto.getIdLoja());
			statement.setString(7, produto.getUrl());
			statement.setInt(8, produto.getId());
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
	public LinkedList<Produto> all() throws SQLException {
		String query = "SELECT * FROM prjbd.produto ORDER BY id;";
		LinkedList<Produto> produtos = new LinkedList<>();
		try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
            	Produto produto = new Produto(result.getInt("id"),
                		result.getString("nome"), 
                		result.getString("nomeTratado"),
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
	                		result.getString("nomeTratado"),
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
