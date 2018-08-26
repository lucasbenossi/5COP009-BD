package prjbd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProdutoDAO extends DAO<Produto> {
	
	private static final String CREATE_QUERY = "INSERT INTO db.produto (id, nome, preco, parcelas, valorparcela, disponivel, loja) VALUES (?,?,?,?,?,?,?);";
//	private static final String READ_QUERY = "SELECT nome, preco, parcelas, valorparcela, disponivel, loja FROM db.produto WHERE id = ?;";
//	private static final String UPDATE_QUERY = "UPDATE db.produto SET nome = ?, preco = ?, parcelas = ?, valorparcela = ?, dosponivel = ?, loja = ? WHERE id = ?;";
//	private static final String DELETE_QUERY = "DELETE FROM db.produto WHERE id = ?;";
//	private static final String ALL_QUERY = "SELECT id, nome FROM db.produto ORDER BY nome;";
	private int id;
	
	public ProdutoDAO(Connection connection) {
		super(connection);
		this.id = 0;
	}

	@Override
	public void create(Produto produto) throws SQLException {
		try {
			PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);
			statement.setInt(1, this.id());
			statement.setString(2, produto.nome());
			statement.setString(3, produto.preco().toString());
			statement.setInt(4, produto.parcelas());
			statement.setString(5, produto.valorParcela().toString());
			statement.setBoolean(6, produto.disponivel());
			statement.setString(7, produto.loja().name());
			statement.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir produto: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir produto.");
            }
        }
	}

	@Override
	public Produto read(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Produto t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Produto> all() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int id() {
		return this.id++;
	}
}
