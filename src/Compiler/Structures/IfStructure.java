package Compiler.Structures;

import java.util.ListIterator;

public class IfStructure extends Structure {
	
	public IfStructure() {
		super();
	}
	
	public IfStructure(ListIterator<String> lines) {
		super(lines);
	}

	@Override
	public boolean declaresStructure(String line) {
		return line.startsWith("if(") && line.endsWith("){");
	}
	
	@Override
	public boolean endsStructure(String line) {
		return line.startsWith("if(") && line.endsWith("){");
	}

	@Override
	public Structure create(ListIterator<String> lines) {
		return new IfStructure(lines);
	}

}
