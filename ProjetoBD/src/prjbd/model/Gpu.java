package prjbd.model;

public class Gpu {
	private int id;
	private String name;
	private int g3dMark;
	private int g2dMark;
	
	public Gpu(int id, String name, int g3dMark, int g2dMark) {
		this.id = id;
		this.name = name;
		this.g3dMark = g3dMark;
		this.g2dMark = g2dMark;
	}
	
	public int id() {
		return this.id;
	}
	public void id(int id) {
		this.id = id;
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
