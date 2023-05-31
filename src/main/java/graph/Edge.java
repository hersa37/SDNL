package graph;

public class Edge {

	private int vertexA;
	private int vertexB;
	private int weight;

	public Edge(int vertexA, int vertexB, int weight) {
		this.vertexA = vertexA;
		this.vertexB = vertexB;
		this.weight = weight;
	}

	public int getVertexA() {
		return vertexA;
	}

	public void setVertexA(int vertexA) {
		this.vertexA = vertexA;
	}

	public int getVertexB() {
		return vertexB;
	}

	public void setVertexB(int vertexB) {
		this.vertexB = vertexB;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
