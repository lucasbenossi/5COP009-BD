package prjbd.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
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
import prjbd.model.Cpu;

@WebServlet(urlPatterns={"/cpus", 
		"/cpus/inserir",
		"/cpus/inserir_processa",
		"/cpus/limpar",
		"/cpus/alterar",
		"/cpus/alterar_processa",
		"/cpus/excluir",
		"/cpus/json"})
@MultipartConfig
public class CpuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CpuController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getServletPath()) {
		case "/cpus":
			try (DAOFactory daoFac = new DAOFactory();) {
				List<Cpu> cpus = daoFac.getCpuDAO().all();
				request.setAttribute("cpusList", cpus);
				request.getRequestDispatcher("/cpus/listar.jsp").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/cpus/inserir":
			try {
				request.getRequestDispatcher("/cpus/inserir.jsp").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/cpus/inserir_processa":
			try (DAOFactory daoFac = new DAOFactory();) {
				DAO<Cpu> dao = daoFac.getCpuDAO();
				Cpu cpu = cpuFromRequest(request);
				dao.create(cpu);
				request.getRequestDispatcher("/cpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/cpus/limpar":
			try (DAOFactory daoFac = new DAOFactory();) {
				DAO<Cpu> dao = daoFac.getCpuDAO();
				dao.clean();
				request.getRequestDispatcher("/cpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/cpus/alterar":
			try (DAOFactory daoFac = new DAOFactory();) {
				int id = Integer.parseInt(request.getParameter("id"));
				DAO<Cpu> dao = daoFac.getCpuDAO();
				Cpu cpu = dao.read(id);
				request.setAttribute("cpu", cpu);
				request.getRequestDispatcher("/cpus/alterar.jsp").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/cpus/alterar_processa":
			try (DAOFactory daoFac = new DAOFactory();) {
				DAO<Cpu> dao = daoFac.getCpuDAO();
				Cpu cpu = cpuFromRequest(request);
				cpu.id(Integer.parseInt(request.getParameter("id")));
				dao.update(cpu);
				request.getRequestDispatcher("/cpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/cpus/excluir":
			try (DAOFactory daoFac = new DAOFactory();) {
				DAO<Cpu> dao = daoFac.getCpuDAO();
				int id = Integer.parseInt(request.getParameter("id"));
				dao.delete(id);
				request.getRequestDispatcher("/cpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/cpus/json":
			try (DAOFactory daoFac = new DAOFactory();) {
				DAO<Cpu> dao = daoFac.getCpuDAO();
				Part part = request.getPart("json");
				
				Gson gson = new GsonBuilder().registerTypeAdapter(Cpu.class, new Cpu.CpuAdapter()).create();
				JsonReader reader = gson.newJsonReader(new InputStreamReader(part.getInputStream()));

				try {
					daoFac.begin();
					
					reader.beginArray();
					while(reader.hasNext()) {
						dao.create(gson.fromJson(reader, Cpu.class));
					}
					reader.endArray();
					
					daoFac.commit();
					daoFac.end();
				} catch (SQLException e) {
					daoFac.rollback();
					throw e;
				}
				
				request.getRequestDispatcher("/cpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		default:
			break;
		}
	}

	private Cpu cpuFromRequest(HttpServletRequest request) {
		String name = request.getParameter("name");
		int cores = Integer.parseInt(request.getParameter("cores"));
		int threads = Integer.parseInt(request.getParameter("threads"));
		int frequency = Integer.parseInt(request.getParameter("frequency"));
		int maxFrequency = Integer.parseInt(request.getParameter("maxFrequency"));
		int scoreSingleCore = Integer.parseInt(request.getParameter("scoreSingleCore"));
		int scoreMultiCore = Integer.parseInt(request.getParameter("scoreMultiCore"));
		String url = request.getParameter("url");
		
		return new Cpu(0, name, cores, threads, frequency, maxFrequency, scoreSingleCore, scoreMultiCore, url);
	}
}
