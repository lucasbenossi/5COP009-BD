package prjbd.teste;

import prjbd.dao.GpuPrecoPorPerformanceDAO;
import prjbd.jdbc.ConnectionFactory;
import prjbd.model.PrecoPorPerformance;

public class Main {
	public static void main(String[] args) throws Exception {
		GpuPrecoPorPerformanceDAO dao = new GpuPrecoPorPerformanceDAO(ConnectionFactory.getInstance().getConnection());
		
		for(PrecoPorPerformance cpu : dao.all()) {
			System.out.println(cpu.getId() + "  " + cpu.getName());
		}
	}
}
