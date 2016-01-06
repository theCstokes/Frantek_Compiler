package Compiler.Object.Types;

public interface IConvertible {
	
	public String convertToReg();
	
	public String convertFromReg();
	
	public boolean isLiteral();
}
