package graph;

import java.sql.SQLOutput;

public class Main {
	public static void main(String[] args) {
		System.out.println("First graph");
		Graph<Character> graph1 = new Graph<>();
		Character[] characters = {'A', 'B', 'C', 'D', 'E', 'F'};
		graph1.addVertices(characters);
		graph1.addEdge(0, 1, 33);
		graph1.addEdge(0, 2, 33);
		graph1.addEdge(0,4,33);
		graph1.addEdge(1,4, 33);
		graph1.addEdge(1,2, 33);
		graph1.addEdge(2, 4, 33);
		graph1.addEdge(2, 3, 33);
		graph1.addEdge(2, 5, 33);
		graph1.addEdge(3, 5, 33);

		graph1.print();

		System.out.println("DFS");
		graph1.dfs(0);

		System.out.println("\nBFS");
		graph1.bfs(graph1.getVertex('A'));

		System.out.println("\n\n\tSecond graph");
		Graph<Character> graph2 = new Graph<>();
		Character[] characters2 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
		graph2.addVertices(characters2);
		graph2.addEdge(0,1, 10);
		graph2.addEdge(0, 6, 10);
		graph2.addEdge(0, 8, 10);
		graph2.addEdge(1, 2, 10);
		graph2.addEdge(1, 5, 10);
		graph2.addEdge(2, 5, 10);
		graph2.addEdge(2, 3, 10);
		graph2.addEdge(5, 4, 10);
		graph2.addEdge(4, 6, 10);
		graph2.addEdge(4, 3, 10);
		graph2.addEdge(3, 7, 10);
		graph2.print();

//		System.out.println("DFS");
//		graph2.dfs(6);
//
//		System.out.println("\nBFS");
//		graph2.bfs(graph2.getVertex('A'));
//		System.out.println(graph2.dijkstra('A','F'));


		Graph<String> jaringan = new Graph<>();
		String[] daftarJaringan = {"PC1", "PC2", "PC3", "PC4", "PC5", "PC6", "PC7", "PC8", "PC9", "PC10"};
		jaringan.addVertices(daftarJaringan);
		jaringan.addEdge("PC1", "PC7", 2);
		jaringan.addEdge("PC1", "PC3", 10);
		jaringan.addEdge("PC3", "PC2", 5);
		jaringan.addEdge("PC3", "PC4", 11);
		jaringan.addEdge("PC7", "PC5", 15);
		jaringan.addEdge("PC4", "PC5", 8);
		jaringan.addEdge("PC4", "PC8", 15);
		jaringan.addEdge("PC5", "PC6", 6);
		jaringan.addEdge("PC6", "PC10", 7);
		jaringan.addEdge("PC5", "PC9", 3);

		jaringan.print();

		System.out.println("\nShortest path:");
		System.out.println(jaringan.dijkstra("PC10", "PC2"));
	}
}
