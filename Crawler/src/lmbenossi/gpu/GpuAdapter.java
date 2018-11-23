package lmbenossi.gpu;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class GpuAdapter extends TypeAdapter<Gpu> {

	@Override
	public Gpu read(JsonReader in) throws IOException {
		return null;
	}

	@Override
	public void write(JsonWriter out, Gpu value) throws IOException {
		out.beginObject();
		out.name("name").value(value.name());
		out.name("g2dMark").value(value.g2dMark());
		out.name("g3dMark").value(value.g3dMark());
		out.endObject();
		out.flush();
	}

}
