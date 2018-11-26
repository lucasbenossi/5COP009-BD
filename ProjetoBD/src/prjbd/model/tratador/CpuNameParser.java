package prjbd.model.tratador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CpuNameParser {
	
	public static String parseNome(String input) {
		input = input.toLowerCase();
		
		String brand = null;
		String category = null;
		String model = null;
		
		brand = parse(" (intel|amd) ", input, 1);
		switch(brand) {
		case "intel":
			category = parse(" (celeron|pentium|core|xeon) ", input, 1);
			if(category == null) {
				category = "core";
			}
			
			switch (category) {
			case "celeron":
				model = parse(" (g[0-9]{4}) ", input, 1);
				break;
			case "pentium":
				model = parse(" (g[0-9]{4}) ", input, 1);
				break;
			case "xeon":
				model = parse(" (e[35]-[0-9]{4}v[0-9])", input, 1);
				if(model != null) {
					model = model.replaceAll("(e[35]-[0-9]{4})(v[0-9])", "$1 $2");
				} else {
					model = parse("intel ([0-9]{4}) xeon silver", input, 1);
					if(model != null) {
						model = "silver " + model; 
					} else {
						model = parse("intel ([0-9]{4}) xeon gold", input, 1);
						if(model != null) {
							model = "gold " + model;
						} else {
							model = "";
						}
					}
				}
				break;
			case "core":
				model = parse(" (i[3579])[ -]", input, 1);
				model += "-";
				model += parse("[ -]([0-9]{4}[a-z]?) ", input, 1);
				break;
			default:
				break;
			}
			break;
		case "amd":
			category = parse(new String[] {" (fx-)", " (ryzen|athlon) ", "(a(4|6|8|10|12))", "(sempron)"}, input, 1);
			switch (category) {
			case "fx-":
				category = "fx";
				model = parse(" fx-([0-9]{4}[a-z]?)", input, 1);
				break;
			case "ryzen":
				model = parse("ryzen ([357] [0-9]{4}[a-z]?)", input, 1);
				break;
			case "athlon":
				model = parse("athlon ([^ ]+)", input, 1);
				break;
			case "sempron":
				model = parse("sempron ([0-9]{4})", input, 1);
				break;
			default:
				model = parse("a(4|6|8|10|12)[- ]([0-9]{4}[a-z]?)", input, 2);
				break;
			}
			break;
		default:
			break;
		}
		
		return brand + " " + category + " " + model;
	}
	
	private static String parse(String regex, String input, int group) {
		return parse(new String[] {regex}, input, group);
	}
	
	private static String parse(String[] regexes, String input, int group) {
		for(String regex : regexes) {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(input);
			if(matcher.find()) {
				return matcher.group(group);
			}
		}
		return null;
	}
}

