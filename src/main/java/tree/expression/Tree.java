package tree.expression;

import tree.TreeNode;

import java.util.Stack;

public class Tree {

	private TreeNode<Character> root;

	public Tree() {

	}

	public Tree(Character rootData) {
		root = new TreeNode<>(rootData);
	}

	public void addExpressionInfix(String expression) {
		Stack<TreeNode<Character>> operand = new Stack<>();
		Stack<TreeNode<Character>> operator = new Stack<>();


		for (int i = 0; i < expression.length(); i++) {
			char character = expression.charAt(i);

			if (character == '(') {
				operator.push(new TreeNode<>(character));
				continue;
			}

			if (Character.isDigit(character)) {
				operand.push(new TreeNode<>(character));
				continue;
			}

			if (character == ')') {
				TreeNode<Character> childNode = operator.pop();
				while(!(childNode.getData() == '(')) {
					childNode.setRight(operand.pop());
					childNode.setLeft(operand.pop());
					operand.push(childNode);
					childNode = operator.pop();
				}
				continue;
			}

			operator.push(new TreeNode<>(character));
		}

		root = operand.pop();
	}

	public StringBuilder inOrder() {
		StringBuilder stringBuilder = new StringBuilder();
		inOrder(root, stringBuilder);
		return stringBuilder;
	}

	private void inOrder(TreeNode<Character> current, StringBuilder stringBuilder) {
		if (current == null) {
			return;
		}

		char character = current.getData();

		if (Character.isDigit(character)) {
			stringBuilder.append(character);
		} else {
			stringBuilder.append('(');
			inOrder(current.getLeft(), stringBuilder);
			stringBuilder.append(character);
			inOrder(current.getRight(), stringBuilder);
			stringBuilder.append(')');
		}
	}

}

