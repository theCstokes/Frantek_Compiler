package Compiler.Object.Operators;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.function.Function;

import Compiler.Object.Types.Convertible;
import Compiler.Object.Types.Type;

public enum Operators implements Evaluatable {
	EQUALS(new Equals()), ADD(new Addable());
	
	public static final char OPEN_BRACKET = '(';
	public static final char CLOSE_BRACKET = ')';
	
	private Operator operator;
	private Operators(Operator op) {
		this.operator = op;
	}
	
	public String getID() {
		return operator.getID();
	}
	
	public int getStrength() {
		return operator.getStrength();
	}
		
	public static boolean isOp(char item) {
		return isOp(String.valueOf(item));
	}
	
	public static boolean isOp(String item) {
		for(Operators op : values()) {
			if(op.getID().equals(item)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isBracket(char item) {
		return isBracket(String.valueOf(item));
	}
	
	public static boolean isBracket(String item) {
		return (item.equals(OPEN_BRACKET) || item.equals(OPEN_BRACKET));
	}
	
	public static Operators identify(String c) {
		for(Operators op : values()) {
			if(op.getID().equals(c)) {
				return op;
			}
		}
		return null;
	}
	
	public void exicute() {
		
	}

	@Override
	public void evaluate(Convertible arg1, Convertible arg2) {
		operator.evaluate(arg1, arg2);		
	}

	@Override
	public String getAsmCall() {
		return operator.getAsmCall();
	}
	
	@Override
	public boolean isReversible() {
		return operator.isReversible();
	}
	
}
