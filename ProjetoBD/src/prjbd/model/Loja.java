package prjbd.model;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class Loja {
	private int id;
	private String nome;
	private String url;
	
	public Loja(int id, String nome, String url) {
		this.id = id;
		this.nome = nome;
		this.url = url;
	}
	
	public int id() {
		return this.id;
	}
	
	public String nome() {
		return this.nome;
	}
	
	public String url() {
		return this.url;
	}
	
	public static class LojaAdapter extends TypeAdapter<Loja> {

		@Override
		public Loja read(JsonReader reader) throws IOException {
			int id = 0;
			String nome = "";
			String url = "";
			
			reader.beginObject();
			while(reader.hasNext()) {
				String jname = reader.nextName();
				switch (jname) {
				case "id":
					id = reader.nextInt();
					break;
				case "nome":
					nome = reader.nextString();
					break;
				case "url":
					url = reader.nextString();
					break;
				default:
					break;
				}
			}
			reader.endObject();
			
			return new Loja(id, nome, url);
		}

		@Override
		public void write(JsonWriter out, Loja value) throws IOException {
		}
		
	}
}
