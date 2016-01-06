package Compiler.Structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import Compiler.Object.Types.Type;

public abstract class Structure implements IStruct {
	
	private final String decloration;
	public final List<String> inputCodeLines;
	public Map<String, Type>  objects;
	
	public Structure() {
		this.inputCodeLines = new ArrayList<>();
		this.objects = new HashMap<>();	
		decloration = "";
	}
	
	public Structure(List<String> lines) {
		this.inputCodeLines = new ArrayList<>(lines);
		this.objects = new HashMap<>();	
		decloration = "";
	}
	
	public Structure(ListIterator<String> lines) {
		List<String> programLines = new ArrayList<>();
		this.objects = new HashMap<>();	
		decloration = lines.previous();
		lines.next();
		while (lines.hasNext()) {
			String line = lines.next();
			if(endsStructure(line)) {
				break;
			}	
			programLines.add(line);		
		}
		this.inputCodeLines = programLines;
	}
}
