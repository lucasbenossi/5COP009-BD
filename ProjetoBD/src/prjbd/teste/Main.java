package prjbd.teste;

import prjbd.dao.CpuDAO;
import prjbd.dao.DAO;
import prjbd.model.Cpu;

public class Main {
	public static void main(String[] args) throws Exception {
		DAO<Cpu> dao = new CpuDAO();
		
		dao.clean();
	}
}
