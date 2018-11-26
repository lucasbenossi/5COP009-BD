package prjbd.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import prjbd.jdbc.ConnectionFactory;
import prjbd.model.Gpu;
import prjbd.model.GpuProduto;
import prjbd.model.Produto;

public class GpuProdutoDAO extends DAO<GpuProduto> {

	public GpuProdutoDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(GpuProduto t) throws SQLException {
		throw new SQLException("método não implementado.");
	}

	@Override
	public GpuProduto read(Integer id) throws SQLException {
		throw new SQLException("método não implementado.");
	}

	@Override
	public void update(GpuProduto t) throws SQLException {
		throw new SQLException("método não implementado.");
	}

	@Override
	public void delete(Integer id) throws SQLException {
		throw new SQLException("método não implementado.");
	}

	@Override
	public LinkedList<GpuProduto> all() throws SQLException {
		LinkedList<GpuProduto> gpusProduto = new LinkedList<>();
		String query = "SELECT p.id, p.nome, p.nomeTratado, p.preco, p.parcelas, p.valorParcela, p.idLoja, p.url, l.nome as nomeLoja " + 
				"FROM prjbd.produto as p, prjbd.loja as l " + 
				"WHERE p.idLoja = l.id AND p.nome ILIKE 'placa%de%video%';";
		
		try (DAOFactory daoFac = new DAOFactory();) {
			DAO<Gpu> gpuDao = daoFac.getGpuDAO();
			
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
					
					if(nomeTratado.equals("")) {
						continue;
					}
					
					LinkedList<Gpu> gpus = gpuDao.search(nomeTratado);
					if(gpus.isEmpty()) {
						System.err.println(nomeTratado + ": Produto sem prjbd.gpu correspondente.");
						continue;
					}
					else if(gpus.size() > 1) {
						System.err.println(nomeTratado + ": Produto com mais de um prjbd.gpu correspondente.");
						continue;
					}
					
					Gpu gpu = gpus.getFirst();
					Produto produto = new Produto(id, nome, nomeTratado, preco, parcelas, valorParcela, idLoja, url);
					GpuProduto gpuProduto = new GpuProduto(produto, gpu, null, nomeLoja);
					
					gpusProduto.add(gpuProduto);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			throw new SQLException(e);
		}
		
		return gpusProduto;
	}
	
	public static void main(String[] args) {
		try {
			GpuProdutoDAO dao = new GpuProdutoDAO(ConnectionFactory.getInstance().getConnection());
			
			LinkedList<GpuProduto> list = dao.all();
			
			for(GpuProduto gpu : list) {
				System.out.println(gpu.getGpu().name());
			}
			
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void clean() throws SQLException {
		throw new SQLException("método não implementado.");
	}

	@Override
	public LinkedList<GpuProduto> search(String pattern) throws SQLException {
		throw new SQLException("método não implementado.");
	}

}
