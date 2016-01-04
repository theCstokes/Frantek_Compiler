package Compiler.Object.Operators;

import Compiler.Object.Types.Convertible;
import Compiler.Object.Types.Storeable;
import Compiler.Object.Types.Type;

public interface Evaluatable {
	public void evaluate(Convertible arg1, Convertible arg2);
	
	public String getID();
	
	public int getStrength();
	
	public boolean isReversible();
	
	public String getAsmCall();
}
