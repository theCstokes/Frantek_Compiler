import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Compiler {
	static List<String> lines = new ArrayList<>();
	static Map<String, VarString> stringData;
	static Program prog;

	public static void main(String[] args) throws IOException {
		prog = new Program("data/program");
	}
}
