package Compiler.Integration;

import Compiler.Builder;
import Compiler.Object.Types.Storeable;
import Compiler.Object.Types.Type;

public enum Reg implements Storeable{
	EAX("eax"), EBX("ebx"), ECX("ecx"), EDX("edx"), ESI("esi"), EDI("edi"), EBP("ebp"), ESP("esp");
	
	private String name;
	private String data;
	private Reg(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void storeToReg(Type data) {
		this.data = data.getData();
		Builder.prog.addProgramLine("mov " + name + ", " + data.convertToReg());
	}
	
	public void loadFromReg(Type data) {
		Builder.prog.addProgramLine("mov " + data.convertFromReg() + ", " + name);
	}
	
	@Override
	public String getData() {
		return data;
	}

	@Override
	public void setData(String data) {
		this.data = data;		
	}
	
	public void push() {
		Stack.push(this);
	}
	
	public Reg pop() {
		Stack.pop(this);
		return this;
	}

	@Override
	public String convertToReg() {
		return name;
	}

	@Override
	public String convertFromReg() {
		return name;
	}

	@Override
	public boolean isLiteral() {
		return true;
	}
}
