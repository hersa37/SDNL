package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

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
		for (T data : datas) {
			root = addRecursion(data, root);
		}
	}

	private TreeNode<T> addRecursion(T data, TreeNode<T> current) {
		if (current == null) {
			return new TreeNode<>(data);
		}
		if (current.getData().compareTo(data) >= 0) {
			current.setLeft(addRecursion(data, current.getLeft()));
		} else {
			current.setRight(addRecursion(data, current.getRight()));
		}
		return current;
	}

	public void print() {
		print(root, 0);
	}

	private void printRecursive(TreeNode<T> current, int depth) {
		if (current == null) {
			return;
		}
		for (int i = 0; i < depth; i++) {
			System.out.print("\t");
		}
		System.out.print(" " + current);
		System.out.print("\n");
		printRecursive(current.getLeft(), depth + 1);
		printRecursive(current.getRight(), depth + 1);
	}


	private void print(TreeNode<T> current, int depth) {
		if (current == null) {
			return;
		}
		for (int i = 0; i < depth; i++) {
			System.out.print("\t");
		}
		if (depth != 0) {
			System.out.print("\u2514\u2500");
		}
		System.out.println(current.getData());
		print(current.getLeft(), depth + 1);
		print(current.getRight(), depth + 1);
	}

	public TreeNode<T> getNode(T key) {
		return searchRecursive(root, key);
	}

	private TreeNode<T> searchRecursive(TreeNode<T> current, T key) {
		if (current == null || key.compareTo(current.getData()) == 0) {
			return current;
		}
		if (key.compareTo(current.getData()) < 0) {
			return searchRecursive(current.getLeft(), key);
		} else return searchRecursive(current.getRight(), key);
	}

	/**
	 * Prints tree according to the order of DFS, left-first
	 *
	 * @param current
	 */
	private void preOrder(TreeNode<T> current) {
		if (current != null) {
			System.out.print(current + " ");
			preOrder(current.getLeft());
			preOrder(current.getRight());
		}
	}


	private void inOrder(TreeNode<T> current) {
		if (current != null) {
			inOrder(current.getLeft());
			System.out.print(current + " ");
			inOrder(current.getRight());
		}
	}

	private void postOrder(TreeNode<T> current) {
		if (current != null) {
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
		if (temp == null) {
			return;
		}
		System.out.print(temp + " ");
		queue.add(temp.getLeft());
		queue.add(temp.getRight());
		levelOrder(queue);
	}

	/**
	 * Method to remove node from a tree
	 *
	 * @param key is the value being removed from tree. If there are multiple, only the first instance is returned
	 */
	public boolean remove(T key) {
		AtomicBoolean isRemoved = new AtomicBoolean(false);
		remove(root, key, isRemoved); //Start at root, as always
		return isRemoved.get();
	}

	/**
	 * Recursive method to remove node from a tree. Replaces isRemoved node with
	 *
	 * @param current subtree root
	 * @param key     of node being removed
	 * @return whether a node is removed or not
	 */
	private TreeNode<T> remove(TreeNode<T> current, T key, AtomicBoolean isRemoved) {
		if (current == null) { //If go beyond leaf, key is not found and nothing is isRemoved.
			return null;
		}
		if (key.compareTo(current.getData()) > 0) {
			current.setRight(remove(current.getRight(), key, isRemoved)); //Move to right subtree
		} else if (key.compareTo(current.getData()) < 0) {
			current.setLeft(remove(current.getLeft(), key, isRemoved)); //Move to left subtree
		} else { //If current == data
			isRemoved.set(true);
			if (current.isLeaf()) { //If node is leaf, then just erase current
				current = null;
			} else if (current.getRight() != null) { //Prioritize right subtree
				current.setData(getSuccessor(current).getData()); //Replace data with next smallest data
				current.setRight(remove(current.getRight(), current.getData(), isRemoved)); //Recursively remove successor
			} else {
				current.setData(getPredecessor(current).getData()); //Replace data with previous biggest data
				current.setLeft(remove(current.getLeft(), current.getData(), isRemoved)); //Recursively remove predecessor
			}
		}
		return current;
	}

	/**
	 * Finds the next smallest data from the right subtree
	 *
	 * @param currentRoot is subtree root
	 * @return data from left-most leaf subtree
	 */
	public TreeNode<T> getSuccessor(TreeNode<T> currentRoot) {
		currentRoot = currentRoot.getRight();
		if (currentRoot == null) {
			return null;
		}
		while (currentRoot.getLeft() != null) {
			currentRoot = currentRoot.getLeft();
		}
		return currentRoot;
	}

	/**
	 * Finds the previous biggest data from the left subtree
	 *
	 * @param currentRoot is subtree root
	 * @return data from right-most leaf of subtree
	 */
	private TreeNode<T> getPredecessor(TreeNode<T> currentRoot) {
		currentRoot = currentRoot.getLeft();
		if (currentRoot == null) {
			return null;
		}
		while (currentRoot.getRight() != null) {
			currentRoot = currentRoot.getRight();
		}
		return currentRoot;
	}

	/**
	 * Find parent of a node
	 *
	 * @param key is a child node
	 * @return the parent of child node
	 */
	public TreeNode<T> getParent(T key) {
		TreeNode<T> parent = null;
		TreeNode<T> current = root;
		while (current != null) {
			if (key.compareTo(current.getData()) == 0) {
				return parent;
			}
			if (key.compareTo(current.getData()) < 0) {
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
	 *
	 * @param parent  is the parent of the current node
	 * @param current is a child node being processed
	 * @param key     is the value of a child node that is being looked for
	 * @return the parent of the child node. Returns null if the child does not exist, or if child node is root
	 */
	private TreeNode<T> getParent(TreeNode<T> parent, TreeNode<T> current, T key) {
		if (current == null) {
			return null;
		}
		if (current.getData().compareTo(key) == 0) {
			return parent;
		}
		if (key.compareTo(current.getData()) < 0) {
			return getParent(current, current.getLeft(), key);
		} else {
			return getParent(current, current.getRight(), key);
		}
	}

	public boolean removeIterative(T key) {
		TreeNode<T> helperNode = getNode(key);
		if (helperNode == null) {
			return false;
		}
		if (helperNode.compareTo(root) == 0) {
			if (helperNode.isLeaf()) {
				root = null;
			} else if (helperNode.getRight() == null) {
				root = helperNode.getLeft();
			} else {
				root = helperNode.getRight();
			}
		} else {
			TreeNode<T> parent = getParent(key);
			if (key.compareTo(parent.getData()) < 0) {
				if (helperNode.isLeaf()) {
					parent.setLeft(null);
				} else if (helperNode.getRight() == null) {
					parent.setLeft(helperNode.getLeft());
				} else {
					parent.setLeft(helperNode.getRight());
				}
			} else {
				if (helperNode.isLeaf()) {
					parent.setRight(null);
				} else if (helperNode.getRight() == null) {
					parent.setRight(helperNode.getLeft());
				} else if (helperNode.getLeft() == null) {
					parent.setRight(helperNode.getRight());
				} else {
					TreeNode<T> predecessor = getPredecessor(helperNode);
					TreeNode<T> parentPredecessor = getParent(predecessor.getData());
					helperNode.setData(predecessor.getData());
					if (parentPredecessor.compareTo(helperNode) == 0) {
						parentPredecessor.setLeft(predecessor.getLeft());
					} else if (parentPredecessor.getRight().isLeaf()) {
						parentPredecessor.setRight(null);
					} else {
						parentPredecessor.setRight(predecessor.getLeft());
					}
				}
			}
		}
		return true;
	}

	public boolean removeLongIterative(T key) {
		TreeNode<T> bantu = getNode(key);
		if (bantu == null) {
			return false;
		}
		if (bantu.compareTo(root) == 0) {
			if (bantu.isLeaf()) {
				root = null;
			} else if (bantu.getRight() == null) {
				root = bantu.getLeft();
			} else if (bantu.getLeft() == null) {
				root = bantu.getRight();
			} else {
				TreeNode<T> predecessor = getPredecessor(bantu);
				System.out.println(predecessor);
				TreeNode<T> parentPredecessor = getParent(predecessor.getData());
				bantu.setData(predecessor.getData());
				if (parentPredecessor.compareTo(bantu) != 0) {
					if (predecessor.getLeft() == null) {
						parentPredecessor.setRight(predecessor.getLeft());
					} else {
						parentPredecessor.setRight(null);
					}
				}
			}
		} else {
			TreeNode<T> parent = getParent(key);
			if (key.compareTo(parent.getData()) < 0) {
				if (bantu.isLeaf()) {
					parent.setLeft(null);
				} else if (bantu.getRight() == null) {
					parent.setLeft(bantu.getLeft());
				} else if (bantu.getLeft() == null) {
					parent.setLeft(bantu.getRight());
				} else {
					TreeNode<T> predecessor = getPredecessor(bantu);
					TreeNode<T> parentPredecessor = getParent(predecessor.getData());
					bantu.setData(predecessor.getData());
					if(parentPredecessor.compareTo(bantu) != 0) {
						if(predecessor.getLeft() != null) {
							parentPredecessor.setRight(predecessor.getLeft());
						} else {
							parentPredecessor.setRight(null);
						}
					} else {
						bantu.setLeft(predecessor.getLeft());
					}
				}
			} else {
				if (bantu.isLeaf()) {
					parent.setRight(null);
				} else if (bantu.getRight() == null) {
					parent.setRight(bantu.getLeft());
				} else if (bantu.getLeft() == null) {
					parent.setRight(bantu.getRight());
				} else {
					TreeNode<T> predecessor = getPredecessor(bantu);
					TreeNode<T> parentPredecessor = getParent(predecessor.getData());
					bantu.setData(predecessor.getData());
					if (parentPredecessor.compareTo(bantu) != 0 ) {
						if (predecessor.getLeft() != null) {
							parentPredecessor.setRight(predecessor.getLeft());
						} else {
							parentPredecessor.setRight(null);
						}
					} else {
						bantu.setLeft(predecessor.getLeft());
					}
				}
			}
		}
		return true;
	}


}
