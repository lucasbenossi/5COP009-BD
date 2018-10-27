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
		"/inserir_produto",
		"/inserir_produto_processa",
		"/alterar_produto",
		"/alterar_produto_processa",
		"/excluir_produto",
		"/inserir_json_processa",
		"/limpar_produtos"})
@MultipartConfig
public class ProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdutoController() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getServletPath()) {
		case "/produtos":
			try {
				List<Produto> produtos = new ProdutoDAO().all();
				request.setAttribute("produtosList", produtos);
				request.getRequestDispatcher("/produtos.jsp").forward(request, response);
			} catch (Exception e) {
				processExeption(request, response, e);
			}
			break;
		case "/inserir_produto":
			request.getRequestDispatcher("/inserir_produto.jsp").forward(request, response);
			break;
		case "/inserir_produto_processa":
			try {
				DAO<Produto> dao = new ProdutoDAO();
				
				Produto produto = productFromRequest(request);
				
				dao.create(produto);
				request.getRequestDispatcher("/produtos").forward(request, response);
			} catch (Exception e) {
				processExeption(request, response, e);
			}
			break;
		case "/alterar_produto":
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				DAO<Produto> dao = new ProdutoDAO();
				Produto produto = dao.read(id);
				
				request.setAttribute("produto", produto);
				
				request.getRequestDispatcher("alterar_produto.jsp").forward(request, response);
			} catch (Exception e) {
				processExeption(request, response, e);
			}
			break;
		case "/alterar_produto_processa":
			try {
				DAO<Produto> dao = new ProdutoDAO();
				
				Produto produto = productFromRequest(request);
				produto.id(Integer.parseInt(request.getParameter("id")));
				
				dao.update(produto);
				request.getRequestDispatcher("/produtos").forward(request, response);
			} catch (Exception e) {
				processExeption(request, response, e);
			}
			break;
		case "/excluir_produto":
			try {
				DAO<Produto> dao = new ProdutoDAO();
				int id = Integer.parseInt(request.getParameter("id"));
				dao.delete(id);
				request.getRequestDispatcher("/produtos").forward(request, response);
			} catch (Exception e) {
				processExeption(request, response, e);
			}
			break;
		case "/inserir_json_processa":
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
					boolean disponivel = object.get("disponivel").getAsBoolean();
					String loja = object.get("loja").getAsString();
					
					Produto produto = new Produto(0, nome, preco, parcelas, valorParcela, disponivel, loja);
					dao.create(produto);
				}
				
				request.getRequestDispatcher("/produtos").forward(request, response);
			} catch (Exception e) {
				processExeption(request, response, e);
			}
			break;
		case "/limpar_produtos":
			try {
				DAO<Produto> dao = new ProdutoDAO();
				dao.clean();
				
				request.getRequestDispatcher("/produtos").forward(request, response);
			} catch (Exception e) {
				processExeption(request, response, e);
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
		boolean disponivel = Boolean.parseBoolean(request.getParameter("disponivel"));
		String loja = request.getParameter("loja");
		
		return new Produto(0, nome, preco, parcelas, valorParcela, disponivel, loja);
	}

	private void processExeption(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws ServletException, IOException {
		e.printStackTrace(System.err);
		request.setAttribute("exeption", e.toString());
		request.getRequestDispatcher("/erro.jsp").forward(request, response);
	}
}
