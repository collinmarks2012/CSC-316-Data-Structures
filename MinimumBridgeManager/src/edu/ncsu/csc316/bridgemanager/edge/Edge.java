package edu.ncsu.csc316.bridgemanager.edge;

public class Edge {
	
	private int vertex1;
	private int vertex2;
	private double cost;
	private Edge next;
	public int getVertex1() {
		return vertex1;
	}
	public void setVertex1(int vertex1) {
		this.vertex1 = vertex1;
	}
	public int getVertex2() {
		return vertex2;
	}
	public void setVertex2(int vertex2) {
		this.vertex2 = vertex2;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public Edge(int vertex1, int vertex2, double cost) {
		setVertex1(vertex1);
		setVertex2(vertex2);
		setCost(cost);
	}
	
	public Edge getNext() {
		return next;
	}
	public void setNext(Edge next) {
		this.next = next;
	}
	
	public class Node {

		Node next;
		Object data;

		public Node(Object data) {
		    this(data, null);
		}

		public Node(Object data, Node next) {
		    this.next = next;
		    this.data = data;
		}

		public Object getData() {
		    return this.data;
		}

		public void setData(Object data) {
		    this.data = data;
		}

		public Node getNext() {
		    return this.next;
		}

		public void setNext(Node nextNode) {
		    this.next = nextNode;
		}

		}
	

}
