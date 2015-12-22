package Compiler.Object.Types;

import java.io.IOException;
import java.util.List;

import Compiler.Object.Operators.Operator;

public abstract class Type {
	private final String ID;
	protected String  data;
	protected String name;
	protected static List<Operator> avalibleOperators;
	
	public abstract void construct(String data);
	public abstract String getInit() throws IOException;
	public abstract List<String> print() throws IOException;
	
	public Type(String id) {
		this.ID = id;
	}
	
	public void parseTypeConstructor(String line) {
		String[] data = line.split("=");
		name = data[0].split(getID())[1].trim();
		construct(data[1]);
	}
	
	public String getName() {
		return name;
	}
	
	public String getID() {
		return ID;
	}
}
