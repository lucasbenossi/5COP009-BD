package lmbenossi.produto;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class ProdutoAdapter extends TypeAdapter<Produto> {

	@Override
	public Produto read(JsonReader in) throws IOException {
		return null;
	}

	@Override
	public void write(JsonWriter writer, Produto produto) throws IOException {
		writer.beginObject();
		writer.name("nome").value(produto.nome());
		writer.name("preco").value(produto.preco());
		writer.name("parcelas").value(produto.parcelas());
		writer.name("valorParcela").value(produto.valorParcela());
		writer.name("disponivel").value(produto.disponivel());
		writer.name("loja").value(produto.loja().nome());
		writer.endObject();
		writer.flush();
	}
	
}
