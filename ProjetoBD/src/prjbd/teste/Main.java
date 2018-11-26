package prjbd.teste;

import prjbd.model.parser.GpuNameParser;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.println("##" + GpuNameParser.parseName("Placa de Video Nvidia 8400GS 1GB Pcyes GDDR2 ") + "##");
	}
}
