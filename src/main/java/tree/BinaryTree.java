package tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T extends Comparable<T>> {

	private TreeNode<T> root;

	public BinaryTree() {

	}
	public BinaryTree(T rootData) {
		this.root = new TreeNode<>(rootData);
	}

	public void setRoot(TreeNode<T> root) {
		this.root = root;
	}

	public TreeNode<T> getRoot() {
		return root;
	}

	public void add(T data) {
		root = addRecursion(data, root);
	}

	public void add(T[] datas) {
		for(T data : datas) {
			root = addRecursion(data, root);
		}
	}
	private TreeNode<T> addRecursion(T data, TreeNode<T> current) {
		if(current == null) {
			return new TreeNode<>(data);
		}
		if(current.getData().compareTo(data) >= 0) {
			current.setLeft(addRecursion(data,current.getLeft()));
		} else {
			current.setRight(addRecursion(data, current.getRight()));
		}
		return current;
	}

	public void print(){
		print(root, 0);
	}

	private void printRecursive(TreeNode<T> current,int depth) {
		if(current == null) {
			return;
		}
		for(int i = 0; i < depth; i++) {
			System.out.print("\t");
		}
		System.out.print(" " + current);
		System.out.print("\n");
		printRecursive(current.getLeft(), depth + 1);
		printRecursive(current.getRight(), depth + 1);
	}


	private void print(TreeNode<T> current, int depth) {
		if(current == null) {
			return;
		}
		for (int i = 0; i < depth; i++) {
			System.out.print("\t");
		}
		if(depth != 0) {
			System.out.print("\u2514\u2500");
		}
		System.out.println(current.getData());
		print(current.getLeft(), depth + 1);
		print(current.getRight(), depth +1);
	}

	public TreeNode<T> search(T key) {
		return searchRecursive(root, key);
	}
	private TreeNode<T> searchRecursive(TreeNode<T> current, T key) {
		if(current == null || key.compareTo(current.getData()) == 0) {
			return current;
		}
		if(key.compareTo(current.getData()) < 0) {
			return searchRecursive(current.getLeft(), key);
		}
		else return searchRecursive(current.getRight(), key);
	}

	/**
	 * Prints tree according to the order of DFS, left-first
	 * @param current
	 */
	private void preOrder(TreeNode<T> current) {
		if(current != null) {
			System.out.print(current + " ");
			preOrder(current.getLeft());
			preOrder(current.getRight());
		}
	}


	private void inOrder(TreeNode<T> current) {
		if(current != null) {
			inOrder(current.getLeft());
			System.out.print(current + " ");
			inOrder(current.getRight());
		}
	}

	private void postOrder(TreeNode<T> current) {
		if(current != null) {
			postOrder(current.getLeft());
			postOrder(current.getRight());
			System.out.print(current + " ");
		}
	}

	/**
	 * Print a tree per level.
	 */
	public void levelOrder() {
		Queue<TreeNode<T>> queue = new LinkedList<>();
		queue.add(root);
		levelOrder(queue);

	}


	private void levelOrder(Queue<TreeNode<T>> queue) {
		TreeNode<T> temp = queue.remove();
		if(temp == null) {
			return;
		}
		System.out.print(temp + " ");
		queue.add(temp.getLeft());
		queue.add(temp.getRight());
		levelOrder(queue);
	}

	/**
	 * Method to remove node from a tree
	 * @param key is the value being removed from tree. If there are multiple, only the first instance is returned
	 */
	public void remove(T key) {
		remove(root, key); //Start at root, as always
	}

	/**
	 * Recursive method to remove node from a tree. Replaces removed node with
	 * @param current
	 * @param key
	 * @return
	 */
	private TreeNode<T> remove(TreeNode<T> current, T key) {
		if(current == null) { //If go beyond leaf, key is not found and nothing is removed.
			return null;
		}
		if(key.compareTo(current.getData()) > 0) {
			current.setRight(remove(current.getRight(), key)); //Move to right subtree
		} else if(key.compareTo(current.getData()) < 0) {
			current.setLeft(remove(current.getLeft(), key)); //Move to left subtree
		} else{ //If current == data
			if(current.isLeaf()) { //If node is leaf, then just erase current
				current = null;
			} else if(current.getRight() != null) { //Prioritize right subtree
				current.setData(successor(current)); //Replace data with next smallest data
				current.setRight(remove(current.getRight(), current.getData())); //Recursively remove successor
			} else {
				current.setData(predecessor(current)); //Replace data with previous biggest data
				current.setLeft(remove(current.getLeft(), current.getData())); //Recursively remove predecessor
			}
		}
		return current;
	}

	/**
	 * Finds the next smallest data from the right subtree
	 * @param currentRoot is subtree root
	 * @return data from left-most leaf subtree
	 */
	private T successor(TreeNode<T> currentRoot) {
		currentRoot = currentRoot.getRight();
		while(currentRoot.getLeft() != null) {
			currentRoot = currentRoot.getLeft();
		}
		return currentRoot.getData();
	}

	/**
	 * Finds the previous biggest data from the left subtree
	 * @param currentRoot is subtree root
	 * @return data from right-most leaf of subtree
	 */
	private T predecessor(TreeNode<T> currentRoot) {
		currentRoot = currentRoot.getLeft();
		while(currentRoot.getRight() != null) {
			currentRoot = currentRoot.getRight();
		}
		return currentRoot.getData();
	}

	/**
	 * Find parent of a node
	 * @param key is a child node
	 * @return the parent of child node
	 */
	public TreeNode<T> getParent(T key) {
		TreeNode<T> parent = null;
		TreeNode<T> current = root;
		while(current != null) {
			if(key.compareTo(current.getData()) == 0) {
				return parent;
			}
			if(key.compareTo(current.getData()) < 0) {
				parent = current;
				current = current.getLeft();
			} else {
				parent = current;
				current = current.getRight();
			}
		}
		return null;
	}

	/**
	 * Recursive method to find parent of a node
	 * @param parent is the parent of the current node
	 * @param current is a child node being processed
	 * @param key is the value of a child node that is being looked for
	 * @return the parent of the child node. Returns null if the child does not exist, or if child node is root
	 */
	private TreeNode<T> getParent(TreeNode<T> parent, TreeNode<T> current, T key) {
		if(current == null) {
			return null;
		}
		if(current.getData().compareTo(key) == 0) {
			return parent;
		}
		if(key.compareTo(current.getData()) < 0) {
			return getParent(current, current.getLeft(), key);
		} else {
			return getParent(current, current.getRight(), key);
		}
	}



}
