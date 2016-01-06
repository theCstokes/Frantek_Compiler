package Compiler.Structures;

import java.util.ListIterator;

public interface IStruct {
	public boolean declaresStructure(String line);
	
	public boolean endsStructure(String line);
	
	public Structure create(ListIterator<String> lines);
}
