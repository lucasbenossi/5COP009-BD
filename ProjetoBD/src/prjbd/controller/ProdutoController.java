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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import prjbd.dao.DAO;
import prjbd.dao.ProdutoDAO;
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
			try {
				List<Produto> produtos = new ProdutoDAO().all();
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
			try {
				DAO<Produto> dao = new ProdutoDAO();
				
				Produto produto = productFromRequest(request);
				
				dao.create(produto);
				request.getRequestDispatcher("/produtos").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/produtos/limpar":
			try {
				DAO<Produto> dao = new ProdutoDAO();
				dao.clean();
				
				request.getRequestDispatcher("/produtos").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/produtos/alterar":
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				DAO<Produto> dao = new ProdutoDAO();
				Produto produto = dao.read(id);
				
				request.setAttribute("produto", produto);
				
				request.getRequestDispatcher("/produtos/alterar.jsp").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/produtos/alterar_processa":
			try {
				DAO<Produto> dao = new ProdutoDAO();
				
				Produto produto = productFromRequest(request);
				produto.id(Integer.parseInt(request.getParameter("id")));
				
				dao.update(produto);
				request.getRequestDispatcher("/produtos").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/produtos/excluir":
			try {
				DAO<Produto> dao = new ProdutoDAO();
				int id = Integer.parseInt(request.getParameter("id"));
				dao.delete(id);
				request.getRequestDispatcher("/produtos").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/produtos/json":
			try {
				DAO<Produto> dao = new ProdutoDAO();
				JsonParser parser = new JsonParser();
				
				Part part = request.getPart("json");
				InputStreamReader isr = new InputStreamReader(part.getInputStream());
				
				JsonArray array = parser.parse(isr).getAsJsonArray();
				
				for(JsonElement element : array) {
					JsonObject object = element.getAsJsonObject();
					
					String nome = object.get("nome").getAsString();
					BigDecimal preco = object.get("preco").getAsBigDecimal();
					int parcelas = object.get("parcelas").getAsInt();
					BigDecimal valorParcela = object.get("valorParcela").getAsBigDecimal();
					int idLoja = object.get("idLoja").getAsInt();
					
					Produto produto = new Produto(0, nome, preco, parcelas, valorParcela, idLoja);
					dao.create(produto);
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
		BigDecimal preco = new BigDecimal(request.getParameter("preco"));
		int parcelas = Integer.parseInt(request.getParameter("parcelas"));
		BigDecimal valorParcela = new BigDecimal(request.getParameter("valorParcela"));
		int idLoja = Integer.parseInt(request.getParameter("idLoja"));
		
		return new Produto(0, nome, preco, parcelas, valorParcela, idLoja);
	}
}
