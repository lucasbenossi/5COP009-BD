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
		String query = "INSERT INTO prjbd.produto (nome, preco, parcelas, valorparcela, idLoja) VALUES (?,?,?,?,?);";
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, produto.nome());
			statement.setBigDecimal(2, produto.preco());
			statement.setInt(3, produto.parcelas());
			statement.setBigDecimal(4, produto.valorParcela());
			statement.setInt(5, produto.idLoja());
			statement.execute();
        }
	}

	@Override
	public Produto read(Integer id) throws SQLException {
		String query = "SELECT * FROM prjbd.produto WHERE id = ?;";
		Produto produto = null;
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setInt(1, id);
			try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    produto = new Produto(result.getInt("id"), 
                    		result.getString("nome"), 
                    		result.getBigDecimal("preco"), 
                    		result.getInt("parcelas"), 
                    		result.getBigDecimal("valorParcela"), 
                    		result.getInt("idLoja"));
                } else {
                    throw new SQLException("Erro ao visualizar: produto não encontrado.");
                }
            }
		}
		return produto;
	}

	@Override
	public void update(Produto produto) throws SQLException {
		String query = "UPDATE prjbd.produto SET nome = ?, preco = ?, parcelas = ?, valorparcela = ?, idLoja = ? WHERE id = ?;";
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, produto.nome());
			statement.setBigDecimal(2, produto.preco());
			statement.setInt(3, produto.parcelas());
			statement.setBigDecimal(4, produto.valorParcela());
			statement.setInt(5, produto.idLoja());
			statement.setInt(6, produto.id());
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
                		result.getInt("idLoja"));
            	produtos.add(produto);
            }
        }
		return produtos;
	}
	
	@Override
	public void clean() throws SQLException{
		String query = "DELETE FROM prjbd.produto;";
		try (PreparedStatement statement = connection.prepareStatement(query);){
			statement.execute();
		}
	}
}
