package lmbenossi.produto;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public enum Loja {
	PICHAU("Pichau"),
	LONDRITECH("Londritech");
	
	private String nome;
	
	private Loja(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public static void createJson(Writer out) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Loja.class, new LojaAdapter()).create();
		JsonWriter writer = gson.newJsonWriter(out);
		
		writer.beginArray().flush();
		
		for(Loja loja : Loja.values()) {
			gson.toJson(gson.toJsonTree(loja), writer);
			writer.flush();
		}
		
		writer.endArray().flush();
	}
	
	private static class LojaAdapter extends TypeAdapter<Loja> {

		@Override
		public Loja read(JsonReader in) throws IOException {
			return null;
		}

		@Override
		public void write(JsonWriter out, Loja loja) throws IOException {
			out.beginObject();
			out.name("id").value(loja.ordinal());
			out.name("nome").value(loja.getNome());
			out.endObject();
			out.flush();
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		Loja.createJson(new FileWriter("lojas.json"));
	}
}
