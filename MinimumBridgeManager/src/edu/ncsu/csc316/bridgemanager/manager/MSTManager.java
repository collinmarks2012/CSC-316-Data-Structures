package edu.ncsu.csc316.bridgemanager.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.xml.soap.Node;

import edu.ncsu.csc316.bridgemanager.edge.Edge;
import edu.ncsu.csc316.bridgemanager.util.ArrayBasedList;
import edu.ncsu.csc316.bridgemanager.util.List;

public class MSTManager {
	
	private static String inputFileName;

	public static void main(String[] args) {
		System.out.println("Enter input filename: ");
		Scanner scanner = new Scanner(System.in);
		inputFileName = scanner.nextLine();
		scanner.close();
		MSTManager mst = new MSTManager(); 
		// we read in the file to construct the edges and insert into heap
		List<Edge> listEdge = new ArrayBasedList<Edge>();
				try {
					Scanner fS = new Scanner(new File(inputFileName));
					String line = fS.nextLine();
					while (!line.equals("-1")) {
						Scanner lS = new Scanner(line);
						int v1 = lS.nextInt();
						int v2 = lS.nextInt();
						double cost = lS.nextDouble();
						lS.close();
						listEdge.add(new Edge(v1, v2, cost));
						line = fS.nextLine();
					}		
					
					
					// need to insert the edge into an adjacency list
					fS.close();
				} catch (FileNotFoundException e) {
					System.out.println("Cannot Find File! ");
					e.printStackTrace();
				}
				
				// V is the set of all vertices in the list
				ArrayBasedList<Integer> V = new ArrayBasedList<Integer>();
				for (int i = 0; i < listEdge.getSize(); i++) {
					int vert1 = listEdge.get(i).getVertex1();
					if (!containsVert(V, vert1)) {
						V.add(vert1);
					}
					int vert2 = listEdge.get(i).getVertex2();
					if (!containsVert(V, vert2)) {
						V.add(vert2);
					}
				}
				//System.out.println(getHeap(listEdge));
				//System.out.println(getMinimumSpanningBridges(listEdge));		
				System.out.println(getAdjacencyList(listEdge));
	}

	public MSTManager() {
		
	}

	private static List<Edge> upHeap(List<Edge> edges, int i) {
		// i is the position of the new element in the heap
		if (i > 0) { // we stop at the root
			if (edges.get((i-1)/2).getCost() > edges.get(i).getCost()) {
				edges.swap((i-1)/2, i);
				return upHeap(edges, (i-1)/2);
			}
		}
		return edges;
	}

	private static List<Edge> downHeap(List<Edge> heap, int m) {
		// i is m's smallest child, if one exists
		int i = 0;
		if ((2*m + 2) < heap.getSize()) { // both children exist
			if (heap.get(2*m+2).getCost() <= heap.get(2*m+1).getCost()) {
				i = 2*m + 2;
			} else {
				i = 2*m + 1;
			}			
		} else if ((2*m + 1) < heap.getSize()) { // only the left child exists
			i = 2*m + 1;
		}
		// at this stage, if i = 0 then the node has no children
		if (i > 0 && heap.get(m).getCost() > heap.get(i).getCost()) {
			heap.swap(m, i);
			return downHeap(heap, i);
		}
		return heap;
		
	}
	
	public static List<Edge> buildHeap(List<Edge> edges) {
		ArrayBasedList<Edge> heap = new ArrayBasedList<Edge>();
		for (int i = 0; i < edges.getSize(); i++) {
			if (i != edges.getSize() - 1) {
				edges.get(i).setNext(edges.get(i+1));
				heap.setItemAtEnd(edges.get(i));
				upHeap(heap, heap.getSize() - 1);
			} else { // handles the end of the edge list
				edges.get(i).setNext(null);
				heap.setItemAtEnd(edges.get(i));
				upHeap(heap, heap.getSize() - 1);
			}
		}
		return heap;
	}
	
	

	public static String getHeap(List<Edge> edges) {
		List<Edge> heap = buildHeap(edges);
		String entireOut = "";
		for (int i = 0; i < heap.getSize() - 1; i++) {
			Edge oo = heap.get(i);
			int v1 = oo.getVertex1();
			int v2 = oo.getVertex2();
			if (v1 > v2) { // we want smallest vertex to be first
				int temp = v1;
				v1 = v2; 
				v2 = temp;
			}
			String stringV1 = Integer.toString(v1);
			String stringV2 = Integer.toString(v2);
			stringV1 = "   " + stringV1;
			stringV2 = "    " + stringV2;
			entireOut = entireOut + stringV1 + stringV2 + "\n";
		}
			Edge oo = heap.get(heap.getSize() - 1);
			int v1 = oo.getVertex1();
			int v2 = oo.getVertex2();
			if (v1 > v2) { // we want smallest vertex to be first
				int temp = v1;
				v1 = v2; 
				v2 = temp;
			}
			String stringV1 = Integer.toString(v1);
			String stringV2 = Integer.toString(v2);
			stringV1 = "   " + stringV1;
			stringV2 = "    " + stringV2;
			entireOut = entireOut + stringV1 + stringV2;
			// remove the last new line char
		return entireOut;
	}
	
	private static boolean containsVert(ArrayBasedList<Integer> list, int v) {
		for (int i = 0; i < list.getSize(); i++) {
			if (v == list.get(i)) {
				return true;
			}
		}
		return false;
	}
	
	public static String getAdjacencyList(List<Edge> edges) {
		for (int i = 0; i < edges.getSize(); i++) {
			edges.get(i).setNext(edges.get(i+1));
		}
		edges.get(edges.getSize()-1).setNext(null);
		ArrayBasedList<Integer> V = new ArrayBasedList<Integer>();
		for (int i = 0; i < edges.getSize(); i++) {
			int vert1 = edges.get(i).getVertex1();
			if (!containsVert(V, vert1)) {
				V.add(vert1);
			}
			int vert2 = edges.get(i).getVertex2();
			if (!containsVert(V, vert2)) {
				V.add(vert2);
			}			
		}
		for (int i = 0; i < V.getSize() ; i++ ) {
			for (int j = i+1; j < V.getSize(); j++) {
				if (V.get(i) > V.get(j)) {
					V.swap(i, j);
				} 
			}
		}
		String output = "";
		for (int i = 0; i < V.getSize(); i++) {
			List<Integer> out = new ArrayBasedList<Integer>();
			Edge compare = edges.get(0);
			while (compare != null) {
				if (V.get(i) == compare.getVertex1()) {
					if (compare.getVertex2() != V.get(i)) {
						out.add(compare.getVertex2());
					}
				} else if (V.get(i) == compare.getVertex2()) {
					if (compare.getVertex1() != V.get(i)) {
						out.add(compare.getVertex1());
					}
				}
				compare = compare.getNext();
			}
			for (int l = 0; l < out.getSize() ; l++ ) {
				for (int j = l+1; j < out.getSize(); j++) {
					if (out.get(l) > out.get(j)) {
						out.swap(l, j);
					} 
				}
			}
			String vOut = "   " + Integer.toString(out.get(0));
			for (int o = 1; o < out.getSize(); o++) {
				vOut = vOut + "    " + Integer.toString(out.get(o));
			}
			output = output + vOut + "\n";				
		}
			
		return output.substring(0, output.length() - 1);
	}

	public static String getMinimumSpanningBridges(List<Edge> edges) {
		// Need to build up V the set of all vertices.
		// build vertex set V
		ArrayBasedList<Integer> V = new ArrayBasedList<Integer>();
		for (int i = 0; i < edges.getSize(); i++) {
			int vert1 = edges.get(i).getVertex1();
			if (!containsVert(V, vert1)) {
				V.add(vert1);
			}
			int vert2 = edges.get(i).getVertex2();
			if (!containsVert(V, vert2)) {
				V.add(vert2);
			}
		}
		List<Edge> E_t = new ArrayBasedList<Edge>();
		List<Edge> heap = buildHeap(edges);
		int components = V.getSize();
		
		// makeSet by setting all default values to -1 as per requirements
		ArrayBasedList<Integer> Up = new ArrayBasedList<Integer>(components);
		for (int i = 0; i < components; i++) {
			Up.add(i, -1);
		}
		while (components > 1) {
			// effectively deleteMin()
			Edge x = heap.get(0);
			heap.decrementSize();
			if (heap.getSize() == 0) {
				break;
			}
			heap.swap(0, heap.getSize());
			heap = downHeap(heap, 0);
			int U = find(Up, x.getVertex1());
			int V1 = find(Up, x.getVertex2());
			if (U != V1) {
				Up = union(Up, U, V1);
				E_t.add(x);
				components--;
			}			
		}
		for (int i = 0; i < E_t.getSize() - 1; i++) {
			Edge oo = E_t.get(i);
			int v1 = oo.getVertex1();
			int v2 = oo.getVertex2();
			if (v1 > v2) { // we want smallest vertex to be first
				oo.setVertex1(v2);
				oo.setVertex2(v1);
			}
		}
		for (int i = 0; i < E_t.getSize() ; i++ ) {
			for (int j = i+1; j < E_t.getSize(); j++) {
				Edge tmp;
				if (E_t.get(i).getVertex1() > E_t.get(j).getVertex1()) {
					E_t.swap(i, j);
				} else if (E_t.get(i).getVertex1() == E_t.get(j).getVertex1()) {
					if (E_t.get(i).getVertex2() > E_t.get(j).getVertex2()) {
						E_t.swap(i, j);
					}
				}
			}
		}
		String entireOut = "";
		for (int i = 0; i < E_t.getSize() - 1; i++) {
			Edge oo = E_t.get(i);
			int v1 = oo.getVertex1();
			int v2 = oo.getVertex2();
			if (v1 > v2) { // we want smallest vertex to be first
				int temp = v1;
				v1 = v2; 
				v2 = temp;
			}
			String stringV1 = "   " + Integer.toString(v1);
			String stringV2 = "    " + Integer.toString(v2);
			entireOut = entireOut + stringV1 + stringV2 + "\n";
		}
			Edge oo = E_t.get(E_t.getSize() - 1);
			int v1 = oo.getVertex1();
			int v2 = oo.getVertex2();
			if (v1 > v2) { // we want smallest vertex to be first
				int temp = v1;
				v1 = v2; 
				v2 = temp;
			}
			String stringV1 = "   " + Integer.toString(v1);
			String stringV2 = "    " + Integer.toString(v2);
			entireOut = entireOut + stringV1 + stringV2;
		return entireOut;
	}
	
	private static ArrayBasedList<Integer> union(ArrayBasedList<Integer> up, int u, int v1) {
		int A = up.get(u); // for 1st vertex
		int B = up.get(v1); // for 2nd vertex
		if (A <= B) { // A has more children than B
			up.set(u, A+B);
			up.set(v1, u);
		} else {
			up.set(v1, A+B);
			up.set(u, v1);
		}
		return up;
	}

	private static int find(ArrayBasedList<Integer> up, int vertex2) {
		int comp = up.get(vertex2);
		int i = 0;
		int temp = vertex2;
		while (comp >= 0) {
			temp = comp;
			comp = up.get(comp);
		}
		return temp;
	}
}
