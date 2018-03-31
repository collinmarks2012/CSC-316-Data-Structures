package edu.ncsu.csc316.familytree.manager;

import java.io.File;
import java.io.FileNotFoundException;
import edu.ncsu.csc316.familytree.util.*;
import java.util.Scanner;

import edu.ncsu.csc316.familytree.person.Person;

public class FamilyTreeManager {
	private static String fileName;
	private static String[] pretrav;
	private static String[] posttrav;
	private static TreeNode tree;
	private static TreeNode temp; //for getrelationship 

	public static void main(String[] args) {
		System.out.println("Enter preorder and postorder traversal filenames:");
		Scanner scanner = new Scanner(System.in);
		fileName = scanner.nextLine();
		FamilyTreeManager ftm = new FamilyTreeManager(fileName);
		System.out.println("Level Order Traversal: ");
		List<Person> levelOrder = ftm.getLevelOrder();
		int stop = levelOrder.size();
		for (int i = 0; i < stop - 1; i++) {
			Person out = levelOrder.get(i);
			String Name = out.getName();
			System.out.print(Name + ", ");
		}
		Person out = levelOrder.get(stop - 1);
		String Name = out.getName();
		System.out.println(Name);
		System.out.println("Enter two names: ");
		scanner.close();
		scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		while (line != null) {
			temp = new TreeNode();
			temp = tree;
			Scanner words = new Scanner(line);
			String A = words.next();
			String B = words.next();
			System.out.println(getRelationship(A, B));
			line = scanner.nextLine();
			words.close();
		}
		scanner.close();
	}

	public FamilyTreeManager(String fileName) {
		if (fileName != null) {
			try {
				Scanner fS = new Scanner(new File(fileName), "utf-8");
				pretrav = fS.nextLine().split(", ");
				posttrav = fS.nextLine().split(", ");
				int size = pretrav.length; // initial size will be entire tree
											// inputted
				tree = buildTree(size, 0, 0);
				fS.close();
			} catch (FileNotFoundException e) {
				System.out.println("Cannot Find File! ");
				e.printStackTrace();
			}
		}
	}

	private static TreeNode buildTree(int size, int prestart, int poststart) {
		// 1. Determine the children (if any) of the root of the subtree to be
		// built,
		// from the part of the traversals passed to it — in other words, no
		// iterative
		// processing of the whole traversals in the beginning to determine
		// subtrees, sizes, etc.

		// The base case is when size=1. In this case, we know that the node is
		// a leaf.
		// All that is needed is to build a node and return the pointer to the
		// calling method,
		// and skip steps #2 and #3 below.
		// initialization of node
		TreeNode root = new TreeNode(new Person(pretrav[prestart]));
		if (size != 1) {
			prestart++;
		} else { // size == 1
			TreeNode leaf = new TreeNode(new Person(pretrav[prestart]));
			return leaf;
		}
		/*
		 * 2. Make a recursive call for each child (if any) with appropriate
		 * values for size, prestart, poststart that define the subtree rooted
		 * at the child within the original traversals — each recursive call
		 * handles the smaller traversals passed to it.
		 */
		int count = 0;
		int sizeOld = size;
		for (int i = 0;; i++) {
			// need to update my size variable somehow
			// also need to update poststart
			// the prestart parameter shoudld work here

			/*
			 * 3. Build a node with pointers to children returned by the
			 * recursive calls and the node label. Once the node is built, visit
			 * each child and set the parent pointer of the child to this node.
			 */
			size = findPost(pretrav[prestart]) - poststart + 1;
			TreeNode child = buildTree(size, prestart, poststart);
			prestart = prestart + size;
			count += size;
			root.addChildAt(i, child); // this addChild method will set the
										// parent for all the children
			poststart = poststart + size;
			if (count >= sizeOld - 1)
				break;
		}
		/*
		 * 4. Return a pointer to this node to the calling method (this node is
		 * the child of the node built by the calling method).
		 */
		poststart++; // to skip the poststart listing of the root
		return root;
	}


	private static int findPost(String string) {
		for (int i = 0; i < pretrav.length; i++) {
			if (string.equals(posttrav[i])) {
				return i;
			}
		}
		return 0;
	}

	public List<Person> getLevelOrder() {
		TreeNode p = tree;
		List<Person> output = new PersonList();
		LinkedQueue<TreeNode> queue = new LinkedQueue<TreeNode>();
		if (p == null) {
			return output;
		}
		queue.enqueue(p);
		while (!queue.isEmpty()) {
			TreeNode q = queue.dequeue();
			if (q != null && q.getPerson() != null) {
				output.add(q.getPerson());
				for (int i = 0; i < q.getNumberOfChildren(); i++) {
					TreeNode r = q.getChildAt(i);
					if (r != null) {
						queue.enqueue(r);
					}
				}
			} else {
				System.out.print("");
			}
		}
		return output;
	}

	public static String getRelationship(String nameA, String nameB) {
		TreeNode leastCommon = new TreeNode();
		temp = buildTree(pretrav.length, 0, 0);
		temp = find(new Person(nameA));
		while (temp != null) {
			temp.setMark(1);
			if (temp.getParent() != null) {
				temp = temp.getParent();
			} else {
				break;
			}			
		}
		temp = find(new Person(nameB));
		while (temp != null) {
			if (temp.getMark() == 1) {
				leastCommon = temp;
				break;
			} else {
				if (temp.getParent() != null) {
					temp = temp.getParent();
				} else {
					break;
				}	
			}
		}
		// return temp to position of first query node
		temp = find(new Person(nameA));
		int plus = 0;
		int minus = 0;
		plus = leastCommon.depth();
		minus = temp.depth();
		int pA = minus - plus;
		// need to put temp back at root of tree
		while (temp != null) {
			if (temp.getParent() != null) {
				temp = temp.getParent();
			} else {
				break;
			}
		}
		temp = find(new Person(nameB));
		minus = temp.depth();
		int pB = minus - plus;
		// will effectively reset the tree
		temp = find(new Person(nameA));
		while (temp != null) {
			temp.setMark(0);
			if (temp.getParent() != null) {
				temp = temp.getParent();
			} else {
				break;
			}	
		}
		if (pA == 0 && pB == 0) {
			return nameA + " is " + nameB;
		}
		if (pA == 0 && pB == 1) {
			return nameA + " is " + nameB + "'s parent";
		}
		if (pA == 0 && pB == 2) {
			return nameA + " is " + nameB + "'s grandparent";
		}
		if (pA == 0 && pB == 3) {
			return nameA + " is " + nameB + "'s great-grandparent";
		}
		if (pA == 0 && pB > 3) {
			String greats = "";
			for (int i = 0; i < pB - 2; i++) {
				greats = greats + "great-";
			}
			return nameA + " is " + nameB + "'s " + greats + "grandparent";
		}
		if (pA == 1 && pB == 0) {
			return nameA + " is " + nameB + "'s child";
		}
		if (pA == 2 && pB == 0) {
			return nameA + " is " + nameB + "'s grandchild";
		}
		if (pA > 3 && pB == 0) {
			String greats = "";
			for (int i = 0; i < pA - 2; i++) {
				greats = greats + "great-";
			}
			return nameA + " is " + nameB + "'s " + greats + "grandchild";
		}
		if (pA == 1 && pB == 1) {
			return nameA + " is " + nameB + "'s sibling";
		}
		if (pA == 1 && pB == 2) {
			return nameA + " is " + nameB + "'s aunt/uncle";
		}
		if (pA == 1 && pB >= 2) {
			String greats = "";
			for (int i = 0; i < pB - 2; i++) {
				greats = greats + "great-";
			}
			return nameA + " is " + nameB + "'s " + greats + "aunt/uncle";
		}
		if (pA == 2 && pB == 1) {
			return nameA + " is " + nameB + "'s niece/nephew";
		}
		if (pA >= 2 && pB == 1) {
			String greats = "";
			for (int i = 0; i < pB - 2; i++) {
				greats = greats + "great-";
			}
			return nameA + " is " + nameB + "'s " + greats + "neice/nephew";
		}
		if (pA >= 2 && pB >= 2) {
			int out = Math.min(pA, pB) -1;
			if (out == 1) {
				return nameA + " is " + nameB + "'s " + Integer.toString(Math.min(pA, pB) - 1) + "st cousin "
						+ Integer.toString(Math.abs(pA - pB)) + " times removed";
			} else if (out == 2) {
				return nameA + " is " + nameB + "'s " + Integer.toString(Math.min(pA, pB) - 1) + "nd cousin "
						+ Integer.toString(Math.abs(pA - pB)) + " times removed";
			} else if (out == 3) {
				return nameA + " is " + nameB + "'s " + Integer.toString(Math.min(pA, pB) - 1) + "rd cousin "
						+ Integer.toString(Math.abs(pA - pB)) + " times removed";
			} else {
				return nameA + " is " + nameB + "'s " + Integer.toString(Math.min(pA, pB) - 1) + "th cousin "
						+ Integer.toString(Math.abs(pA - pB)) + " times removed";
			}
			
		}
		return null;
	}

	public static TreeNode find(Person dataToFind) {
		TreeNode returnNode = null;
		if (temp != null) {
			returnNode = auxiliaryFind(temp, dataToFind);
		}
		return returnNode;
	}

	private static TreeNode auxiliaryFind(TreeNode node, Person dataToFind) {
		TreeNode returnNode = null;
		int i = 0;

		if (node.getPerson().getName().equals(dataToFind.getName())) {
			returnNode = node;
		}

		else if (node.hasChildren()) {
			i = 0;
			while (returnNode == null && i < node.getNumberOfChildren()) {
				returnNode = auxiliaryFind(node.getChildAt(i), dataToFind);
				i++;
			}
		}
		return returnNode;
	}
}
