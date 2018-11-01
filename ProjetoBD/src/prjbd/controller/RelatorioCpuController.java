package prjbd.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;

import prjbd.dao.CpuProdutoDAO;
import prjbd.model.CpuProduto;

@WebServlet("/relatorios/cpu")
public class RelatorioCpuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RelatorioCpuController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CpuProdutoDAO dao = new CpuProdutoDAO();
			List<CpuProduto> cpus = dao.all();
			
			for(CpuProduto cpu : cpus) {
				try {
					BigDecimal precoPorPerformance = new BigDecimal(cpu.getCpu().scoreMultiCore()).divide(cpu.getProduto().getPreco(), 2, RoundingMode.UP);
					
					cpu.setPrecoPorPerformance(precoPorPerformance);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			cpus.sort(new ComparatorCpuProduto());
			
			JsonArray x = new JsonArray();
			JsonArray y = new JsonArray();
			JsonArray text = new JsonArray();
			
			int i = 1;
			for(CpuProduto cpu : cpus) {
				x.add(i++ + ". " + cpu.getCpu().name());
				y.add(cpu.getPrecoPorPerformance().doubleValue());
				text.add("R$ " + cpu.getProduto().getPreco());
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

	private class ComparatorCpuProduto implements Comparator<CpuProduto> {
		@Override
		public int compare(CpuProduto o1, CpuProduto o2) {
			BigDecimal valor1 = o1.getPrecoPorPerformance().multiply(BigDecimal.valueOf(10000));
			BigDecimal valor2 = o2.getPrecoPorPerformance().multiply(BigDecimal.valueOf(10000));
			return valor1.subtract(valor2).intValue();
		}
	}
}
