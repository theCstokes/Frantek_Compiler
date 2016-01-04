package Compiler.Object.Types;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Compiler.Object.Operators.Addable;
import Compiler.Object.Operators.Equals;
import Compiler.Object.Operators.Operator;
import Compiler.Parser.CodeLine;

public class VInteger extends Type {
	
public static final String ID;	
	
	static {
		ID = "int";
//		avalibleOperators = new ArrayList<Operator>() {
//			private static final long serialVersionUID = 4154642723517066304L;
//			{
//				add(new Equals());
//			}
//		};
	}
	
	public VInteger(String data, boolean literal) {
		super(ID, data, literal);
	}

	public VInteger(CodeLine data) {
		super(ID, data);
		init(this);
		System.out.println(ID + " int constructor");
	}

	@Override
	public void construct(CodeLine line) {
		setData(line.getValue(data -> {
			String dataLine = data.getLine();
			return dataLine.substring(dataLine.indexOf("=") + 1).replace("\"", "").replace(";", "").trim();
		}));		
	}

	@Override
	public String getInit() throws IOException {
		String line = name +": dd " + getData();
		return line;
	}

	@Override
	public List<String> print() throws IOException {
		System.out.println("P");
		List<String> lines = new ArrayList<>();
		lines.add("mov eax, dword [" + name +"]" );
		lines.add("call print_int");
		lines.add("call print_nl");
		return lines;
	}

	@Override
	public String convertToReg() {
		return "dword [" + name +"]";
	}
	
	@Override
	public String convertFromReg() {
		if(isLiteral()) {
			return "dword " + getData();
		} else {
			return "[" + name + "]";
		}
	}

}
