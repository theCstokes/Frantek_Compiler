import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.StandardConstants;

public class VarString {
	private String  intiData;
	private String name;
	
	public VarString(String name, String data) {
		this.name = name;
		this.intiData = data;
	}
	
	public String getInit() throws IOException {
		String line = name +": db \"" + intiData + "\",10,0";
		return line;
	}
	
	public List<String> print() throws IOException {
		List<String> lines = new ArrayList<>();
		lines.add("mov eax, dword " + name );
		lines.add("call print_string");
		lines.add("call print_nl");
		return lines;
	}
	
}
