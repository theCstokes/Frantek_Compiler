package Compiler.Object.Operators;

import java.util.List;

import Compiler.Object.Types.IConvertible;
import Compiler.Object.Types.IStoreable;
import Compiler.Object.Types.Type;

public interface IEvaluatable {
	public void evaluate(IConvertible arg1, IConvertible arg2);
	
	public String getID();
	
	public int getStrength();
	
	public boolean isReversible();
	
	public String getAsmCall();
	
	public List<String> getAsmCalls();
}
