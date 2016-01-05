package Compiler.Object.Operators;

import Compiler.Builder;
import Compiler.Object.Types.Convertible;

public class LessThen extends Operator {
	
	public LessThen() {
		super("=", Integer.MAX_VALUE, true, "cmp", "lt");
	}
	
	@Override
	public void evaluate(Convertible arg1, Convertible arg2) {
		StringBuilder sb = new StringBuilder();
		sb.append(getAsmCall());
		sb.append(arg1.convertFromReg());
		sb.append(ARG_SPLITTER);
		sb.append(arg2.convertToReg());
		Builder.prog.addProgramLine(sb.toString());				
	}

}
