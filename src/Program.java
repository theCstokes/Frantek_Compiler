import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program extends BaseProgram{
	private String name;
	private File asm;
	private List<String> programLines;
	
	private Map<String, VarString>  stringData;
	
	public Program(String name) throws IOException {
		super();
		this.name = name;
		programLines = 	Files.readAllLines(Paths.get(name));
		asm = new File(FileConstants.ASM_FILE_DATA.location());
		if (asm.exists()) {
			asm.delete();
		}
		asm.createNewFile();
		stringData = new HashMap<String, VarString>();
		parse();
		write();
	}
	
	private void parse() throws IOException {
		for(String line : programLines) {
			if(line.startsWith("String")) {
				String[] lineData = line.split("=");
				VarString data = new VarString(lineData[0].split(" ")[1], lineData[1].replace("\"", "").replace(";", ""));
				stringData.put(lineData[0].split(" ")[1], data);
				addDataLine(data.getInit());
			}
			if(line.startsWith("System.out.println(")) {
				String[] lineData = line.split("\\(");
				String itemId =  lineData[1].replace(")", "").replace(";", "");
				addProgramLine(stringData.get(itemId).print());
			}
		}
	}
	
	

}
