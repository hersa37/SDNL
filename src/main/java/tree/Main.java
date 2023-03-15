package tree;

public class Main {
	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<>();
		Integer[] integers = {42, 21, 38, 27, 71, 82, 55, 63, 6, 2, 40, 12};
		tree.add(integers);
		tree.print();
		System.out.println("Remove 12");
		tree.remove(12);
		tree.print();
		System.out.println("Remove 27");
		tree.remove(27);
		tree.print();
		System.out.println("Remove 6");
		tree.removeIterative(6);
		tree.print();
		System.out.println("Remove 55");
		tree.remove(55);
		tree.print();
//		System.out.println(tree.search(8));
//		System.out.println(tree.search(2));
	}
}
