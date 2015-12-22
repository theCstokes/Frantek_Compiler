package Compiler.Object.Types;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.StandardConstants;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import Compiler.Object.Operators.Addable;
import Compiler.Object.Operators.Equals;
import Compiler.Object.Operators.Equatable;
import Compiler.Object.Operators.Operator;

public class VString extends Type implements Equatable, Addable{
	public static final String ID;	
	
	static {
		ID = "String";
		avalibleOperators = new ArrayList<Operator>() {
			private static final long serialVersionUID = 4154642723517066304L;
			{
				add(new Equals());
			}
		};
	}
	
	public VString(String data) {
		super(ID);
		parseTypeConstructor(data);
	}
	
	public String getInit() throws IOException {
		String line = name +": db \"" + data + "\",10,0";
		return line;
	}
	
	public List<String> print() throws IOException {
		List<String> lines = new ArrayList<>();
		lines.add("mov eax, dword " + name );
		lines.add("call print_string");
		lines.add("call print_nl");
		return lines;
	}

	@Override
	public void construct(String data) {
		this.data = data.replace("\"", "").replace(";", "");
	}

	@Override
	public boolean equals(Equatable e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(Type a) {
		this.data += a.data;		
	}
	
}
