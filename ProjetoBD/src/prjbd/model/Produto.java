package prjbd.model;

import java.io.IOException;
import java.math.BigDecimal;

import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class Produto {
	private int id;
	private String nome;
	private String nomeTratado;
	private BigDecimal preco;
	private int parcelas;
	private BigDecimal valorParcela;
	private int idLoja;
	private String url;
	
	public Produto(int id, String nome, String nomeTratado, BigDecimal preco, int parcelas, BigDecimal valorParcela, int idLoja, String url) {
		this.id = id;
		this.nome = nome;
		this.nomeTratado = nomeTratado;
		this.preco = preco;
		this.parcelas = parcelas;
		this.valorParcela = valorParcela;
		this.idLoja = idLoja;
		this.url = url;
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getNomeTratado() {
		return this.nomeTratado;
	}
	
	public BigDecimal getPreco() {
		return this.preco;
	}
	
	public int getParcelas() {
		return this.parcelas;
	}
	
	public BigDecimal getValorParcela() {
		return this.valorParcela;
	}
	
	public int getIdLoja() {
		return this.idLoja;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public static class ProdutoAdapter extends TypeAdapter<Produto> {

		@Override
		public Produto read(JsonReader reader) throws IOException {
			String nome = "";
			BigDecimal preco = null;
			int parcelas = -1;
			BigDecimal valorParcela = null;
			int idLoja = -1;
			String url = "";
			
			JsonParser parser = new JsonParser();
			
			reader.beginObject();
			while(reader.hasNext()) {
				String jname = reader.nextName();
				switch (jname) {
				case "nome":
					nome = reader.nextString();
					break;
				case "preco":
					preco = parser.parse(reader).getAsBigDecimal();
					break;
				case "parcelas":
					parcelas = reader.nextInt();
					break;
				case "valorParcela":
					valorParcela = parser.parse(reader).getAsBigDecimal();
					break;
				case "idLoja":
					idLoja = reader.nextInt();
					break;
				case "url":
					url = reader.nextString();
					break;
				default:
					break;
				}
			}
			reader.endObject();
			
			return new Produto(0, nome, "", preco, parcelas, valorParcela, idLoja, url);
		}

		@Override
		public void write(JsonWriter out, Produto value) throws IOException {
		}
		
	}
}
