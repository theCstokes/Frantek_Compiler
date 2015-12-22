package Compiler.Parser;

import java.io.IOException;

import Compiler.Builder;
import Compiler.Object.Operators.Addable;
import Compiler.Object.Operators.Operator;
import Compiler.Object.Operators.Operators;
import Compiler.Object.Types.Type;
import Compiler.Object.Types.Types;

public class ParserUtils {
	
	public static void parse() throws IOException {
		for(String line : Builder.prog.programLines) {
			String ID = line.split(" ")[0].split(" = ")[0];
			Types type = Types.isType(ID);
			if(type != null) {
				Type t = type.construct(line);
				Builder.prog.objects.put(t.getName(), t);
				System.out.println(Builder.prog.objects);
				Builder.prog.addDataLine(t.getInit());
				continue;
			}
			if(line.startsWith("System.out.println(")) {
				String[] lineData = line.split("\\(");
				String itemId =  lineData[1].replace(")", "").replace(";", "");
				System.out.println(Builder.prog.objects);
				System.out.println(Builder.prog.objects.get(itemId));
				Builder.prog.addProgramLine(Builder.prog.objects.get(itemId).print());
				continue;
			}
			if(line.contains("+")) {
				String[] lineData = line.split("\\+");
				if(Builder.prog.objects.containsKey(lineData[0].trim()) && Builder.prog.objects.containsKey(lineData[1].trim())) {
					String obj1 = lineData[0].trim();
					String obj2 = lineData[1].trim();
					if(Builder.prog.objects.get(obj1) instanceof Addable) {
						((Addable) Builder.prog.objects.get(obj1)).add(Builder.prog.objects.get(obj2));
					}
				}
			}
		}
	}
}
