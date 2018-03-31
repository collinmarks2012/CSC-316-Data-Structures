package edu.ncsu.csc316.familytree.util;

import edu.ncsu.csc316.familytree.person.Person;

public class TreeNode {
	private TreeNode parent;
	private Person person;
	private ChildrenList children;
	private int mark;
	
	public TreeNode() {
		super();
		children = new ChildrenList();
		mark = 0;
		parent = null;
		person = null;
	}
	// will only be used for adding tree nodes to a child list
	public TreeNode(Person person) {
		this();
		setPerson(person);
		mark = 0;
	}
	
	public TreeNode getParent() {
		return this.parent;
	}
	
	public int getNumberOfNodes() {
		int numberOfNodes = 0;
		if (parent != null) {
			numberOfNodes = auxiliaryGetNumberOfNodes(parent) + 1;
		}
		return numberOfNodes;
	}
	
	private int auxiliaryGetNumberOfNodes(TreeNode node) {
		 int numberOfNodes = node.getNumberOfChildren();

		 	for (int i = 0; i < node.getNumberOfChildren(); i++) {
		 		numberOfNodes += auxiliaryGetNumberOfNodes(node.getChildAt(i));
		 	}

	        return numberOfNodes;
	}
	
	public TreeNode find(Person dataToFind) {
		TreeNode returnNode = null;

        if(parent != null) {
            returnNode = auxiliaryFind(parent, dataToFind);
        }

        return returnNode;
	}
	private TreeNode auxiliaryFind(TreeNode node, Person dataToFind) {
		TreeNode returnNode = null;
        int i = 0;

        if (node.getPerson().equals(dataToFind)) {
        	returnNode = node;
        }

        else if(node.hasChildren()) {
            i = 0;
            while(returnNode == null && i < node.getNumberOfChildren()) {
                returnNode = auxiliaryFind(node.getChildAt(i), dataToFind);
                i++;
            }
        }

        return returnNode;
	}
	
	public boolean isEmpty() {
		return (parent == null);
	}
	
	public List<TreeNode> getChildren() {
		return this.children;
	}
	
	public boolean hasChildren() {
		return (getNumberOfChildren() > 0);
	}
	
	public void setChildren(ChildrenList children) {
		this.children = children;
		for (int i = 0; i < children.size(); i++) {
			children.get(i).setParent(this);
		}
	}
	
	private void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public void addChildAt(int index, TreeNode child) {
		children.add(child);
		child.setParent(this);
	}
	
	public void removeChildren() {
		this.children = new ChildrenList();
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public int getNumberOfChildren() {
		return getChildren().size();
	}
	
	public TreeNode getChildAt(int index) {
		return children.get(index);
	}
	
	public Person getPerson() {
		return this.person;
	}
	
	
	public String toString() {
		return getPerson().toString();
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		if (mark == 0 || mark == 1) {
			this.mark = mark;
		} else {
			throw new IllegalArgumentException("Mark must be 0 or 1!");
		}
	}
	
	 /**
     * Returns the depth of this node in the tree; that is,
     * it returns the distance to the root.
     * @return The depth of this node.
     */
    public int depth() {
        if (parent == null) return 0;
        else return 1 + parent.depth();
    }
    
    /**
     * Tests whether this node is a leaf.
     * 
     * @return <code>true</code> if this node has no children.
     */
    public boolean isLeaf() {
        return children.isEmpty();
    }
    
	public void setRoot(TreeNode root) {
		this.parent = root;
	}
	
	
}
