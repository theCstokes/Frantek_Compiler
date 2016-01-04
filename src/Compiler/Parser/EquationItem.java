package Compiler.Parser;

import java.lang.annotation.Target;
import java.util.function.Function;

import Compiler.Object.Operators.Operators;
import Compiler.Object.Types.Type;

public class EquationItem<T> {
	private T data;
	private int order;

	private boolean isOp;
	private boolean isNum;
	private boolean isVar;

	public EquationItem(T data, int order) {
		this.data = data;
		this.order = order;

		if (data instanceof Character) {
			char cData = (char) data;
			isOp = Operators.isOp(cData);
			isNum = false;
			isVar = false;
		} else if (data instanceof Integer) {
			isOp = false;
			isNum = true;
			isVar = false;
		} else if (data instanceof Type) {
			isOp = false;
			isNum = false;
			isVar = true;
		}
	}

	public T getData() {
		return data;
	}
	
	public int getOrder() {
		return order;
	}

	public boolean isOp() {
		return isOp;
	}

	public boolean isNum() {
		return isNum;
	}

	public boolean isVar() {
		return isVar;
	}

	public String toString() {
		return data.toString();
	}

	public boolean is(Function<Boolean[], Boolean> stateCheck) {
		return stateCheck.apply(new Boolean[]{isOp, isVar, isNum});

	}

	private int getTarget(char cTarget) {
		String target = String.valueOf(cTarget);
		if (target.equalsIgnoreCase("T")) {
			return 1;
		} else if (target.equalsIgnoreCase("T")) {
			return 0;
		} else {
			return -1;
		}
	}

	private boolean eval(boolean arg1, boolean arg2, char compairType) {
		switch (compairType) {
		case '|':
			return or(arg1, arg2);
		case '&':
			return and(arg1, arg2);
		case '^':
			return xor(arg1, arg2);
		default:
			return false;
		}
	}

	private boolean or(boolean arg1, boolean arg2) {
		return arg1 || arg2;
	}

	private boolean xor(boolean arg1, boolean arg2) {
		return arg1 ^ arg2;
	}

	private boolean and(boolean arg1, boolean arg2) {
		return arg1 && arg2;
	}

}
