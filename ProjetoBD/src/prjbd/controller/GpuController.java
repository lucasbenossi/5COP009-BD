package prjbd.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

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
import prjbd.dao.GpuDAO;
import prjbd.model.Gpu;

@WebServlet(urlPatterns={"/gpus", 
		"/gpus/inserir",
		"/gpus/inserir_processa",
		"/gpus/limpar",
		"/gpus/alterar",
		"/gpus/alterar_processa",
		"/gpus/excluir",
		"/gpus/json"})
@MultipartConfig
public class GpuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GpuController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getServletPath()) {
		case "/gpus":
			try {
				LinkedList<Gpu> gpus = new GpuDAO().all();
				request.setAttribute("gpusList", gpus);
				request.getRequestDispatcher("/gpus/listar.jsp").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/gpus/inserir":
			try {
				request.getRequestDispatcher("/gpus/inserir.jsp").forward(request, response);
			} catch(Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/gpus/inserir_processa":
			try {
				DAO<Gpu> dao = new GpuDAO();
				Gpu gpu = gpuFromRequest(request);
				dao.create(gpu);
				request.getRequestDispatcher("/gpus").forward(request, response);
			} catch(Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/gpus/limpar":
			try {
				DAO<Gpu> dao = new GpuDAO();
				dao.clean();
				request.getRequestDispatcher("/gpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/gpus/alterar":
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				DAO<Gpu> dao = new GpuDAO();
				Gpu gpu = dao.read(id);
				request.setAttribute("gpu", gpu);
				request.getRequestDispatcher("/gpus/alterar.jsp").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/gpus/alterar_processa":
			try {
				DAO<Gpu> dao = new GpuDAO();
				Gpu gpu = gpuFromRequest(request);
				gpu.id(Integer.parseInt(request.getParameter("id")));
				dao.update(gpu);
				request.getRequestDispatcher("/gpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/gpus/excluir":
			try {
				DAO<Gpu> dao = new GpuDAO();
				int id = Integer.parseInt(request.getParameter("id"));
				dao.delete(id);
				request.getRequestDispatcher("/gpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/gpus/json":
			try {
				DAO<Gpu> dao = new GpuDAO();
				JsonParser parser = new JsonParser();
				
				Part part = request.getPart("json");
				InputStreamReader isr = new InputStreamReader(part.getInputStream());
				
				JsonArray array = parser.parse(isr).getAsJsonArray();
				
				for(JsonElement element : array) {
					JsonObject object = element.getAsJsonObject();
					
					String name = object.get("name").getAsString();
					int g3dMark = object.get("g3dMark").getAsInt();
					int g2dMark = object.get("g2dMark").getAsInt();
					
					Gpu gpu = new Gpu(0, name, g3dMark, g2dMark);
					dao.create(gpu);
				}
				
				request.getRequestDispatcher("/gpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		default:
			break;
		}
	}
	
	private Gpu gpuFromRequest(HttpServletRequest request) {
		String name = request.getParameter("name");
		int g3dMark = Integer.parseInt(request.getParameter("g3dMark"));
		int g2dMark = Integer.parseInt(request.getParameter("g2dMark"));
		
		return new Gpu(0, name, g3dMark, g2dMark);
	}
}
