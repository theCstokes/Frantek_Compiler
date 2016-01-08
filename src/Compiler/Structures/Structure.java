package Compiler.Structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import Compiler.Object.Types.Type;

public abstract class Structure implements IStruct {
	private static int strucNum = 0;
	private final int ID;
	private final String structName;
	public final List<String> inputCodeLines;
	public Map<String, Type>  objects;
	public List<String> asmCodeLines;
	
	public Structure(String structName) {
		this.structName = structName;
		strucNum++;
		ID = strucNum;
		this.inputCodeLines = new ArrayList<>();
		this.asmCodeLines = new ArrayList<>();
		this.objects = new HashMap<>();	
	}
	
	public Structure(String structName, List<String> lines) {
		this.structName = structName;
		strucNum++;
		ID = strucNum;
		this.inputCodeLines = new ArrayList<>(lines);
		this.asmCodeLines = new ArrayList<>();
		this.objects = new HashMap<>();	
	}
	
	public Structure(String structName, ListIterator<String> lines) {
		this.structName = structName;
		strucNum++;
		ID = strucNum;
		List<String> programLines = new ArrayList<>();
		this.asmCodeLines = new ArrayList<>();
		this.objects = new HashMap<>();	
		lines.previous();
		while (lines.hasNext()) {
			String line = lines.next();	
			programLines.add(line);	
			if(endsStructure(line)) {
				break;
			}
		}
		this.inputCodeLines = programLines;
	}
	
	public String getStructId() {
		StringBuilder sb = new StringBuilder();
		sb.append(structName);
		sb.append("_");
		sb.append(ID);
		return sb.toString();
	}
	
	public String buildSetstionID(String Id) {
		StringBuilder sb = new StringBuilder();
		sb.append(getStructId());
		sb.append("_");
		sb.append(Id);
		sb.append(":");
		sb.append(System.lineSeparator());
		return sb.toString();
	}
}
