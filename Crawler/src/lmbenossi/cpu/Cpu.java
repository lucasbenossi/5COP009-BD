package lmbenossi.cpu;

import java.io.PrintStream;

public class Cpu {
	private String brand;
	private String model;
	private int cores;
	private int threads;
	private int frequency;
	private int maxFrequency;
	private int scoreSingleCore;
	private int scoreMultiCore;

	public Cpu(String brand, String model, int cores, int threads, int frequency, int maxFrequency, int scoreSingleCore,
			int scoreMultiCore) {
		super();
		this.brand = brand;
		this.model = model;
		this.cores = cores;
		this.threads = threads;
		this.frequency = frequency;
		this.maxFrequency = maxFrequency;
		this.scoreSingleCore = scoreSingleCore;
		this.scoreMultiCore = scoreMultiCore;
	}

	public String brand() {
		return brand;
	}

	public String model() {
		return model;
	}
	
	public int cores() {
		return this.cores;
	}
	
	public int threads() {
		return this.threads;
	}
	
	public int frequency() {
		return this.frequency;
	}
	
	public int maxFrequency() {
		return this.maxFrequency;
	}

	public int scoreSingleCore() {
		return scoreSingleCore;
	}

	public int scoreMultiCore() {
		return scoreMultiCore;
	}
	
	public void print(PrintStream out) {
		out.println(this.brand);
		out.println(this.model);
		out.println(this.cores);
		out.println(this.threads);
		out.println(this.frequency);
		out.println(this.maxFrequency);
		out.println(this.scoreSingleCore);
		out.println(this.scoreMultiCore);
	}
	
	public void print() {
		this.print(System.out);
	}
	
	public String name() {
		return this.brand + " " + this.model;
	}
	
	public static String parseBrand(String name) {
		return name.split(" ")[0];
	}
	
	public static String parseModel(String name) {
		String[] tokens = name.split(" ");
		String model = "";
		for(int i = 1; i < tokens.length; i++) {
			model += tokens[i] + " ";
		}
		return model.trim();
	}
}
