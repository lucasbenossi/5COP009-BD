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

import prjbd.dao.CpuDAO;
import prjbd.dao.DAO;
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
			try {
				List<Cpu> cpus = new CpuDAO().all();
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
			try {
				DAO<Cpu> dao = new CpuDAO();
				Cpu cpu = cpuFromRequest(request);
				dao.create(cpu);
				request.getRequestDispatcher("/cpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/cpus/limpar":
			try {
				DAO<Cpu> dao = new CpuDAO();
				dao.clean();
				request.getRequestDispatcher("/cpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/cpus/alterar":
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				DAO<Cpu> dao = new CpuDAO();
				Cpu cpu = dao.read(id);
				request.setAttribute("cpu", cpu);
				request.getRequestDispatcher("/cpus/alterar.jsp").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/cpus/alterar_processa":
			try {
				DAO<Cpu> dao = new CpuDAO();
				Cpu cpu = cpuFromRequest(request);
				cpu.id(Integer.parseInt(request.getParameter("id")));
				dao.update(cpu);
				request.getRequestDispatcher("/cpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/cpus/excluir":
			try {
				DAO<Cpu> dao = new CpuDAO();
				int id = Integer.parseInt(request.getParameter("id"));
				dao.delete(id);
				request.getRequestDispatcher("/cpus").forward(request, response);
			} catch (Exception e) {
				ExceptionHandler.processExeption(request, response, e);
			}
			break;
		case "/cpus/json":
			try {
				DAO<Cpu> dao = new CpuDAO();
				JsonParser parser = new JsonParser();
				
				Part part = request.getPart("json");
				InputStreamReader isr = new InputStreamReader(part.getInputStream());
				
				JsonArray array = parser.parse(isr).getAsJsonArray();
				
				for(JsonElement element : array) {
					JsonObject object = element.getAsJsonObject();
					
					String name = object.get("name").getAsString();
					int cores = object.get("cores").getAsInt();
					int threads = object.get("threads").getAsInt();
					int frequency = object.get("frequency").getAsInt();
					int maxFrequency = object.get("maxFrequency").getAsInt();
					int scoreSingleCore = object.get("scoreSingleCore").getAsInt();
					int scoreMultiCore = object.get("scoreMultiCore").getAsInt();
					
					Cpu cpu = new Cpu(0, name, cores, threads, frequency, maxFrequency, scoreSingleCore, scoreMultiCore);
					dao.create(cpu);
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
		
		return new Cpu(0, name, cores, threads, frequency, maxFrequency, scoreSingleCore, scoreMultiCore);
	}
}
