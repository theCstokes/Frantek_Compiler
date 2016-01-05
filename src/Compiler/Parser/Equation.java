package Compiler.Parser;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AbstractDocument.LeafElement;
import javax.swing.text.DefaultEditorKit.CutAction;

import Compiler.Builder;
import Compiler.Integration.Reg;
import Compiler.Integration.Stack;
import Compiler.Object.Operators.Evaluatable;
import Compiler.Object.Operators.Operator;
import Compiler.Object.Operators.Operators;
import Compiler.Object.Types.Convertible;
import Compiler.Object.Types.Type;
import Compiler.Object.Types.VInteger;

public class Equation {
	List<EquationItem<?>> data;

	public Equation(CodeLine line) {
		data = new ArrayList<>();
		init(line.getAssignmentValue());
	}
	
	public Equation(List<EquationItem<?>> data) {
		this.data = new ArrayList<>(data);
	}
	
	public EquationItem<?> get(int index) {
		return data.get(index);
	}

	public void init(String value) {
		StringBuilder varBuilder = new StringBuilder();
		int order = 0;
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			char lastChar = value.charAt(i <= 0 ? 0 : i - 1);
			if (c == '-') {
				pushVarData(varBuilder, order);
				if (i == 0 || Operators.isOp(lastChar)) {
					if ((i +1) < value.length() && Operators.OPEN_BRACKET == value.charAt(i + 1)) {
						data.add(new EquationItem<VInteger>(new VInteger("-1", true), order));
						data.add(new EquationItem<Character>(Character.valueOf('*'), order));
					} else {
						varBuilder.append(c);
					}
				}

			}
			if (Operators.isOp(c)) {
				pushVarData(varBuilder, order);					
				data.add(new EquationItem<Character>(Character.valueOf(c), order));
				continue;
			}
			if (Character.isDigit(c) || c == '.' || Character.isAlphabetic(c)) {
				varBuilder.append(c);
				continue;
			}
			if (Operators.OPEN_BRACKET == c) {
				pushVarData(varBuilder, order);
				++order;
				continue;
			}
			if (Operators.CLOSE_BRACKET == c) {
				pushVarData(varBuilder, order);
				--order;
				continue;
			}
			if (Operators.LESS_THEN == c) {
				pushVarData(varBuilder, order);
				continue;
			}
			if (Operators.GREATER_THEN == c) {
				pushVarData(varBuilder, order);
				continue;
			}
		}
		pushVarData(varBuilder, order);
	}

	public void pushVarData(StringBuilder varBuilder, int order) {
		String varData = varBuilder.toString();
		if (varData != null && !varData.isEmpty()) {
			try {
				data.add(new EquationItem<Integer>(Integer.valueOf(varData), order));
			} catch (NumberFormatException e) {
				data.add(new EquationItem<Type>(Builder.prog.objects.get(varData), order));
			}
			varBuilder.delete(0, varBuilder.length());
		}
	}
	
	public int parseDataModel() {
		int maxOrder = 0;
		int splitPos = 0;
		Operators maxType = Operators.EQUALS;
		for(int i = 1; i < data.size(); i++) {
			if(data.get(i).isOp() && data.get(i).getOrder() >= maxOrder) {
				Operators currentType = Operators.identify(data.get(i).toString());
				if(currentType.getStrength() < maxType.getStrength()) {
					maxType = currentType;
					maxOrder = data.get(i).getOrder();
					splitPos = i;
				}
			}
		}
		return splitPos;
	}
	
	public void eval() {
		SplitObject splitData = split();
		boolean collapsedLeft = false;
		boolean collapsedRight = false;
		Convertible arg1 = null;
		Convertible arg2 = null;
		
		if(!splitData.left.isEvaluateable()) {
			collapsedLeft = true;
			splitData.left.eval();
		}
		
		if(!splitData.right.isEvaluateable()) {
			if(collapsedLeft){
				Reg.EAX.push();
			}
			collapsedRight = true;
			splitData.right.eval();
		}
		
		if(collapsedLeft) {
			if(collapsedRight) {
				System.out.println("L");
				arg1 = new Stack();
			} else {
				arg1 = Reg.EAX;
			}
		} else {
			if(collapsedRight) {
				if(splitData.op.isReversible()) {
					arg1 = Reg.EAX;
					arg2 = Builder.prog.objects.get(((Type) splitData.left.get(0).getData()).getName());
				} else {
					Reg.EAX.push();
					arg1 = Operator.move(Builder.prog.objects.get(((Type) splitData.left.get(0).getData()).getName()));
					arg2 = new Stack();
				}
			} else {
				arg1 = Operator.move(Builder.prog.objects.get(((Type) splitData.left.get(0).getData()).getName()));
			}
		}
		
		if(arg2 == null) {
			if(collapsedRight) {
				System.out.println("R");
				arg2 = Reg.EAX;
			} else {
				arg2 = Builder.prog.objects.get(((Type) splitData.right.get(0).getData()).getName());
			}
		}
		
		splitData.op.evaluate(arg1, arg2);
	}
	
	public SplitObject split() {
		return new SplitObject(this);
	}
	
	public boolean isEvaluateable() {
		if(data.size() == 1) {
			return data.get(0).is(args -> !args[0] && (args[1] || args[2]));
		} else {
			return false;
		}
	}
	
	public class SplitObject {
		private Equation left;
		private Equation right;
		private Operators op;
		
		public SplitObject(Equation equation) {
			int splitPoint = equation.parseDataModel();
			System.out.println("Split " + equation.data +" at " + splitPoint);
			this.left = new Equation(equation.data.subList(0, splitPoint));
			this.right = new Equation(equation.data.subList(splitPoint + 1, equation.data.size()));
			this.op = Operators.identify(equation.data.subList(splitPoint, splitPoint + 1).get(0).toString());
		}
		
		public Equation getLeft() {
			return left;
		}
		
		public Equation getRight() {
			return right;
		}
		
		public Operators getOp() {
			return op;
		}
	}

}
