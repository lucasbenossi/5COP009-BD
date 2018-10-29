package lmbenossi.cpu;

public class Cpu {
	private String name;
	private int cores;
	private int threads;
	private int frequency;
	private int maxFrequency;
	private int scoreSingleCore;
	private int scoreMultiCore;
	private String url;

	public Cpu(String name, int cores, int threads, int frequency, int maxFrequency, int scoreSingleCore,
			int scoreMultiCore, String url) {
		super();
		this.name = name;
		this.cores = cores;
		this.threads = threads;
		this.frequency = frequency;
		this.maxFrequency = maxFrequency;
		this.scoreSingleCore = scoreSingleCore;
		this.scoreMultiCore = scoreMultiCore;
		this.url = url;
	}

	public String name() {
		return this.name;
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
	
	public String url() {
		return this.url;
	}
}
