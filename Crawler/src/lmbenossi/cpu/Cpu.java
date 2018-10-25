package lmbenossi.cpu;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cpu {
	private String brand;
	private String model;
	private int cores;
	private double frequency;
	private int scoreSingleCore;
	private int scoreMultiCore;
	
	public Cpu(String input) {
		this.brand = parseBrand(input);
		this.model = parseModel(input);
		this.cores = parseCores(input);
		this.frequency = parseFrequency(input);
		this.scoreSingleCore = -1;
		this.scoreMultiCore = -1;
	}
	
	public Cpu(String brand, String model, int cores, double frequency, int scoreSingleCore, int scoreMultiCore) {
		this.brand = brand;
		this.model = model;
		this.cores = cores;
		this.frequency = frequency;
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
	
	public double frequency() {
		return this.frequency;
	}

	public int scoreSingleCore() {
		return scoreSingleCore;
	}
	public void scoreSingleCore(int value) {
		this.scoreSingleCore = value;
	}

	public int scoreMultiCore() {
		return scoreMultiCore;
	}
	public void scoreMultiCore(int value) {
		this.scoreMultiCore = value;
	}
	
	public void print(PrintStream out) {
		out.println(this.brand);
		out.println(this.model);
		out.println(this.cores);
		out.println(this.frequency);
		out.println(this.scoreSingleCore);
		out.println(this.scoreMultiCore);
	}
	
	public void print() {
		this.print(System.out);
	}
	
	public String name() {
		return this.brand + " " + this.model;
	}
	
	public static String parseBrand(String input) {
		return input.split(" ")[0];
	}
	
	public static String parseModel(String input) {
		String[] tokens = input.split(" ");
		String model = "";
		for(int i = 1; i < tokens.length - 4; i++) {
			model += tokens[i] + " ";
		}
		return model.trim();
	}
	
	public static int parseCores(String input) {
		Pattern pattern = Pattern.compile("([0-9]+) core[s]?");
		Matcher matcher = pattern.matcher(input);
		
		if(matcher.find()) {
			String coresString = matcher.group(1);
			return Integer.parseInt(coresString);
		}
		return -1;
	}
	
	public static double parseFrequency(String input) {
		Pattern pattern = Pattern.compile("([0-9]+\\.[0-9]+) GHz");
		Matcher matcher = pattern.matcher(input);
		
		if(matcher.find()) {
			String freqString = matcher.group(1);
			return Double.parseDouble(freqString);
		}
		return -1;
	}
}
