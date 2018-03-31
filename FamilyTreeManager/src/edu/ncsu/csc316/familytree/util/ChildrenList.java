package edu.ncsu.csc316.familytree.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ChildrenList implements Iterable<TreeNode>, List<TreeNode>
{
   private Node<TreeNode> head;
   private int size;

 /**
   *  Constructs an empty list
   */
   public ChildrenList()
   {
      head = null;
      size = 0;
   }
 /**
   *  Returns true if the list is empty
   *
   */
   public boolean isEmpty()
   {
      return head == null;
   }
 /**
   *  Inserts a new node at the beginning of this list.
   *
   */
   private void addFirst(TreeNode item)
   {
      head = new Node<TreeNode>(item, head);
   }
 /**
   *  Returns the first element in the list.
   *
   */
   private TreeNode getFirst()
   {
      if(head == null) throw new NoSuchElementException();

      return head.data;
   }
 /**
   *  Removes the first element in the list.
   *
   */
   public TreeNode removeFirst()
   {
      TreeNode tmp = getFirst();
      head = head.next;
      return tmp;
   }
 /**
   *  Inserts a new node to the end of this list.
   *
   */
   public void addLast(TreeNode item)
   {
      if( head == null)
         addFirst(item);
      else
      {
         Node<TreeNode> tmp = head;
         while(tmp.next != null) tmp = tmp.next;

         tmp.next = new Node<TreeNode>(item, null);
      }
   }
 /**
   *  Returns the last element in the list.
   *
   */
   public TreeNode getLast()
   {
      if(head == null) throw new NoSuchElementException();

      Node<TreeNode> tmp = head;
      while(tmp.next != null) tmp = tmp.next;

      return tmp.data;
   }
 /**
   *  Removes all nodes from the list.
   *
   */
   public void clear()
   {
      head = null;
   }
 /**
   *  Returns true if this list contains the specified element.
   *
   */
   public boolean contains(TreeNode x)
   {
      for(TreeNode tmp : this)
         if(tmp.equals(x)) return true;

      return false;
   }
 /**
   *  Returns the data at the specified position in the list.
   *
   */
   public TreeNode getT(int pos)
   {
      if (head == null) throw new IndexOutOfBoundsException();

      Node<TreeNode> tmp = head;
      for (int k = 0; k < pos; k++) tmp = tmp.next;

      if( tmp == null) throw new IndexOutOfBoundsException();

      return tmp.data;
   }
 /**
   *  Returns a string representation
   *
   */
   public String toString()
   {
      StringBuffer result = new StringBuffer();
      for(Object x : this)
      	result.append(x + " ");

      return result.toString();
   }
 /**
   *  Inserts a new node after a node containing the key.
   *
   */
   public void insertAfter(TreeNode key, TreeNode toInsert)
   {
      Node<TreeNode> tmp = head;

      while(tmp != null && !tmp.data.equals(key)) tmp = tmp.next;

      if(tmp != null)
         tmp.next = new Node<TreeNode>(toInsert, tmp.next);
   }
 /**
   *  Inserts a new node before a node containing the key.
   *
   */
   public void insertBefore(TreeNode key, TreeNode toInsert)
   {
      if(head == null) return;

      if(head.data.equals(key))
      {
         addFirst(toInsert);
         return;
      }

      Node<TreeNode> prev = null;
      Node<TreeNode> cur = head;

      while(cur != null && !cur.data.equals(key))
      {
         prev = cur;
         cur = cur.next;
      }
      //insert between cur and prev
      if(cur != null)
         prev.next = new Node<TreeNode>(toInsert, cur);
   }
 /**
   *  Removes the first occurrence of the specified element in this list.
   *
   */
   public void remove(TreeNode key)
   {
      if(head == null)
         throw new RuntimeException("cannot delete");

      if( head.data.equals(key) )
      {
         head = head.next;
         return;
      }

      Node<TreeNode> cur  = head;
      Node<TreeNode> prev = null;

      while(cur != null && !cur.data.equals(key) )
      {
         prev = cur;
         cur = cur.next;
      }

      if(cur == null)
         throw new RuntimeException("cannot delete");

      //delete cur node
      prev.next = cur.next;
   }


 /*******************************************************
 *
 *  The Node class
 *
 ********************************************************/
   @SuppressWarnings("hiding")
private static class Node<TreeNode>
   {
      private TreeNode data;
      private Node<TreeNode> next;

      public Node(TreeNode data, Node<TreeNode> next)
      {
         this.data = data;
         this.next = next;
      }
   }

 /*******************************************************
 *
 *  The Iterator class
 *
 ********************************************************/

   public Iterator<TreeNode> iterator()
   {
      return new LinkedListIterator();
   }

   private class LinkedListIterator  implements Iterator<TreeNode>
   {
      private Node<TreeNode> nextNode;

      public LinkedListIterator()
      {
         nextNode = head;
      }

      public boolean hasNext()
      {
         return nextNode != null;
      }

      public TreeNode next()
      {
         if (!hasNext()) throw new NoSuchElementException();
         TreeNode res = nextNode.data;
         nextNode = nextNode.next;
         return res;
      }

      public void remove() { throw new UnsupportedOperationException(); }
   }

@Override
public TreeNode get(int index) {
	// CLAH
		return getT(index);
}
@Override
public void add(TreeNode value) {
		this.addLast(value);
		size++;
}
@Override
public int size() {
	return this.size;
}
}