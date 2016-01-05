package Compiler.Object.Operators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Compiler.Builder;
import Compiler.Integration.Reg;
import Compiler.Object.Types.Convertible;

public abstract class Operator implements Evaluatable {
	
	public static final String ARG_SPLITTER = ", ";
	
	private final String ID;
	private final int strength;
	private final List<String> asmCalls; 
	private final boolean reversable;
	
	public Operator(Evaluatable evaluatable) {
		this.ID = evaluatable.getID();
		this.strength = evaluatable.getStrength();
		this.asmCalls = new ArrayList<>(evaluatable.getAsmCalls());
		this.reversable = evaluatable.isReversible();
	}
	
	public Operator(String ID, int strength, boolean reversable, String... asmCalls) {
		this.ID = ID;
		this.strength = strength;
		this.asmCalls = new ArrayList<>(Arrays.asList(asmCalls));
		this.reversable = reversable;
	}
	
	public String getID() {
		return ID;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public String getAsmCall() {
		StringBuffer out = new StringBuffer();
		for(String call : asmCalls) {
			out.append(call);
			out.append(System.lineSeparator()); 
		}
		out.append(" ");
		return out.toString();
	}
	
	public List<String> getAsmCalls() {
		return asmCalls;
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
