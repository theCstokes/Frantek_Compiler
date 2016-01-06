package Compiler.Object.Types;

import Compiler.Builder;
import Compiler.Integration.Reg;

public abstract class AbstractType implements IStoreable {
	private String data;
	
	public AbstractType() {
		data = "";
	}
	
	public AbstractType(String data) {
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
}
