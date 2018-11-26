package prjbd.teste;

public class Main {
	public static void main(String[] args) throws Exception {
		stringsIt(new String[]{" (fx-)", " (ryzen|athlon) ", "(a(4|6|8|10|12))", "(sempron)"});
	}
	
	private static void stringsIt(String[] strings) {
		for(String string : strings) {
			System.out.println(string);
		}
	}
}
