package Compiler.Object.Types;

public interface Convertible {
	public String convertToReg();
	
	public String convertFromReg();
	
	public boolean isLiteral();
}
