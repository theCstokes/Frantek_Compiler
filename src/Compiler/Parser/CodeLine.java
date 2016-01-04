package Compiler.Parser;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import Compiler.Object.Operators.Operators;
import Compiler.Object.Types.Types;

public class CodeLine {
	
	private String line;
	private List<String> spaceSplit;
	
	private boolean hasAssignment;
	private boolean hasEquation;
	private Types type;
	
	public CodeLine(String line) {
		this.line = line;
		spaceSplit = Arrays.asList(line.split(" "));
		hasAssignment = spaceSplit.contains(Operators.EQUALS.getID());
		hasEquation = check(Operators::isOp);
		type = Types.isType(spaceSplit.get(0));
	}
	
	public String getLine() {
		return line;
	}
	
	public boolean hasAssignment() {
		return hasAssignment;
	}
	
	public String getAssignmentTarget() {
		return getObjectName();
	}
	
	public String getAssignmentValue() {
		if(hasAssignment) {
			return line.substring(line.indexOf("=") + 1);
		} else {
			return null;
		}
	}
	
	public boolean createsType() {
		return type != null;
	}
	
	public Types getCreatedType() {
		return type;
	}
	
	public String getObjectName() {
		if(hasAssignment) {
			if(createsType()) {
				return spaceSplit.get(1).trim();
			} else {
				return spaceSplit.get(0).trim();
			}
		} else {
			return null;
		}
	}
	
	public boolean check(Function<String, Boolean> itemCheck) {
		for(String item : spaceSplit) {
			if(itemCheck.apply(item)) {
				return true;
			}
		}
		return false;
	}
	
	
	public String getValue(Function<CodeLine, String> parser) {
		if(!hasAssignment) {
			return null;
		}
		return parser.apply(this);
		
	}
}
