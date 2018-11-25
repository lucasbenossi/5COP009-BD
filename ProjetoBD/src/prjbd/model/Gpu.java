package prjbd.model;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

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
	
	public static class GpuAdapter extends TypeAdapter<Gpu> {
		@Override
		public Gpu read(JsonReader reader) throws IOException {
			if(reader.peek() == JsonToken.NULL) {
				reader.nextNull();
				return null;
			}
			reader.beginObject();
			
			String name = "";
			int g3dMark = -1;
			int g2dMark = -1;
			
			while(reader.hasNext()) {
				String nameToken = reader.nextName();
				if(nameToken.equals("name")) {
					name = reader.nextString();
				} else if(nameToken.equals("g3dMark")) {
					g3dMark = reader.nextInt();
				} else if(nameToken.equals("g2dMark")) {
					g2dMark = reader.nextInt();
				}
			}
			
			reader.endObject();
			
			return new Gpu(0, name, g3dMark, g2dMark);
		}

		@Override
		public void write(JsonWriter out, Gpu value) throws IOException {
			// TODO Auto-generated method stub
		}
	}
}
