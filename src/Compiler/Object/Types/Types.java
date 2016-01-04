package Compiler.Object.Types;

import java.lang.reflect.Field;
import java.util.function.Function;

import Compiler.Parser.CodeLine;


public enum Types {
	STRING(VString::new, VString.class, VString.ID), INT(VInteger::new, VInteger.class, VInteger.ID);
	
	
	private Function<CodeLine, Type> constructor;
	private Class<?> type;
	private String id;
	private <T extends Type> Types(Function<CodeLine, Type> constructor, Class<?> type, String id) {
		this.constructor = constructor;
		this.type = type;
		this.id = id;
	}
	
	public static Types isType(String id) {
		for(Types t : values()) {
			if(t.id.equals(id)) {
				return t;
			}
		}
		return null;
	}
	
	public Type construct(CodeLine data) {
		return this.constructor.apply(data);
	}
}
