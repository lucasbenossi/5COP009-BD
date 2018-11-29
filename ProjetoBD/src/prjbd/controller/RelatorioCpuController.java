package prjbd.controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;

import prjbd.dao.CpuPrecoPorPerformanceDAO;
import prjbd.dao.DAOFactory;
import prjbd.model.PrecoPorPerformance;

@WebServlet("/relatorios/cpu")
public class RelatorioCpuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RelatorioCpuController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (DAOFactory daoFac = new DAOFactory();) {
			CpuPrecoPorPerformanceDAO dao = daoFac.getCpuPrecoPorPerformanceDAO();
			LinkedList<PrecoPorPerformance> cpus = dao.all();
			
			JsonArray x = new JsonArray();
			JsonArray y = new JsonArray();
			JsonArray text = new JsonArray();
			
			int i = 1;
			for(PrecoPorPerformance cpu : cpus) {
				x.add(i++ + ". " + cpu.getName());
				y.add(cpu.getPrecoPorPerformance().doubleValue());
				text.add("R$ " + cpu.getPreco());
			}
			
			request.setAttribute("x", x.toString());
			request.setAttribute("y", y.toString());
			request.setAttribute("text", text.toString());
			request.setAttribute("cpusList", cpus);
			
			request.getRequestDispatcher("/relatorios/cpu.jsp").forward(request, response);
			
		} catch (Exception e) {
			ExceptionHandler.processExeption(request, response, e);
		}
	}
}
