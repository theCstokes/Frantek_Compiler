package Compiler.Structures;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Function;

public enum Structures implements IStruct {
	
	IF(new IfStructure(), IfStructure::new);
	
	private IStruct structure;
	private Function<ListIterator<String>, Structure> constructor;
	private Structures(IStruct structure, Function<ListIterator<String>, Structure> constructor) {
		this.structure = structure;
		this.constructor = constructor;
	}

	@Override
	public boolean declaresStructure(String line) {
		return structure.declaresStructure(line);
	}

	@Override
	public boolean endsStructure(String line) {
		return structure.endsStructure(line);
	}
	
	@Override
	public Structure create(ListIterator<String> lines) {
		return constructor.apply(lines);
	}

}
