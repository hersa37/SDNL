package tree.avlTree;

public class BalancedTreeNode {
	public static void main(String[] args) {

	}

	//make a method that accepts a string and returns the sum of the ascii values of the characters in the string
	//for example, if the string is "abc" then the method should return 294
	//hint: you can use the charAt method of the String class to get the character at a specific index
	//hint: you can use the cast operator to convert a char to an int
	//hint: you can use the + operator to add two ints together
	public static int sumAscii(String s) {
		int sum = 0;
		for(int i = 0; i < s.length(); i++) {
			sum += (int) s.charAt(i);
		}
		return sum;
	}
}



