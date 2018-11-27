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

import prjbd.dao.DAOFactory;
import prjbd.dao.GpuProdutoDAO;
import prjbd.model.GpuProduto;

@WebServlet("/relatorios/gpu")
public class RelatorioGpuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RelatorioGpuController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (DAOFactory daoFac = new DAOFactory();) {
			GpuProdutoDAO dao = daoFac.getGpuProdutoDAO();
			List<GpuProduto> gpus = dao.all();
			
			for(GpuProduto gpu : gpus) {
				try {
					BigDecimal precoPorPerformance = new BigDecimal(gpu.getGpu().g3dMark()).divide(gpu.getProduto().getPreco(), 2, RoundingMode.UP);
					
					gpu.setPrecoPorPerformance(precoPorPerformance);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			gpus.sort(new CompratorGpuProduto());
			
			JsonArray x = new JsonArray();
			JsonArray y = new JsonArray();
			JsonArray text = new JsonArray();
			
			int i = 1;
			for(GpuProduto gpu : gpus) {
				x.add(i++ + ". " + gpu.getGpu().name());
				y.add(gpu.getPrecoPorPerformance().doubleValue());
				text.add("R$ " + gpu.getProduto().getPreco());
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
	
	private class CompratorGpuProduto implements Comparator<GpuProduto> {

		@Override
		public int compare(GpuProduto o1, GpuProduto o2) {
			BigDecimal valor1 = o1.getPrecoPorPerformance().multiply(BigDecimal.valueOf(10000));
			BigDecimal valor2 = o2.getPrecoPorPerformance().multiply(BigDecimal.valueOf(10000));
			return valor1.subtract(valor2).intValue();
		}
		
	}
}
