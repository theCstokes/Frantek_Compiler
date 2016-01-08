package Compiler.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import Compiler.Builder;
import Compiler.Object.Types.Type;


public class ProgramParser {
	
	private List<String> programData;
	public ProgramParser(List<String> programData) {
		this.programData = programData;
	}
	
//	public void assignment(CodeLine line) {
//		if(line.hasAssignment()) {
//			
//		}
//	}
	
	public void parse() {	
		ListIterator<String> progLines = new ArrayList<>(programData).listIterator();
		while(progLines.hasNext()) {
			String sLine = progLines.next();
			System.out.println("Parsing line: " + sLine);
			parseLine(sLine);
		}
	}
	
	public static void parseLine(String sLine) {
		CodeLine line = new CodeLine(sLine);
		if(line.hasAssignment()) {
			if(line.createsType()) {
				Type t = line.getCreatedType().construct(line);
			} else {
				String targetName = line.getAssignmentTarget();
				Type target = Builder.prog.objects.get(targetName);
				target.assign(line);
			}
			return;
			
		}
		if(line.startsIf()) {
			
		}
		if(line.getLine().startsWith("System.out.println(")) {
			String[] lineData = line.getLine().split("\\(");
			String itemId =  lineData[1].replace(")", "").replace(";", "");
			System.out.println(Builder.prog.objects);
			System.out.println(Builder.prog.objects.get(itemId));
			System.out.println("getting: " + itemId);
			try {
				Builder.prog.addProgramLine(Builder.prog.objects.get(itemId).print());
			} catch (IOException e) {
				System.out.println("in");
				System.out.println("item: " + Builder.prog.objects.get(itemId));
				e.printStackTrace();
			}
			return;
		}
	}
	
	
}
