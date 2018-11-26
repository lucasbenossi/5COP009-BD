package prjbd.model;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class Cpu {
	private int id;
	private String name;
	private int cores;
	private int threads;
	private int frequency;
	private int maxFrequency;
	private int scoreSingleCore;
	private int scoreMultiCore;
	private String url;

	public Cpu(int id, String name, int cores, int threads, int frequency, int maxFrequency, int scoreSingleCore,
			int scoreMultiCore, String url) {
		this.id = id;
		this.name = name;
		this.cores = cores;
		this.threads = threads;
		this.frequency = frequency;
		this.maxFrequency = maxFrequency;
		this.scoreSingleCore = scoreSingleCore;
		this.scoreMultiCore = scoreMultiCore;
		this.url = url;
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
	
	public static class CpuAdapter extends TypeAdapter<Cpu> {

		@Override
		public Cpu read(JsonReader reader) throws IOException {
			String name = "";
			int cores = -1;
			int threads = -1;
			int frequency = -1;
			int maxFrequency = -1;
			int scoreSingleCore = -1;
			int scoreMultiCore = -1;
			String url = "";
			
			reader.beginObject();
			while(reader.hasNext()) {
				String jname = reader.nextName();
				switch (jname) {
				case "name":
					name = reader.nextString();
					break;
				case "cores":
					cores = reader.nextInt();
					break;
				case "threads":
					threads = reader.nextInt();
					break;
				case "frequency":
					frequency = reader.nextInt();
					break;
				case "maxFrequency":
					maxFrequency = reader.nextInt();
					break;
				case "scoreSingleCore":
					scoreSingleCore = reader.nextInt();
					break;
				case "scoreMultiCore":
					scoreMultiCore = reader.nextInt();
					break;
				case "url":
					url = reader.nextString();
					break;
				default:
					break;
				}
			}
			reader.endObject();
			return new Cpu(0, name, cores, threads, frequency, maxFrequency, scoreSingleCore, scoreMultiCore, url);
		}

		@Override
		public void write(JsonWriter out, Cpu value) throws IOException {
		}
		
	}
}
