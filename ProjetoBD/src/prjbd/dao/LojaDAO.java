package prjbd.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import prjbd.model.Loja;

public class LojaDAO extends DAO<Loja> {

	public LojaDAO() throws ClassNotFoundException, IOException, SQLException {
		super();
	}

	@Override
	public void create(Loja loja) throws SQLException {
		String query = "INSERT INTO prjbd.loja (id, nome, url) VALUES (?,?,?);";
		try (PreparedStatement stmt = connection.prepareStatement(query);) {
			stmt.setInt(1, loja.id());
			stmt.setString(2, loja.nome());
			stmt.setString(3, loja.url());
			stmt.execute();
        }
	}

	@Override
	public Loja read(Integer id) throws SQLException {
		String query = "SELECT * FROM prjbd.loja WHERE id=?;";
		Loja loja = null;
		try (PreparedStatement stmt = connection.prepareStatement(query);) {
			stmt.setInt(1, id);
			try(ResultSet result = stmt.executeQuery();){
				if(result.next()) {
					loja = new Loja(result.getInt("id"), result.getString("nome"), result.getString("url"));
				}
			}
		}
		return loja;
	}

	@Override
	public void update(Loja loja) throws SQLException {
		String query = "UPDATE prjbd.loja SET nome = ?, url = ? WHERE id = ?;";
		try (PreparedStatement stmt = connection.prepareStatement(query);) {
			stmt.setString(1, loja.nome());
			stmt.setString(2, loja.url());
			stmt.setInt(3, loja.id());
			if(stmt.executeUpdate() < 1) {
				throw new SQLException("Erro ao editar: loja não encontrada.");
			}
		}
	}

	@Override
	public void delete(Integer id) throws SQLException {
		String query = "DELETE FROM prjbd.loja WHERE id = ?;";
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setInt(1, id);
			if(statement.executeUpdate() < 1) {
				 throw new SQLException("Erro ao excluir: loja não encontrada.");
			}
		}
	}

	@Override
	public List<Loja> all() throws SQLException {
		String query = "SELECT * FROM prjbd.loja ORDER BY id;";
		LinkedList<Loja> lojas = new LinkedList<>();
		try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
            	Loja loja = new Loja(result.getInt("id"), result.getString("nome"), result.getString("url"));
            	lojas.add(loja);
            }
        }
		return lojas;
	}

	@Override
	public void clean() throws SQLException {
		String query = "DELETE FROM prjbd.loja;";
		try (PreparedStatement statement = connection.prepareStatement(query);){
			statement.execute();
		}
	}

}