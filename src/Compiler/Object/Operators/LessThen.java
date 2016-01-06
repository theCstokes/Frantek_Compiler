package Compiler.Object.Operators;

import Compiler.Builder;
import Compiler.Object.Types.IConvertible;

public class LessThen extends Operator {
	
	public LessThen() {
		super("=", Integer.MAX_VALUE, true, "cmp", "lt");
	}
	
	@Override
	public void evaluate(IConvertible arg1, IConvertible arg2) {
		StringBuilder sb = new StringBuilder();
		sb.append(getAsmCalls().get(0));
		sb.append(arg1.convertFromReg());
		sb.append(ARG_SPLITTER);
		sb.append(arg2.convertToReg());
		sb.append(System.lineSeparator());
		sb.append(getAsmCalls().get(1));
		Builder.prog.addProgramLine(sb.toString());				
	}
	
//	private String returnFalse() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("mov ");
//		sb
//	}
//	
//	private String returnTrue() {
//		
//	}

}
