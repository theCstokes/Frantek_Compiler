package Compiler.Object.Operators;

import Compiler.Builder;
import Compiler.Object.Types.IConvertible;
import Compiler.Object.Types.Type;

public class Equals extends Operator {
	
	public Equals() {
		super("=", Integer.MAX_VALUE, true, "mov");
	}
	
	@Override
	public void evaluate(IConvertible arg1, IConvertible arg2) {
		StringBuilder sb = new StringBuilder();
		sb.append(getAsmCall());
		sb.append(arg1.convertFromReg());
		sb.append(ARG_SPLITTER);
		sb.append(arg2.convertToReg());
		Builder.prog.addProgramLine(sb.toString());				
	}

}
