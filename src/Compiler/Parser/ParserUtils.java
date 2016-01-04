package Compiler.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Compiler.Builder;
import Compiler.Object.Types.Type;


public class ParserUtils {
	
	public static void assignment(CodeLine line) {
		if(line.hasAssignment()) {
			
		}
	}
	
	public static void parse() {	
		List<String> progLines = new ArrayList<>(Builder.prog.getInputProgramLines());
		for(String stringLine : progLines) {
			System.out.println("Parsing line: " + stringLine);
			CodeLine line = new CodeLine(stringLine);
			if(line.createsType()) {
				Type t = line.getCreatedType().construct(line);
				continue;
			}
			if(line.hasAssignment()) {
				String targetName = line.getAssignmentTarget();
				Type target = Builder.prog.objects.get(targetName);
				target.assign(line);
				continue;
				
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
				continue;
			}
//			if(line.contains("+")) {
//				String[] lineData = line.split("\\+");
//				if(Builder.prog.objects.containsKey(lineData[0].trim()) && Builder.prog.objects.containsKey(lineData[1].trim())) {
//					String obj1 = lineData[0].trim();
//					String obj2 = lineData[1].trim();
//					if(Builder.prog.objects.get(obj1) instanceof Addable) {
//						((Addable) Builder.prog.objects.get(obj1)).add(Builder.prog.objects.get(obj2));
//					}
//				}
//			}
		}
	}
	
	
}
