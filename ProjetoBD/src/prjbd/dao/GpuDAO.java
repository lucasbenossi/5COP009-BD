package prjbd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import prjbd.model.Gpu;

public class GpuDAO extends DAO<Gpu> {

	public GpuDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(Gpu gpu) throws SQLException {
		String query = "INSERT INTO prjbd.gpu (name, g3dMark, g2dMark) VALUES (?,?,?);";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, gpu.name());
			stmt.setInt(2, gpu.g3dMark());
			stmt.setInt(3, gpu.g2dMark());
			stmt.execute();
		}
	}

	@Override
	public Gpu read(Integer id) throws SQLException {
		String query = "SELECT * FROM prjbd.gpu WHERE id = ?;";
		Gpu gpu = null;
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			try (ResultSet result = stmt.executeQuery()) {
				if(result.next()) {
					gpu = new Gpu(result.getInt("id"), 
							result.getString("name"), 
							result.getInt("g3dMark"), 
							result.getInt("g2dMark"));
				} else {
					throw new SQLException("Gpu não encontrada");
				}
			}
		}
		return gpu;
	}

	@Override
	public void update(Gpu gpu) throws SQLException {
		String query = "UPDATE prjbd.gpu "
				+ "SET name = ?, g3dMark = ?, g2dMark = ? "
				+ "WHERE id = ?;";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, gpu.name());
			stmt.setInt(2, gpu.g3dMark());
			stmt.setInt(3, gpu.g2dMark());
			stmt.setInt(4, gpu.id());
			if(stmt.executeUpdate() < 1) {
				throw new SQLException("Erro ao editar: Gpu não encontrada.");
			}
		}
	}

	@Override
	public void delete(Integer id) throws SQLException {
		String query = "DELETE FROM prjbd.gpu WHERE id = ?;";
		try (PreparedStatement stmt = connection.prepareStatement(query);) {
			stmt.setInt(1, id);
			if(stmt.executeUpdate() < 1) {
				 throw new SQLException("Erro ao excluir: CPU não encontrada.");
			}
		}
	}

	@Override
	public LinkedList<Gpu> all() throws SQLException {
		String query = "SELECT * FROM prjbd.gpu ORDER BY id;";
		LinkedList<Gpu> gpus = new LinkedList<>();
		try (PreparedStatement stmt = connection.prepareStatement(query);
				ResultSet result = stmt.executeQuery();) {
			while(result.next()) {
				Gpu gpu = new Gpu(result.getInt("id"), 
						result.getString("name"), 
						result.getInt("g3dMark"), 
						result.getInt("g2dMark"));
				gpus.add(gpu);
			}
		}
		return gpus;
	}

	@Override
	public void clean() throws SQLException {
		String query = "DELETE FROM prjbd.gpu;";
		try (PreparedStatement stmt = connection.prepareStatement(query);){
			stmt.execute();
		}
	}

	@Override
	public LinkedList<Gpu> search(String pattern) throws SQLException {
		LinkedList<Gpu> gpus = new LinkedList<>();
		String query = "SELECT * FROM prjbd.gpu WHERE lower(name) SIMILAR TO ?";
		
		try (PreparedStatement stmt = connection.prepareStatement(query);){
			stmt.setString(1, pattern);
			try (ResultSet result = stmt.executeQuery();) {
				while (result.next()) {
					Gpu gpu = new Gpu(result.getInt("id"), 
							result.getString("name"), 
							result.getInt("g3dMark"), 
							result.getInt("g2dMark"));
					gpus.add(gpu);
	            }
			}
		}
		
		return gpus;
	}
	
}
