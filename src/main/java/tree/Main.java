package tree;

public class Main {
	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<>();
		Integer[] integers = {42, 21, 38, 27, 71, 82, 55, 63, 6, 2, 40, 12,11,50,3,23,100,7};
		tree.add(integers);
		tree.print();
		tree.remove(6);
		tree.print();
//		System.out.println(tree.search(8));
//		System.out.println(tree.search(2));
	}
}
