package prjbd.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;

import prjbd.dao.SsdProdutoDAO;
import prjbd.model.SsdProduto;

@WebServlet(urlPatterns={"/relatorios/ssd"})
public class RelatorioSsdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RelatorioSsdController() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SsdProdutoDAO dao = new SsdProdutoDAO();
			List<SsdProduto> ssdsFromDAO = dao.all();
			LinkedList<SsdProduto> ssds = new LinkedList<>();
			
			for(SsdProduto ssd : ssdsFromDAO) {
				try {
					int gigas = parseGigas(ssd.getProduto().getNome());
					BigDecimal precoPorGiga = ssd.getProduto().getPreco().divide(new BigDecimal(gigas), 2, RoundingMode.UP);
					
					ssd.setPrecoPorGiga(precoPorGiga);
					ssds.add(ssd);
				} catch (Exception e) {
					System.out.println("ERRO: " + ssd.getProduto().getNome());
					e.printStackTrace();
				}
			}
			
			ssds.sort(new ComparatorSsd());
			
			JsonArray x = new JsonArray();
			JsonArray y = new JsonArray();
			JsonArray text = new JsonArray();
			
			for(SsdProduto ssd : ssds) {
				x.add(ssd.getProduto().getNome());
				y.add(ssd.getPrecoPorGiga());
				text.add("R$ " + ssd.getProduto().getPreco());
			}
			
			request.setAttribute("x", x.toString());
			request.setAttribute("y", y.toString());
			request.setAttribute("text", text.toString());
			request.setAttribute("ssdsList", ssds);
			
			request.getRequestDispatcher("/relatorios/ssd.jsp").forward(request, response);
		} catch (Exception e) {
			ExceptionHandler.processExeption(request, response, e);
		}
	}
    
    private class ComparatorSsd implements Comparator<SsdProduto> {
		@Override
		public int compare(SsdProduto o1, SsdProduto o2) {
			return o1.getPrecoPorGiga().subtract(o2.getPrecoPorGiga()).multiply(new BigDecimal(100)).intValue();
		}
    }
    
    private static int parseGigas(String nome) {
    	Pattern regex1 = Pattern.compile(" ([\\d]+)[ ]?G[B]?[ ,]", Pattern.CASE_INSENSITIVE);
    	Pattern regex2 = Pattern.compile(" ([\\d]+)TB ", Pattern.CASE_INSENSITIVE);

    	Matcher matcher = regex1.matcher(nome);
    	if(matcher.find()) {
    		return Integer.parseInt(matcher.group(1));
    	}
    	else {
    		matcher = regex2.matcher(nome);
    		if(matcher.find()) {
    			return 1000 * Integer.parseInt(matcher.group(1));
    		}
    	}
    	return 0;
    }
}
