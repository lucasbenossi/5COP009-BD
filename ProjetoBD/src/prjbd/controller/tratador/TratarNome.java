package prjbd.controller.tratador;

public abstract class TratarNome {
	public static String tratarNome(String nome) {
		if(nome.toLowerCase().contains("processador")) {
			return CpuNameParser.parseNome(nome);
		}
		return "";
	}
}
