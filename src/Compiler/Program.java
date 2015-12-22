package Compiler;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Compiler.Object.Types.Type;
import Compiler.Object.Types.Types;
import Compiler.Object.Types.VString;

public class Program extends BaseProgram{
	private String name;
	private File asm;
	
	public List<String> programLines;
	public Map<String, Type>  objects;
	
	public Program(String name) throws IOException {
		super();
		this.name = name;
		programLines = 	Files.readAllLines(Paths.get(name));
		asm = new File(FileConstants.ASM_FILE_DATA.location());
		if (asm.exists()) {
			asm.delete();
		}
		asm.createNewFile();
		objects = new HashMap<String, Type>();
	}	
	

}
