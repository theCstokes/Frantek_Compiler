package Compiler;
import java.io.IOException;

import Compiler.Parser.ParserUtils;

public class Builder {
	public static Program prog;

	public static void main(String[] args) throws IOException {
		prog = new Program("data/program");
		ParserUtils.parse();
		prog.write();
	}
}
