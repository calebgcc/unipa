package jalgo.datastructures;

public class Jpriority<T>{

    class Node{
        public Node left;
        public Node center;
        public Node right;
        public Node parent;
        public int type; // 1:leaf 2:2-node 3:3-node
        public T llabel;
        public T rlable;
        public T data;
    }


    private Node root = null;


    public void push(T val){
        // if root is null
            // create a leaf node
        // if root is a leaf
            // create a root 2-node
        // else search for the right spot to insert val
        // create the node
        // call insert on the current node passing the new node
        // ! remember to reach the root and update all the label during the path 
    }

    public void insert(Node currentNode, Node newNode){
        // if currentNode is a 2-node
            // ad newNode in the right branch of currentNode
            // update the label of currentNode
            // return
        // else
            // divide currentNode in 2 2-node
            // call insert on the parent node of currentNode
            // if parent is null update the root
    }

}