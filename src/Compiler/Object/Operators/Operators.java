package Compiler.Object.Operators;

import java.util.concurrent.Callable;
import java.util.function.Function;

public enum Operators {
	EQUALS(Equals::new);
	Callable<Operator> constructor;
	private Operators(Callable<Operator> constructor) {
		this.constructor = constructor;
	}
	
}
