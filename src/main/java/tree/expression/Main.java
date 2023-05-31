package tree.expression;

public class Main {

	public static void main(String[] args) {
		Tree expression = new Tree();

		expression.addExpressionInfix("(1+2*3)");
		System.out.println(expression.inOrder());
	}
}

