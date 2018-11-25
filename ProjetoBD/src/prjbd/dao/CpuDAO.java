package prjbd.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import prjbd.model.Cpu;

public class CpuDAO extends DAO<Cpu> {

	public CpuDAO() throws ClassNotFoundException, IOException, SQLException {
		super();
	}

	@Override
	public void create(Cpu cpu) throws SQLException {
		String query = "INSERT INTO prjbd.cpu (name, cores, threads, frequency, maxFrequency, scoreSingleCore, scoreMultiCore, url) VALUES (?,?,?,?,?,?,?,?);";
		try (PreparedStatement stmt = connection.prepareStatement(query);) {
			stmt.setString(1, cpu.name());
			stmt.setInt(2, cpu.cores());
			stmt.setInt(3, cpu.threads());
			stmt.setInt(4, cpu.frequency());
			stmt.setInt(5, cpu.maxFrequency());
			stmt.setInt(6, cpu.scoreSingleCore());
			stmt.setInt(7, cpu.scoreMultiCore());
			stmt.setString(8, cpu.url());
			stmt.execute();
		}
	}

	@Override
	public Cpu read(Integer id) throws SQLException {
		String query = "SELECT * FROM prjbd.cpu WHERE id = ?;";
		Cpu cpu = null;
		try (PreparedStatement stmt = connection.prepareStatement(query);) {
			stmt.setInt(1, id);
			try (ResultSet result = stmt.executeQuery();) {
				if(result.next()) {
					cpu = new Cpu(result.getInt("id"), 
							result.getString("name"), 
							result.getInt("cores"), 
							result.getInt("threads"), 
							result.getInt("frequency"), 
							result.getInt("maxFrequency"), 
							result.getInt("scoreSingleCore"), 
							result.getInt("scoreMultiCore"),
							result.getString("url"));
				}
				else {
					throw new SQLException("Cpu não encontrada.");
				}
			}
		}
		return cpu;
	}

	@Override
	public void update(Cpu cpu) throws SQLException {
		String query = "UPDATE prjbd.cpu "
				+ "SET name = ?, cores = ?, threads = ?, frequency = ?, maxFrequency = ?, scoreSingleCore = ?, scoreMultiCore = ?, url = ? "
				+ "WHERE id = ?;";
		try (PreparedStatement stmt = connection.prepareStatement(query);) {
			stmt.setString(1, cpu.name());
			stmt.setInt(2, cpu.cores());
			stmt.setInt(3, cpu.threads());
			stmt.setInt(4, cpu.frequency());
			stmt.setInt(5, cpu.maxFrequency());
			stmt.setInt(6, cpu.scoreSingleCore());
			stmt.setInt(7, cpu.scoreMultiCore());
			stmt.setString(8, cpu.url());
			stmt.setInt(9, cpu.id());
			if(stmt.executeUpdate() < 1) {
				throw new SQLException("Erro ao editar: CPU não encontrada.");
			}
		}
	}

	@Override
	public void delete(Integer id) throws SQLException {
		String query = "DELETE FROM prjbd.cpu WHERE id = ?;";
		try (PreparedStatement stmt = connection.prepareStatement(query);) {
			stmt.setInt(1, id);
			if(stmt.executeUpdate() < 1) {
				 throw new SQLException("Erro ao excluir: CPU não encontrada.");
			}
		}
	}

	@Override
	public LinkedList<Cpu> all() throws SQLException {
		String query = "SELECT * FROM prjbd.cpu ORDER BY id;";
		LinkedList<Cpu> cpus = new LinkedList<>();
		try (PreparedStatement stmt = connection.prepareStatement(query);
                ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
            	Cpu cpu = new Cpu(result.getInt("id"), 
						result.getString("name"), 
						result.getInt("cores"), 
						result.getInt("threads"), 
						result.getInt("frequency"), 
						result.getInt("maxFrequency"), 
						result.getInt("scoreSingleCore"), 
						result.getInt("scoreMultiCore"),
						result.getString("url"));
            	cpus.add(cpu);
            }
        }
		return cpus;
	}

	@Override
	public void clean() throws SQLException {
		String query = "DELETE FROM prjbd.cpu;";
		try (PreparedStatement stmt = connection.prepareStatement(query);){
			stmt.execute();
		}
	}

	public LinkedList<Cpu> search(String pattern) throws SQLException {
		LinkedList<Cpu> cpus = new LinkedList<>();
		String query = "SELECT * FROM prjbd.cpu WHERE lower(name) SIMILAR TO ?";
		
		try (PreparedStatement stmt = connection.prepareStatement(query);){
			stmt.setString(1, pattern);
			try (ResultSet result = stmt.executeQuery();) {
				while (result.next()) {
	            	Cpu cpu = new Cpu(result.getInt("id"), 
							result.getString("name"), 
							result.getInt("cores"), 
							result.getInt("threads"), 
							result.getInt("frequency"), 
							result.getInt("maxFrequency"), 
							result.getInt("scoreSingleCore"), 
							result.getInt("scoreMultiCore"),
							result.getString("url"));
	            	cpus.add(cpu);
	            }
			}
		}
		
		return cpus;
	}
}
