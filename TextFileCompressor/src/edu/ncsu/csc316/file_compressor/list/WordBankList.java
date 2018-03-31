package edu.ncsu.csc316.file_compressor.list;
public class WordBankList {
	Node head;
	private int size;

	/**
	 * Constructs an empty list
	 */
	public WordBankList() {
		head = null;
		size = 0;
	}
	public String get(int index) {
	    Node p = head;
	    int k = 0;
	    while (p!=null && k < index) {
	    	p = p.getNext();
	    	k++;
	    }
	    if (p != null) {
	    	return p.getData();
	    } else {
	    	return null;
	    }
	}
	
	
	public void addToFront(String data) {
	    Node temp = new Node(data);
	    temp.setNext(head);
	    this.head = temp;
	    this.size++;
	}
	
	public int remove(String d) {
	    Node tmpNode = head;
	    Node prevNode = null;
	    int deletedANode = -1;

	    if (head == null) {
	        return deletedANode;
	    }
	    int index = 0;
	    while (tmpNode != null) {
	    	index++;
	        if (tmpNode.data.equals(d)) {
	            if (tmpNode == head) {
	                head = head.next;
	            }
	            else {
	                prevNode.next = tmpNode.next;
	            }
	         deletedANode = index;
	         addToFront(d);
	         break;
	         }
	        if (index > size()) {
	        	deletedANode = -1; //Its not in the wordBank
	        }
	        
	         prevNode = tmpNode;
	         tmpNode = tmpNode.next;
	    }
	    return deletedANode;
	}
	
	public int size() {
	    return this.size;
	}
	
	public class Node {

		Node next;
		String data;

		public Node(String data) {
		    this(data, null);
		}

		public Node(String data, Node next) {
		    this.next = next;
		    this.data = data;
		}

		public String getData() {
		    return this.data;
		}

		public void setData(String data) {
		    this.data = data;
		}

		public Node getNext() {
		    return this.next;
		}

		public void setNext(Node nextNode) {
		    this.next = nextNode;
		}

		}

	public Object getIndex() {
		// TODO Auto-generated method stub
		return null;
	}
}