package prjbd.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.LinkedList;

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
			try (DAOFactory daoFac = new DAOFactory();) {
				LinkedList<Gpu> gpus = daoFac.getGpuDAO().all();
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
			try (DAOFactory daoFac = new DAOFactory();) {
				DAO<Gpu> dao = daoFac.getGpuDAO();
				Gpu gpu = gpuFromRequest(request);
				dao.create(gpu);
				request.getRequestDispatcher("/gpus").forward(request, response);
			} catch(Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/gpus/limpar":
			try (DAOFactory daoFac = new DAOFactory();) {
				DAO<Gpu> dao = daoFac.getGpuDAO();
				dao.clean();
				request.getRequestDispatcher("/gpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/gpus/alterar":
			try (DAOFactory daoFac = new DAOFactory();) {
				int id = Integer.parseInt(request.getParameter("id"));
				DAO<Gpu> dao = daoFac.getGpuDAO();
				Gpu gpu = dao.read(id);
				request.setAttribute("gpu", gpu);
				request.getRequestDispatcher("/gpus/alterar.jsp").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/gpus/alterar_processa":
			try (DAOFactory daoFac = new DAOFactory();) {
				DAO<Gpu> dao = daoFac.getGpuDAO();
				Gpu gpu = gpuFromRequest(request);
				gpu.id(Integer.parseInt(request.getParameter("id")));
				dao.update(gpu);
				request.getRequestDispatcher("/gpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/gpus/excluir":
			try (DAOFactory daoFac = new DAOFactory();) {
				DAO<Gpu> dao = daoFac.getGpuDAO();
				int id = Integer.parseInt(request.getParameter("id"));
				dao.delete(id);
				request.getRequestDispatcher("/gpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/gpus/json":
			try (DAOFactory daoFac = new DAOFactory();) {
				DAO<Gpu> dao = daoFac.getGpuDAO();
				Part part = request.getPart("json");
				
				Gson gson = new GsonBuilder().registerTypeAdapter(Gpu.class, new Gpu.GpuAdapter()).create();
				JsonReader reader = gson.newJsonReader(new InputStreamReader(part.getInputStream()));
				
				try {
					daoFac.begin();
					
					reader.beginArray();
					while(reader.hasNext()) {
						dao.create(gson.fromJson(reader, Gpu.class));
					}
					reader.endArray();
					
					daoFac.commit();
					daoFac.end();
				} catch (SQLException e) {
					daoFac.rollback();
					throw e;
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
