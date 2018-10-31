package prjbd.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;

import prjbd.dao.ProdutoDAO;
import prjbd.model.Produto;

@WebServlet(urlPatterns={"/relatorios/ssd"})
public class RelatorioSsdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RelatorioSsdController() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			LinkedList<Produto> produtos = dao.search("ssd%");
			LinkedList<Ssd> ssds = new LinkedList<>();
			
			for(Produto produto : produtos) {
				try {
					int gigas = parseGigas(produto.nome());
					BigDecimal precoPorGiga = produto.preco().divide(new BigDecimal(gigas), 2, RoundingMode.UP);
					
					ssds.add(new Ssd(produto, precoPorGiga));
				} catch (Exception e) {
					System.out.println("ERRO: " + produto.nome());
				}
			}
			
			ssds.sort(new ComparatorSsd());
			
			JsonArray x = new JsonArray();
			JsonArray y = new JsonArray();
			JsonArray text = new JsonArray();
			
			for(Ssd ssd : ssds) {
				x.add(ssd.produto().nome());
				y.add(ssd.precoPorGiga());
				text.add("R$ " + ssd.produto().preco());
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
    
    private int parseGigas(String nome) throws Exception {
    	Pattern regex = Pattern.compile(" ([\\d]+)GB ");
    	Matcher matcher = regex.matcher(nome);
    	
    	if(matcher.find()) {
        	return Integer.parseInt(matcher.group(1));
    	}
    	throw new Exception();
    }
    
    public class Ssd {
    	private Produto produto;
    	private BigDecimal precoPorGiga;
		
    	public Ssd(Produto produto, BigDecimal precoPorGiga) {
			this.produto = produto;
			this.precoPorGiga = precoPorGiga;
		}
    	
    	public Produto produto() {
    		return this.produto;
    	}
    	public BigDecimal precoPorGiga() {
    		return this.precoPorGiga;
    	}
    }
    
    private class ComparatorSsd implements Comparator<Ssd> {
		@Override
		public int compare(Ssd o1, Ssd o2) {
			return o1.precoPorGiga.subtract(o2.precoPorGiga).multiply(new BigDecimal(1000)).intValue();
		}
    }
}
