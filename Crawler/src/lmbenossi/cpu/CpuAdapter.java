package lmbenossi.cpu;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class CpuAdapter extends TypeAdapter<Cpu> {

	@Override
	public Cpu read(JsonReader in) throws IOException {
		return null;
	}

	@Override
	public void write(JsonWriter writer, Cpu cpu) throws IOException {
		writer.beginObject();
		writer.name("name").value(cpu.name());
		writer.name("cores").value(cpu.cores());
		writer.name("threads").value(cpu.threads());
		writer.name("frequency").value(cpu.frequency());
		writer.name("maxFrequency").value(cpu.maxFrequency());
		writer.name("scoreSingleCore").value(cpu.scoreSingleCore());
		writer.name("scoreMultiCore").value(cpu.scoreMultiCore());
		writer.name("url").value(cpu.url());
		writer.endObject();
		writer.flush();
	}

}
