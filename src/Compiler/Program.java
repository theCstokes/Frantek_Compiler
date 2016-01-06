package Compiler;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.ListIterator;
import Compiler.Object.Types.Type;
import Compiler.Parser.ProgramParser;
import Compiler.Structures.Structure;

public class Program extends BaseProgram {
	private File asm;
	private ProgramParser programParser;
	
	
	
	public Program(String name) throws IOException {
		super(Files.readAllLines(Paths.get(name)));
		asm = new File(FileConstants.ASM_FILE_DATA.location());
		if (asm.exists()) {
			asm.delete();
		}
		asm.createNewFile();
		objects = new HashMap<String, Type>();
		programParser = new ProgramParser(inputCodeLines);
	}

	@Override
	public boolean declaresStructure(String line) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean endsStructure(String line) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Structure create(ListIterator<String> lines) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void build() {
		programParser.parse();
		write();
	}
	

}
