package prjbd.controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;

import prjbd.dao.DAOFactory;
import prjbd.dao.GpuPrecoPorPerformanceDAO;
import prjbd.model.PrecoPorPerformance;

@WebServlet("/relatorios/gpu")
public class RelatorioGpuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RelatorioGpuController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (DAOFactory daoFac = new DAOFactory();) {
			GpuPrecoPorPerformanceDAO dao = daoFac.getGpuPrecoPorPerformanceDAO();
			LinkedList<PrecoPorPerformance> gpus = dao.all();
			
			JsonArray x = new JsonArray();
			JsonArray y = new JsonArray();
			JsonArray text = new JsonArray();
			
			int i = 1;
			for(PrecoPorPerformance gpu : gpus) {
				x.add(i++ + ". " + gpu.getName());
				y.add(gpu.getPrecoPorPerformance().doubleValue());
				text.add("R$ " + gpu.getPreco());
			}
			
			request.setAttribute("x", x.toString());
			request.setAttribute("y", y.toString());
			request.setAttribute("text", text.toString());
			request.setAttribute("gpusList", gpus);
			
			request.getRequestDispatcher("/relatorios/gpu.jsp").forward(request, response);
		} catch (Exception e) {
			ExceptionHandler.processExeption(request, response, e);
		}
	}
}
