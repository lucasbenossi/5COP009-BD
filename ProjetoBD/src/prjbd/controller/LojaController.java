package prjbd.controller;

import java.io.IOException;
import java.io.InputStreamReader;
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
import prjbd.dao.LojaDAO;
import prjbd.model.Loja;

@WebServlet(urlPatterns={"/lojas", 
		"/lojas/inserir",
		"/lojas/inserir_processa",
		"/lojas/limpar",
		"/lojas/alterar",
		"/lojas/alterar_processa",
		"/lojas/excluir",
		"/lojas/json"})
@MultipartConfig
public class LojaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LojaController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch(request.getServletPath()) {
		case "/lojas":
			try {
				List<Loja> lojas = new LojaDAO().all();
				request.setAttribute("lojasList", lojas);
				request.getRequestDispatcher("/lojas/listar.jsp").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/lojas/inserir":
			try {
				request.getRequestDispatcher("/lojas/inserir.jsp").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/lojas/inserir_processa":
			try {
				DAO<Loja> dao = new LojaDAO();
				Loja loja = lojaFromRequest(request);
				dao.create(loja);
				request.getRequestDispatcher("/lojas").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/lojas/limpar":
			try {
				DAO<Loja> dao = new LojaDAO();
				dao.clean();
				request.getRequestDispatcher("/lojas").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/lojas/alterar":
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				DAO<Loja> dao = new LojaDAO();
				
				Loja loja = dao.read(id);
				request.setAttribute("loja", loja);
				
				request.getRequestDispatcher("/lojas/alterar.jsp").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/lojas/alterar_processa":
			try {
				DAO<Loja> dao = new LojaDAO();
				Loja loja = lojaFromRequest(request);
				dao.update(loja);
				request.getRequestDispatcher("/lojas").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/lojas/excluir":
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				DAO<Loja> dao = new LojaDAO();
				dao.delete(id);
				request.getRequestDispatcher("/lojas").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
		case "/lojas/json":
			try {
				DAO<Loja> dao = new LojaDAO();
				JsonParser parser = new JsonParser();
				
				Part part = request.getPart("json");
				InputStreamReader isr = new InputStreamReader(part.getInputStream());
				
				JsonArray array = parser.parse(isr).getAsJsonArray();
				
				for(JsonElement element : array) {
					JsonObject object = element.getAsJsonObject();
					
					int id = object.get("id").getAsInt();
					String nome = object.get("nome").getAsString();
					String url = object.get("url").getAsString();
					
					Loja loja = new Loja(id, nome, url);
					dao.create(loja);
				}
				
				request.getRequestDispatcher("/lojas").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		default:
			break;
		}
	}
	
	private Loja lojaFromRequest(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String url = request.getParameter("url");
		
		return new Loja(id, nome, url);
	}

}
