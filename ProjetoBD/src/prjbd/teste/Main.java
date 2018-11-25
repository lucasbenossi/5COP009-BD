package prjbd.teste;

import prjbd.dao.DAO;
import prjbd.dao.GpuDAO;
import prjbd.model.Gpu;

public class Main {
	public static void main(String[] args) throws Exception {
		DAO<Gpu> dao = new GpuDAO();
		
		for(Gpu gpu : dao.search("%4%")) {
			System.out.println(gpu.name());
		}
	}
}
