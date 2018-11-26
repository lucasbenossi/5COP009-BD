package prjbd.model.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class NameParser {
	
	public static String parse(String regex, String input, int group) {
		return parse(new String[] {regex}, input, group);
	}
	
	public static String parse(String[] regexes, String input, int group) {
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
