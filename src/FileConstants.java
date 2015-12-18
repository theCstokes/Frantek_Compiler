import java.nio.file.Path;
import java.nio.file.Paths;

public enum FileConstants {	
	BASE_FILE_DATA("baseAsm"), PROGRAM_FILE_DATA("program"), ASM_FILE_DATA("program.asm");
	private String path;
	
	private FileConstants(String path) {
		this.path = "data/" + path;
	}
	
	public Path getPath() {
		return Paths.get(path);
	}
	
	public String location() {
		return path;
	}
}
