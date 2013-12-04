import java.util.Vector;

/**
 * @author Toviah
 * A classic binary search tree. Delete is incomplete.
 */
public class BinarySearchTree {
	Node root = null;
	private class Node {

		private int value;
		private Node left;
		private Node right;
		private Node parent;
		

		public int getValue() {
			return value;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public Node getParent() {
			return parent;
		}

		public Node(int value) {
			this.value = value;
		}

		public void setParent(Node node) {
			parent = node;
		}
	}

	

	public void insert(int i) {
		Node node = new Node(i);
		if (root == null) {
			root = node;
		} else {
			insert(node, root);
		}
	}

	private void insert(Node node, Node tempRoot) {
		int value = node.getValue();
		int tempRootVal = tempRoot.getValue();
		if (value > tempRootVal) {
			if (tempRoot.getRight() == null) {
				tempRoot.setRight(node);
			} else {
				insert(node, tempRoot.getRight());
			}
		} else if (value < tempRootVal) {
			if (tempRoot.getLeft() == null) {
				tempRoot.setLeft(node);
			} else {
				insert(node, tempRoot.getLeft());
			}
		}

	}
	
	public boolean search(int i){
		return search(i, root);
	}
	public boolean search(int i, Node tempRoot){
		boolean found = false;
		int val = tempRoot.getValue();
		if (i == val){
			found = true;
		}
		else if (i <= val){
			return search(i, tempRoot.getLeft());
		}
		else if (i <= val){
			return search(i, tempRoot.getRight());
		}
		return found;		
	}
	
	public void inOrderTraverse(){
		inOrderTraverse(root);
	}
	
	private void inOrderTraverse(Node n){
		if(n == null) return;
		if (n.getLeft()!=null){
			inOrderTraverse(n.left);
		}
		System.out.println(n.getValue());
		if (n.getRight()!=null){
			inOrderTraverse(n.right);
		}
	}
	
	public void delete(Node n){
		Node parent = n.getParent();
		if(n.getLeft() == null && n.getRight()==null){ //leaf case
			if(n==parent.getLeft()){
				parent.setLeft(null);
			}
			if(n==parent.getRight()){
				parent.setRight(null);
			}
		}
		if(n.getLeft() == null && n.getRight()!=null){ //child on right
			if(n==parent.getLeft()){
				parent.setLeft(n.getRight());
			}
			if(n==parent.getRight()){
				parent.setRight(n.getRight());
			}
		}
		if(n.getRight() == null && n.getLeft()!=null){ //child on left
			if(n==parent.getLeft()){
				parent.setLeft(n.getLeft());
			}
			if(n==parent.getRight()){
				parent.setRight(n.getLeft());
			}
		} 
		else{ //if n has 2 children, TODO 
			
		}
	}
	private void preOrderTraverse(){
		preOrderTraverse(root);
	}
	
	private void preOrderTraverse(Node node) {
		System.out.println(node.getValue());
		if(node.getLeft()!=null){
			preOrderTraverse(node.getLeft());
		}
		if(node.getRight()!=null){
			preOrderTraverse(node.getRight());
		}
	}
	
	private void postOrderTraverse(){
		postOrderTraverse(root);
	}
	
	private void postOrderTraverse(Node node) {
		
			
		if(node.getLeft()!=null){
			postOrderTraverse(node.getLeft());
		}
		if(node.getRight()!=null){
			postOrderTraverse(node.getRight());
		}
		System.out.println(node.getValue());

	}
	
	public Node getNode(){
		return root;	
	}

	public static void main(String[] args){
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(5);
		bst.insert(7);
		bst.insert(4);
		bst.insert(6);
		bst.inOrderTraverse();
		bst.preOrderTraverse();
		
	}
}
