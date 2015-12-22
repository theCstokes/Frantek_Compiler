package Compiler;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class BaseProgram {

	private final String IMPORTS_TAG = ";//IMPORT";
	private final String DATA_TAG = ";//DATA";
	private final String BSS_TAG = ";//BSS";
	private final String TEXT_TAG = ";//TEXT";
	private final String START_TAG = ";//START";
	private final String END_TAG = ";//END";

	private List<String> refs;
	private List<String> data;
	private List<String> bss;
	private List<String> text;
	private List<String> start;
	private List<String> prog;
	private List<String> end;

	public BaseProgram() {
		refs = new ArrayList<>();
		data = new ArrayList<>();
		bss = new ArrayList<>();
		text = new ArrayList<>();
		start = new ArrayList<>();
		prog = new ArrayList<>();
		end = new ArrayList<>();
		parse();

	}

	private void parse() {
		List<String> baseData = null;
		try {
			baseData = Files.readAllLines(FileConstants.BASE_FILE_DATA.getPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (baseData != null) {
			for (int i = 0; i < baseData.size(); i++) {
				switch (baseData.get(i)) {
				case IMPORTS_TAG:
					store(refs, baseData, i);
					break;
				case DATA_TAG:
					store(data, baseData, i);
					break;
				case BSS_TAG:
					store(bss, baseData, i);
					break;
				case TEXT_TAG:
					store(text, baseData, i);
					break;
				case START_TAG:
					store(start, baseData, i);
					break;
				case END_TAG:
					store(end, baseData, i);
					break;
				default:
					break;
				}
			}
		}

	}

	public void addProgramLine(String line) {
		prog.add(line);
	}

	public void addDataLine(String line) {
		data.add(line);
	}

	public void addBSSLine(String line) {
		bss.add(line);
	}
	
	public void addProgramLine(List<String> line) {
		prog.addAll(line);
	}

	public void addDataLine(List<String> line) {
		data.addAll(line);
	}

	public void addBSSLine(List<String> line) {
		bss.addAll(line);
	}

	public void write() {
		try {
			writeSection(refs);
			writeSection(data);
			writeSection(bss);
			writeSection(text);
			writeSection(start);
			writeSection(prog);
			writeSection(end);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeSection(List<String> section) throws IOException {
		for (String line : section) {
			Files.write(FileConstants.ASM_FILE_DATA.getPath(), (line + System.lineSeparator()).getBytes(),
					StandardOpenOption.APPEND);
		}
	}

	private void store(List<String> data, List<String> baseData, int i) {
		while ((i+1) < baseData.size() && !baseData.get(i + 1).startsWith(";//")) {
			++i;
			data.add(baseData.get(i));
		}
	}

}
