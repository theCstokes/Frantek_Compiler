package Compiler.Object.Types;

import java.io.IOException;
import java.util.List;

import Compiler.Builder;
import Compiler.Integration.Reg;
import Compiler.Object.Operators.Operator;
import Compiler.Object.Operators.Operators;
import Compiler.Parser.CodeLine;
import Compiler.Parser.Equation;

public abstract class Type extends AbstractType {
	private final String ID;
	protected String name;
	protected boolean literal = false; 
	protected static List<Operator> avalibleOperators;
	
	public abstract void construct(CodeLine data);
	public abstract String getInit() throws IOException;
	public abstract List<String> print() throws IOException;
	
	public Type(String id, String data, boolean literal) {
		super(data);
		this.ID = id;
		this.literal = literal;		
		name = null;
	}
	
	public Type(String id, CodeLine data) {
		super();
		this.ID = id;
		System.out.println(data.getLine());
		System.out.println(Builder.prog.objects);
		
		name = data.getObjectName();
		System.out.println("name: " + name);
		construct(data);
	}
	
	public String getName() {
		return name;
	}
	
	public String getID() {
		return ID;
	}
	
	protected void init(Type type) {
		Builder.prog.objects.put(name, type);
		try {
			Builder.prog.addDataLine(getInit());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void assign(CodeLine line) {
		boolean hasEquation = line.check(item -> {
			for(Operators op : Operators.values()) {
				if(op.getID() == "=") {
					continue;
				}
				if(item.contains(op.getID())) {
					return true;
				}
			}
			return false;
		});
		
		if(hasEquation) {
			Equation eq = new Equation(line);
			eq.eval();
			Operators.EQUALS.evaluate(this, Reg.EAX);
		}
	}
	
	@Override
	public boolean isLiteral() {
		return literal;
	}
}
