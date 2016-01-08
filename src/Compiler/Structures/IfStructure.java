package Compiler.Structures;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import Compiler.Parser.ProgramParser;

public class IfStructure extends Structure {
	private final static String ID = "IF";
	private int elIfCount = 0;
	private List<String> declorations;

	public IfStructure() {
		super(ID);
		declorations = new ArrayList<>();
		parse();
	}

	public IfStructure(ListIterator<String> lines) {
		super(ID, lines);
		declorations = new ArrayList<>();
		parse();
	}

	private void parse() {
		int count = 0;
		List<String> segment = new ArrayList<>();
		for (String line : inputCodeLines) {
			if (line.contains("}")) {
				count--;
			}

			if (startsIfSegment(line) && count == 0) {
				parse(segment);
				segment = new ArrayList<>();
			} else {
				segment.add(line);
			}

			if (line.contains("{")) {
				count++;
			}
		}
	}

	private void parse(List<String> segment) {
		ProgramParser programParser = new ProgramParser(segment);
		programParser.parse();
	}

	private boolean startsIfSegment(String line) {
		line = line.replace(" ", "");
		switch (checkDecloration(line, "if(", "}elseif(", "}else{")) {
		case -1:
			return false;
		case 0:
			asmCodeLines.add(buildSetstionID("if"));
			return true;
		case 1:
			elIfCount++;
			asmCodeLines.add(buildSetstionID("elif" + elIfCount));
			return true;
		case 2:
			asmCodeLines.add(buildSetstionID("else"));
			return true;
		default:
			return false;
		}
	}

	private int checkDecloration(String line, String... args) {
		for (int i = 0; i < args.length; i++) {
			if (line.startsWith(args[i])) {
				declorations.add(line.replace(args[i], "").replace("){", ""));
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean declaresStructure(String line) {
		return line.startsWith("if(") && line.endsWith("){");
	}

	@Override
	public boolean endsStructure(String line) {
		return line.startsWith("}") && !line.endsWith("else");
	}

	@Override
	public Structure create(ListIterator<String> lines) {
		return new IfStructure(lines);
	}

}
