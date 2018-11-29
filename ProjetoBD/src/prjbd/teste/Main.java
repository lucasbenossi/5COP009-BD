package prjbd.teste;

import prjbd.dao.CpuPrecoPorPerformanceDAO;
import prjbd.jdbc.ConnectionFactory;
import prjbd.model.PrecoPorPerformance;

public class Main {
	public static void main(String[] args) throws Exception {
		CpuPrecoPorPerformanceDAO dao = new CpuPrecoPorPerformanceDAO(ConnectionFactory.getInstance().getConnection());
		
		for(PrecoPorPerformance cpu : dao.all()) {
			System.out.println(cpu.getId() + "  " + cpu.getName());
		}
	}
}
