package prjbd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import prjbd.model.Produto;

public class ProdutoDAO extends DAO<Produto> {
	
	public ProdutoDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(Produto produto) throws SQLException {
		String query = "INSERT INTO db.produto (nome, preco, parcelas, valorparcela, disponivel, loja) VALUES (?,?,?,?,?,?);";
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, produto.nome());
			statement.setBigDecimal(2, produto.preco());
			statement.setInt(3, produto.parcelas());
			statement.setBigDecimal(4, produto.valorParcela());
			statement.setBoolean(5, produto.disponivel());
			statement.setString(6, produto.loja());
			statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            if (e.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir produto: pelo menos um campo está em branco.");
            }
            else {
                throw new SQLException("Erro ao inserir produto.");
            }
        }
	}

	@Override
	public Produto read(Integer id) throws SQLException {
		String query = "SELECT id, nome, preco, parcelas, valorparcela, disponivel, loja FROM db.produto WHERE id = ?;";
		Produto produto = null;
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setInt(1, id);
			try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    produto = new Produto(result.getInt("id"),
                    		result.getString("nome"), 
                    		result.getBigDecimal("preco"), 
                    		result.getInt("parcelas"), 
                    		result.getBigDecimal("valorparcela"), 
                    		result.getBoolean("disponivel"), 
                    		result.getString("loja"));
                } else {
                    throw new SQLException("Erro ao visualizar: produto não encontrado.");
                }
            }
		} catch (SQLException e) {
            if (e.getMessage().equals("Erro ao visualizar: produto não encontrado.")) {
                throw e;
            } else {
            	System.err.println(e.getMessage());
                throw new SQLException("Erro ao visualizar produto.");
            }
		}
		return produto;
	}

	@Override
	public void update(Produto produto) throws SQLException {
		//                                    1         2          3             4 				   5			   6				   7
		String query = "UPDATE db.produto SET nome = ?, preco = ?, parcelas = ?, valorparcela = ?, disponivel = ?, loja = ? WHERE id = ?;";
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, produto.nome());
			statement.setBigDecimal(2, produto.preco());
			statement.setInt(3, produto.parcelas());
			statement.setBigDecimal(4, produto.valorParcela());
			statement.setBoolean(5, produto.disponivel());
			statement.setString(6, produto.loja());
			statement.setInt(7, produto.id());
			if(statement.executeUpdate() < 1) {
				throw new SQLException("Erro ao editar: produto não encontrado.");
			}
		} catch (SQLException e) {
			if(e.getMessage().equals("Erro ao editar: produto não encontrado.")) {
				throw e;
			}
			else {
				System.err.println(e.getMessage());
				throw new SQLException("Erro ao editar produto.");
			}
		}		
	}

	@Override
	public void delete(Integer id) throws SQLException {
		String query = "DELETE FROM db.produto WHERE id = ?;";
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setInt(1, id);
			if(statement.executeUpdate() < 1) {
				 throw new SQLException("Erro ao excluir: produto não encontrado.");
			}
		} catch (Exception e) {
			if(e.getMessage().equals("Erro ao excluir: produto não encontrado.")) {
				throw e;
			}
			else {
				System.err.println(e.getMessage());
				throw new SQLException("Erro ao excluir produto.");
			}
		}
		
	}

	@Override
	public List<Produto> all() throws SQLException {
		String query = "SELECT id, nome, preco, parcelas, valorparcela, disponivel, loja FROM db.produto ORDER BY id;";
		LinkedList<Produto> produtos = new LinkedList<>();
		try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
            	Produto produto = new Produto(result.getInt("id"),
                		result.getString("nome"), 
                		result.getBigDecimal("preco"), 
                		result.getInt("parcelas"), 
                		result.getBigDecimal("valorparcela"), 
                		result.getBoolean("disponivel"), 
                		result.getString("loja"));
            	produtos.add(produto);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao listar produtos.");
        }
		return produtos;
	}
	
	@Override
	public void clean() throws SQLException{
		String query = "DELETE FROM db.produto;";
		try (PreparedStatement statement = connection.prepareStatement(query);){
			statement.execute();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new SQLException("Erro ao excluir produtos.");
		}
	}
}
