package tree;

public class TreeNode <T extends Comparable<T>> implements Comparable<TreeNode<T>>{

	private T data;
	private TreeNode<T> left;
	private TreeNode<T> right;

	public TreeNode(T data) {
		this.data = data;
	}

	public TreeNode(T data, TreeNode<T> treeNode) {
		this.data = data;
		if(this.compareTo(treeNode) <= 0) {
			this.left = treeNode;
		} else {
			this.right = treeNode;
		}
	}

	public void setLeft(TreeNode<T> node) {
		left = node;
	}

	public void setRight(TreeNode<T> node) {
		right = node;
	}

	public void setData(T data) {
		this.data = data;
	}
	public T getData() {
		return data;
	}

	public TreeNode<T> getLeft() {
		return left;
	}

	public TreeNode<T> getRight() {
		return right;
	}

	public int compareTo(TreeNode<T> node) {
		return this.data.compareTo(node.getData());
	}

	public boolean isLeaf() {
		return (left == null && right == null);
	}
	public String toString() {
		return data.toString();
	}

}
