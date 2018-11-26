package prjbd.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import prjbd.dao.DAO;
import prjbd.dao.DAOFactory;
import prjbd.model.Produto;

@WebServlet(urlPatterns={"/produtos", 
		"/produtos/inserir",
		"/produtos/inserir_processa",
		"/produtos/limpar",
		"/produtos/alterar",
		"/produtos/alterar_processa",
		"/produtos/excluir",
		"/produtos/json"})
@MultipartConfig
public class ProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProdutoController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getServletPath()) {
		case "/produtos":
			try (DAOFactory daoFac = new DAOFactory();) {
				List<Produto> produtos = daoFac.getProdutoDAO().all();
				request.setAttribute("produtosList", produtos);
				request.getRequestDispatcher("/produtos/listar.jsp").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/produtos/inserir":
			try {
				request.getRequestDispatcher("/produtos/inserir.jsp").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/produtos/inserir_processa":
			try (DAOFactory daoFac = new DAOFactory();) {
				DAO<Produto> dao = daoFac.getProdutoDAO();
				
				Produto produto = productFromRequest(request);
				
				dao.create(produto);
				request.getRequestDispatcher("/produtos").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/produtos/limpar":
			try (DAOFactory daoFac = new DAOFactory();) {
				DAO<Produto> dao = daoFac.getProdutoDAO();
				dao.clean();
				
				request.getRequestDispatcher("/produtos").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/produtos/alterar":
			try (DAOFactory daoFac = new DAOFactory();) {
				int id = Integer.parseInt(request.getParameter("id"));
				DAO<Produto> dao = daoFac.getProdutoDAO();
				Produto produto = dao.read(id);
				
				request.setAttribute("produto", produto);
				
				request.getRequestDispatcher("/produtos/alterar.jsp").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/produtos/alterar_processa":
			try (DAOFactory daoFac = new DAOFactory();) {
				DAO<Produto> dao = daoFac.getProdutoDAO();
				
				Produto produto = productFromRequest(request);
				produto.setId(Integer.parseInt(request.getParameter("id")));
				
				dao.update(produto);
				request.getRequestDispatcher("/produtos").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/produtos/excluir":
			try (DAOFactory daoFac = new DAOFactory();) {
				DAO<Produto> dao = daoFac.getProdutoDAO();
				int id = Integer.parseInt(request.getParameter("id"));
				dao.delete(id);
				request.getRequestDispatcher("/produtos").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/produtos/json":
			try (DAOFactory daoFac = new DAOFactory();) {
				DAO<Produto> dao = daoFac.getProdutoDAO();
				Part part = request.getPart("json");
				
				Gson gson = new GsonBuilder().registerTypeAdapter(Produto.class, new Produto.ProdutoAdapter()).create();
				JsonReader reader = gson.newJsonReader(new InputStreamReader(part.getInputStream()));
				
				try {
					daoFac.begin();
					
					reader.beginArray();
					while(reader.hasNext()) {
						dao.create(gson.fromJson(reader, Produto.class));
					}
					reader.endArray();
					
					daoFac.commit();
					daoFac.end();
				} catch (Exception e) {
					daoFac.rollback();
					throw e;
				}
				
				request.getRequestDispatcher("/produtos").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		default:
			break;
		}
	}

	private Produto productFromRequest(HttpServletRequest request) {
		String nome = request.getParameter("nome");
		String nomeTratado = request.getParameter("nomeTratado");
		BigDecimal preco = new BigDecimal(request.getParameter("preco"));
		int parcelas = Integer.parseInt(request.getParameter("parcelas"));
		BigDecimal valorParcela = new BigDecimal(request.getParameter("valorParcela"));
		int idLoja = Integer.parseInt(request.getParameter("idLoja"));
		String url = request.getParameter("url");
		
		return new Produto(0, nome, nomeTratado, preco, parcelas, valorParcela, idLoja, url);
	}
}
