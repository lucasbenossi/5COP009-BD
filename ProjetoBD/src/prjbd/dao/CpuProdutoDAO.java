package prjbd.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import prjbd.model.Cpu;
import prjbd.model.CpuProduto;
import prjbd.model.Produto;

public class CpuProdutoDAO extends DAO<CpuProduto> {

	public CpuProdutoDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(CpuProduto t) throws SQLException {
		throw new SQLException("método não implementado");
	}

	@Override
	public CpuProduto read(Integer id) throws SQLException {
		throw new SQLException("método não implementado");
	}

	@Override
	public void update(CpuProduto t) throws SQLException {
		throw new SQLException("método não implementado");
	}

	@Override
	public void delete(Integer id) throws SQLException {
		throw new SQLException("método não implementado");
	}

	@Override
	public LinkedList<CpuProduto> all() throws SQLException {
		LinkedList<CpuProduto> cpusProduto = new LinkedList<>();
		String query = "SELECT p.id, p.nome, p.nomeTratado, p.preco, p.parcelas, p.valorParcela, p.idLoja, p.url, l.nome as nomeLoja " + 
				"FROM prjbd.produto as p, prjbd.loja as l " + 
				"WHERE p.idLoja = l.id AND p.nome ILIKE 'processador%';";
		
		try (DAOFactory daoFac = new DAOFactory();) {
			CpuDAO cpuDao = (CpuDAO) daoFac.getCpuDAO();
			
			try (PreparedStatement stmt = connection.prepareStatement(query);
					ResultSet result = stmt.executeQuery();){
				while(result.next()) {
					int id = result.getInt("id");
					String nome = result.getString("nome");
					String nomeTratado = result.getString("nomeTratado");
					BigDecimal preco = result.getBigDecimal("preco");
					int parcelas = result.getInt("parcelas");
					BigDecimal valorParcela = result.getBigDecimal("valorParcela");
					int idLoja = result.getInt("idLoja");
					String url = result.getString("url");
					String nomeLoja = result.getString("nomeLoja");
					
					
					LinkedList<Cpu> cpus = cpuDao.search(nomeTratado);
					if(cpus.isEmpty()) {
						System.err.println(nomeTratado + ": Produto sem prjbd.cpu correspondente.");
						continue;
					}
					else if(cpus.size() > 1) {
						System.err.println(nomeTratado + ": Produto com mais de um prjbd.cpu correspondente.");
						continue;
					}
					
					Cpu cpu = cpus.getFirst();
					Produto produto = new Produto(id, nome, nomeTratado, preco, parcelas, valorParcela, idLoja, url);
					CpuProduto cpuProduto = new CpuProduto(produto, cpu, null, nomeLoja);
					
					cpusProduto.add(cpuProduto);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			throw new SQLException(e);
		}
		
		return cpusProduto;
	}

	@Override
	public void clean() throws SQLException {
		throw new SQLException("método não implementado");
	}

	@Override
	public LinkedList<CpuProduto> search(String pattern) throws SQLException {
		throw new SQLException("método não implementado");
	}
}
