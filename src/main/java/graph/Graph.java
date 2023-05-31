package graph;

import java.util.*;

public class Graph<E> {

	public final int MAX_VERTICES = 10;
	public final int MAX_WEIGHT = 500;
	private int[][] adjacencyMatrix;
	private Vertex<E>[] vertexList;
	private int vertexCount;


	public Graph() {
		adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];
		vertexList = new Vertex[MAX_VERTICES];
		vertexCount = 0;
		setDefaultValues();
	}

	private void setDefaultValues(){
		for (int i = 0; i < MAX_VERTICES; i++) {
			for (int j = 0; j < MAX_VERTICES; j++) {
				if (i != j) {
					adjacencyMatrix[i][j] = 501;
				} else {
					adjacencyMatrix[i][j] = 0;
				}
			}

		}
	}

	public int vertexIndex(E vertex) {
		for (int i = 0; i < vertexCount; i++) {
			if (vertexList[i].getLabel() == vertex) {
				return i;
			}
		}
		return -1;
	}



	public void addVertex(E vertex) {
		if (vertexCount == MAX_VERTICES){
			return;
		}
		if (contains(vertex)){
			return;
		}

		Vertex<E> newVertex = new Vertex<>(vertex);
		vertexList[vertexCount++] = newVertex;
	}

	public void addVertices(E[] vertices) {
		for (E vertex : vertices) {
			addVertex(vertex);
		}
	}

	public void addEdge(int vert1, int vert2, int weight) {
		adjacencyMatrix[vert1][vert2] = weight;
		adjacencyMatrix[vert2][vert1] = weight;
	}

	public void addEdge(E vert1, E vert2, int weight) {
		addEdge(vertexIndex(vert1), vertexIndex(vert2), weight);
	}

	public void print() {
		StringBuilder stringBuilder = new StringBuilder(String.format("%4s", ""));
		for (int i = 0; i < vertexCount; i++) {
			stringBuilder.append(String.format("%4s",vertexList[i]));
		}
		for (int i = 0; i < vertexCount; i++) {
			stringBuilder.append(String.format("\n%4s", vertexList[i]));
			for (int j = 0; j < vertexCount; j++) {
				stringBuilder.append(String.format("%4d", adjacencyMatrix[i][j]));
			}
		}
		System.out.println(stringBuilder);
	}

	private boolean contains(E vertex) {
		for (int i = 0; i < vertexCount; i++) {
			if (vertex == vertexList[i].getLabel()) {
				return true;
			}
		}
		return false;
	}

	private ArrayList<Vertex<E>> getNeighbors(int vertexIndex){
		ArrayList<Vertex<E>> neighbors = new ArrayList<>(vertexCount);
		for (int i = 0; i < vertexCount; i++) {
			if (adjacencyMatrix[vertexIndex][i] != -1 && adjacencyMatrix[vertexIndex][i] != 0) {
				neighbors.add(vertexList[i]);
			}
		}
		return neighbors;
	}

	public void dfs(int seed) {
		Stack<Vertex<E>> stack = new Stack<>();
		stack.push(vertexList[seed]);
		ArrayList<Vertex<E>> neighbors;
		while(!stack.isEmpty()) {
			Vertex<E> vertex = stack.pop();
			if(vertex.isVisited()) {
				continue;
			}
			System.out.print(vertex + " ");
			neighbors = getNeighbors(vertexIndex(vertex.getLabel()));
			for (Vertex<E> neighbor : neighbors) {
				if(!neighbor.isVisited()) {
					stack.push(neighbor);
				}
			}
			vertex.setVisited(true);
		}
		setNotVisited();
	}

	private void bfs(int seed) {
		Queue<Vertex<E>> queue = new Queue<>();
		queue.add(vertexList[seed]);
		ArrayList<Vertex<E>> neighbors;
		while(!queue.isEmpty()) {
			Vertex<E> vertex = queue.dequeue();
			if(vertex.isVisited()) {
				continue;
			}
			System.out.print(vertex + " ");
			neighbors = getNeighbors(vertexIndex(vertex.getLabel()));
			for (Vertex<E> neighbor : neighbors) {
				if(neighbor == null) {
					break;
				}

				if(!neighbor.isVisited()) {
					queue.add(neighbor);
				}
			}
			neighbors.clear();
			vertex.setVisited(true);
		}
		setNotVisited();
	}

	public void bfs(Vertex<E> vertex) {
		bfs(vertexIndex(vertex.getLabel()));
	}

	public Vertex<E> getVertex(E label) {
		for (int i = 0; i < vertexCount; i++) {
			if(vertexList[i].getLabel() == label) {
				return vertexList[i];
			}
		}
		return null;
	}

	private void setNotVisited() {
		for (int i = 0; i < vertexCount; i++) {
			vertexList[i].setVisited(false);
		}
	}

//	public ArrayList<Edge> getPrimEdges(int seed) {
//		final int MAX_WEIGHT = 20;
//		ArrayList<Edge> primEdges = new ArrayList<>();
//		ArrayList<Integer> primVertices = new ArrayList<>();
//		primVertices.add(seed);
//		while (primVertices.size() < vertexCount) {
//			tempMinWeight = MAX_WEIGHT;
//			tempMinIndexVertexI = -1;
//			tempMinIndexVertexJ = -1;
//			for (int i = 0; i < primVertices.size(); i++) {
//				for (int j = 0; j < vertexCount; j++) {
//					if()
//				}
//			}
//		}
//	}

// Use dijkstra to find distance between 2 nodes
	public List<Vertex<E>> dijkstra(E start, E end) {
		LinkedList<Vertex<E>> path = new LinkedList<>();
		Vertex<E> startVertex = getVertex(start);
		Vertex<E> endVertex = getVertex(end);
		if (startVertex == null || endVertex == null) {
			return path;
		}
		HashMap<Vertex<E>,Integer> distance = new HashMap<>();
		HashMap<Vertex<E>,Vertex<E>> previous = new HashMap<>();
		PriorityQueue<Vertex<E>> queue = new PriorityQueue<>(vertexCount, Comparator.comparingInt(distance::get));
		for (int i = 0; i < vertexCount; i++) {
			distance.put(vertexList[i], Integer.MAX_VALUE);
			previous.put(vertexList[i], null);
		}
		distance.put(startVertex, 0);
		queue.add(startVertex);
		while (!queue.isEmpty()) {
			Vertex<E> vertex = queue.poll();
			if (vertex == endVertex) {
				while (previous.get(vertex) != null) {
					path.addFirst(vertex);
					vertex = previous.get(vertex);
				}
				path.addFirst(vertex);
				break;
			}
			ArrayList<Vertex<E>> neighbors = getNeighbors(vertexIndex(vertex.getLabel()));
			for (Vertex<E> neighbor : neighbors) {
				int alt = distance.get(vertex) + adjacencyMatrix[vertexIndex(vertex.getLabel())][vertexIndex(neighbor.getLabel())];
				if (alt < distance.get(neighbor)) {
					distance.put(neighbor, alt);
					previous.put(neighbor, vertex);
					queue.add(neighbor);
				}
			}
		}
		return path;
	}

}
