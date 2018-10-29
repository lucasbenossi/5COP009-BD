package lmbenossi.test;

public class Test {
	public static void main(String[] args) {
		try {
			func1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void func1() throws Exception {
		try {
			func2();
		} catch(Exception e) {
			Exception e2 = new Exception();
			e2.setStackTrace(e.getStackTrace());
			throw e2;
		}
	}
	
	public static void func2() throws Exception {
		func3();
	}
	
	public static void func3() throws Exception {
		throw new Exception();
	}
}
