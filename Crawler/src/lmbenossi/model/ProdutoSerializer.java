package lmbenossi.model;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ProdutoSerializer implements JsonSerializer<Produto> {

	@Override
	public JsonElement serialize(Produto produto, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		
		json.add("nome", new JsonPrimitive(produto.nome()));
		json.addProperty("preco", produto.preco());
		json.addProperty("parcelas", produto.parcelas());
		json.addProperty("valorParcela", produto.valorParcela());
		json.add("disponivel", new JsonPrimitive(produto.disponivel()));
		json.addProperty("loja", produto.loja().nome());
		
		return json;
	}

}
