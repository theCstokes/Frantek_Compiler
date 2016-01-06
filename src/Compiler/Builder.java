package Compiler;
import java.io.IOException;

import Compiler.Parser.ProgramParser;

public class Builder {
	public static Program prog;

	public static void main(String[] args) throws IOException {
		prog = new Program("data/program");
//		Parser.parse();
		prog.build();
	}
}
