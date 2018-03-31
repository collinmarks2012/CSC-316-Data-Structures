import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import Tree.tree_TraversalStrategy;

public class tree_ex {
    public static void main(String[] args) {

        Tree tree = new Tree();

        
        tree.addtree_Node("H");
        tree.addtree_Node("J");
        tree.addtree_Node("B");
        
        tree.addtree_Node("D");
        tree.addtree_Node("G");
        tree.addtree_Node("M");
        
        tree.addtree_Node("C");
        
        
        tree.treedisplay("H");

        System.out.println("\n*****  ITERATION *****");

        Iterator<tree_Node> tree_tree_depthIterator = tree.iterator("H");

        while (tree_tree_depthIterator.hasNext()) {
            tree_Node tree_Node = tree_tree_depthIterator.next();
            System.out.println(tree_Node.tree_gettree_identifier());
        }

        System.out.println("\n****-FIRST ITERATION *****");

        Iterator<tree_Node> breadthIterator = tree.iterator("H", tree_TraversalStrategy.tree_BREADTH_FIRST);

        while (breadthIterator.hasNext()) {
            tree_Node tree_Node = breadthIterator.next();
            System.out.println(tree_Node.tree_gettree_identifier());
        }
    }
}
class tree_Node {

    private String tree_identifier;
    private ArrayList<String> tree_children;

    
    public tree_Node(String tree_identifier) {
        this.tree_identifier = tree_identifier;
        tree_children = new ArrayList<String>();
    }

    
    public String tree_gettree_identifier() {
        return tree_identifier;
    }

    public ArrayList<String> gettree_children() {
        return tree_children;
    }

    public void tree_addChild(String tree_identifier) {
        tree_children.add(tree_identifier);
    }
}


 class tree_BreadthFirstTreeIterator implements Iterator<tree_Node> {

    private static final int tree_ROOT = 0;

    private LinkedList<tree_Node> list;
    private HashMap<Integer, ArrayList<String>> tree_ tree_ tree_ROOT;

    public tree_BreadthFirstTreeIterator(HashMap<String, tree_Node> tree, String tree_identifier) {
        list = new LinkedList<tree_Node>();
        tree_ tree_ tree_ROOT = new HashMap<Integer, ArrayList<String>>();

        if (tree.containsKey(tree_identifier)) {
            this.buildList(tree, tree_identifier, tree_ROOT);

            for (Map.Entry<Integer, ArrayList<String>> entry : tree_ tree_ tree_ROOT.entrySet()) {
                for (String child : entry.getValue()) {
                    list.add(tree.get(child));
                }
            }
        }
    }

    private void buildList(HashMap<String, tree_Node> tree, String tree_identifier, int level) {
        if (level == tree_ROOT) {
            list.add(tree.get(tree_identifier));
        }

        ArrayList<String> tree_children = tree.get(tree_identifier).gettree_children();

        if (!tree_ tree_ tree_ROOT.containsKey(level)) {
            tree_ tree_ tree_ROOT.put(level, new ArrayList<String>());
        }
        for (String child : tree_children) {
            tree_ tree_ tree_ROOT.get(level).add(child);

            // Recursive call
            this.buildList(tree, child, level + 1);
        }
    }

    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }

    @Override
    public tree_Node next() {
        return list.poll();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
public classtree_ tree_depthFirstTreeIterator implements Iterator<tree_Node> {
    private LinkedList<tree_Node> list;

    publictree_ tree_depthFirstTreeIterator(HashMap<String, tree_Node> tree, String tree_identifier) {
        list = new LinkedList<tree_Node>();

        if (tree.containsKey(tree_identifier)) {
            this.buildList(tree, tree_identifier);
        }
    }

    private void buildList(HashMap<String, tree_Node> tree, String tree_identifier) {
        list.add(tree.get(tree_identifier));
        ArrayList<String> tree_children = tree.get(tree_identifier).gettree_children();
        for (String child : tree_children) {

            // Recursive call
            this.buildList(tree, child);
        }
    }

    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }

    @Override
    public tree_Node next() {
        return list.poll();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

 class Tree {

    private final static int tree_ROOT = 0;

    private HashMap<String, tree_Node> tree_Nodes;
    private tree_TraversalStrategy tree_TraversalStrategy;

    
    public Tree() {
        this(tree_TraversalStrategy.tree_tree_depth_FIRST);
    }

    public Tree(tree_TraversalStrategy tree_TraversalStrategy) {
        this.tree_Nodes = new HashMap<String, tree_Node>();
        this.tree_TraversalStrategy = tree_TraversalStrategy;
    }

    
    public HashMap<String, tree_Node> gettree_Nodes() {
        return tree_Nodes;
    }

    public tree_TraversalStrategy gettree_TraversalStrategy() {
        return tree_TraversalStrategy;
    }

    public void settree_TraversalStrategy(tree_TraversalStrategy tree_TraversalStrategy) {
        this.tree_TraversalStrategy = tree_TraversalStrategy;
    }

   
    public  tree_Node addtree_Node(String tree_identifier) {
        return this.addtree_Node(tree_identifier, null);
    }

    public tree_Node addtree_Node(String tree_identifier, String parent) {
        tree_Node tree_Node = new tree_Node(tree_identifier);
        tree_Nodes.put(tree_identifier, tree_Node);

        if (parent != null) {
            tree_Nodes.get(parent).tree_addChild(tree_identifier);
        }

        return tree_Node;
    }

    public void treedisplay(String tree_identifier) {
        this.treedisplay(tree_identifier, tree_ROOT);
    }

    public void treedisplay(String tree_identifier, int tree_depth) {
        ArrayList<String> tree_children = tree_Nodes.get(tree_identifier).gettree_children();

        if (tree_depth == tree_ROOT) {
            System.out.println(tree_Nodes.get(tree_identifier).tree_gettree_identifier());
        } else {
            String tree_tabs = String.format("%0" + tree_depth + "d", 0).replace("0", "    "); // 4 spaces
            System.out.println(tree_tabs + tree_Nodes.get(tree_identifier).tree_gettree_identifier());
        }
        tree_depth++;
        for (String child : tree_children) {

            
            this.treedisplay(child, tree_depth);
        }
    }

    public Iterator<tree_Node> iterator(String tree_identifier) {
        return this.iterator(tree_identifier, tree_TraversalStrategy);
    }

    public Iterator<tree_Node> iterator(String tree_identifier, tree_TraversalStrategy tree_TraversalStrategy) {
        return tree_TraversalStrategy == tree_TraversalStrategy.tree_BREADTH_FIRST ?
                new tree_BreadthFirstTreeIterator(tree_Nodes, tree_identifier) :
                new tree_depthFirstTreeIterator(tree_Nodes, tree_identifier);
    }
    

public enum tree_TraversalStrategy {
    tree_tree_depth_FIRST,
    tree_BREADTH_FIRST
}

}
