package prjbd.model.parser;

public abstract class GpuNameParser {
	
	public static String parseName(String input) {
		input = input.toLowerCase();
		
		String category = null;
		String model = null;
		
		model = NameParser.parse(new String[] {" (r[579x][ ]?[0-9]{3}[x]?) ", 
				" (gt[ ]?[0-9]{3,4}) ", 
				" (gtx[ ]?[0-9]{3,4}[ ,]?(ti)?) ",
				" (rtx[ ]?[0-9]{4}[ ]?(ti)?) ",
				" (vega [0-9]{2}) ", 
				" (gtx titan x) ",
				" (r9 fury) ",
				" ([0-9]{4}[ ]?g(t|s)) ",
				" (hd[0-9]{4}) ",
				" (quadro [^ ]*) "
				}, input, 1);
		if(model == null) {
			return "";
		}
		
		if(model.matches("r[579x][ ]?[0-9]{3}[x]?")) {
			category = "radeon";
			
			model = model.replace(" ", "").replaceAll("(r[579x])([0-9]{3}[x]?)", "$1 $2");
			
		} else if(model.matches("gt[ ]?[0-9]{3,4}")) {
			category = "geforce";
			
			model = model.replace(" ", "").replaceAll("gt([0-9]{3,4})", "gt $1");
		} else if(model.matches("gtx[ ]?[0-9]{3,4}[ ,]?(ti)?")) {
			category = "geforce";

			model = model.replace(" ", "").replace(",", "").replaceAll("gtx([0-9]{3,4})(ti)?", "gtx $1 $2").trim();
			
		} else if(model.matches("rtx[ ]?[0-9]{4}[ ]?(ti)?")) { 
			category = "geforce";
			
			model = model.replace(" ", "").replaceAll("rtx([0-9]{4})(ti)?", "rtx $1 $2").trim();
			
		} else if(model.contains("vega")) {
			category = "radeon";
			
			model = "pro " + model;
			
		} else if(model.matches("gtx titan x")) {
			category = "geforce";
			
		} else if(model.matches("r9 fury")) {
			category = "radeon";
			
		} else if(model.matches("[0-9]{4}[ ]?g(t|s)")) {
			category = "geforce";
			
			model = model.replace(" ", "").replaceAll("([0-9]{4})g(t|s)", "$1 g$2");
			
		} else if(model.matches("hd[0-9]{4}")) {
			category = "radeon";
			
			model = model.replaceAll("hd([0-9]{4})", "hd $1");
			
		} else if(model.matches("quadro [^ ]*")) {
			category = "";
			
		}
		
		return (category + " " + model).trim();
	}

}
