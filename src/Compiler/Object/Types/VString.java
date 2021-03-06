package Compiler.Object.Types;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Compiler.Integration.Reg;

import Compiler.Parser.CodeLine;

public class VString extends Type {
	public static final String ID;	
	
	static {
		ID = "String";
//		avalibleOperators = new ArrayList<Operator>() {
//			private static final long serialVersionUID = 4154642723517066304L;
//			{
//				add(new Equals());
//			}
//		};
	}
	
	public VString(String data, boolean literal) {
		super(ID, data, literal);
	}
	
	public VString(CodeLine data) {
		super(ID, data);
		init(this);
	}
	
	public String getInit() throws IOException {
		String line = name +": db \"" + getData() + "\",10,0";
		return line;
	}
	
	public List<String> print() throws IOException {
		List<String> lines = new ArrayList<>();
//		lines.add("mov eax, dword " + name );
		Reg.EAX.storeToReg(this);
		lines.add("call print_string");
		lines.add("call print_nl");
		return lines;
	}

	@Override
	public void construct(CodeLine line) {
		setData(line.getValue(data -> {
			String dataLine = data.getLine();
			return dataLine.substring(dataLine.indexOf("=") + 1).replace("\"", "").replace(";", "").trim();
		}));
	}

	@Override
	public String convertToReg() {
		if(isLiteral()) {
			return "dword " + getData();
		} else {
			return "dword " + name;
		}
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
