package Compiler.Object.Operators;

import Compiler.Builder;
import Compiler.Integration.Reg;
import Compiler.Object.Types.Convertible;

public abstract class Operator implements Evaluatable {
	
	public static final String ARG_SPLITTER = ", ";
	
	private final String ID;
	private final int strength;
	private final String asmCall; 
	private final boolean reversable;
	
	public Operator(Evaluatable evaluatable) {
		this.ID = getID();
		this.strength = getStrength();
		this.asmCall = getAsmCall();
		this.reversable = isReversible();
	}
	
	public Operator(String ID, int strength, String asmCall, boolean reversable) {
		this.ID = ID;
		this.strength = strength;
		this.asmCall = asmCall;
		this.reversable = reversable;
	}
	
	public String getID() {
		return ID;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public String getAsmCall() {
		return asmCall + " ";
	}
	
	public boolean isReversible() {
		return reversable;
	}
	
	public static Convertible move(Convertible arg) {
		return move(arg, Reg.EAX);
	}
	
	public static Convertible move(Convertible arg, Reg dest) {
		StringBuilder sb = new StringBuilder();
		sb.append("mov ");
		sb.append(dest.getName());
		sb.append(ARG_SPLITTER);
		sb.append(arg.convertToReg());
		Builder.prog.addProgramLine(sb.toString());	
		return dest;
	}
}
