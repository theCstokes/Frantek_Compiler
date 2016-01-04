package Compiler.Integration;

import Compiler.Builder;
import Compiler.Object.Types.Convertible;
import Compiler.Object.Types.Type;
import Compiler.Parser.Equation.SplitObject;

public class Stack implements Convertible {
	
	private static int lookBack = 0;
	
	public static void push(Reg reg) {
		Builder.prog.addProgramLine("push " + reg.getName());
	}
	
	public static void push(Type data) {
		Builder.prog.addProgramLine("push " + data.convertToReg());
	}
	
	public static void pop(Reg reg) {
		Builder.prog.addProgramLine("pop " + reg.getName());
	}
	
//	public static void pop(Type data) {
//		
//	}
	
	public static void setLookBack(int lookBack) {
		Stack.lookBack = lookBack;
	}
	
	public static void addLookBack(int lookBack) {
		Stack.lookBack += lookBack;
	}

	@Override
	public String convertToReg() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(Reg.ESP.name());
		if(lookBack != 0) {
			sb.append(" + ");
			sb.append(lookBack);
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public String convertFromReg() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(Reg.ESP.name());
		if(lookBack != 0) {
			sb.append(" + ");
			sb.append(lookBack);
		}
		sb.append("]");
		return sb.toString();
	}

	@Override
	public boolean isLiteral() {
		return false;
	}
	
	
}
