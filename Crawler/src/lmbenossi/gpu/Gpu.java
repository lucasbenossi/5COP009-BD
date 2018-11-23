package lmbenossi.gpu;

public class Gpu {
	private String name;
	private int g3dMark;
	private int g2dMark;
	
	public Gpu(String name, int g3dMark, int g2dMark) {
		this.name = name;
		this.g2dMark = g2dMark;
		this.g3dMark = g3dMark;
	}
	
	public String name() {
		return this.name;
	}
	public int g3dMark() {
		return this.g3dMark;
	}
	public int g2dMark() {
		return this.g2dMark;
	}
}
