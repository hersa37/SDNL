package graph;

public class Vertex<E> {

	private E label;
	private boolean visited;

	public Vertex(E label) {
		this.label = label;
		visited = false;
	}

	public E getLabel() {
		return label;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String toString() {
		return label.toString();
	}
}
