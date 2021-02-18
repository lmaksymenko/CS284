package HW5;
import java.util.Random;
import java.util.Stack;


public class Treap<E extends Comparable<E>> {
	//Data fields
	private Random priorityGenerator;
	private Node<E> root;
	
	public static class Node<E>{
		// Data fields
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;
		
		// Constructors
		public Node(E data, int priority) {
			super();
			this.data = data;
			this.priority = priority;
		}
		public Node(E data) {
			super();
			this.data = data;
		}
		
		
		public Node<E> rotateRight(){
			
			Node<E> templeft = this.left;//save left data of root
			this.left = templeft.right;//change left of root to new val
			templeft.right = this;//set new root to point to prev root
			return templeft;//return new subtree root
			
		}
		
		public Node<E> rotateLeft(){
			Node<E> tempright = this.right;
			this.right = tempright.left;
			tempright.left = this;
			return tempright;
		}
		
        public Node<E> moveDown() {
            if (left != null && right != null) {
            
                if(left.priority < right.priority) {
                	return rotateRight();
                }else {
                	return rotateLeft();
                }
                
            }else if (left == null) {
                return rotateLeft();
            
            }else if (right == null) {
                return rotateRight();
            
            }else {
                return this;
            }
        }
		
		public String toString() {
			return "[Key=" + data + ", Priority=" + priority + "]";
		}
		
	}
	//Constructors
	public Treap() {
		this.priorityGenerator = new Random();
	}
	
	public Treap(long seed) {
		this.priorityGenerator = new Random(seed);
	}
	
	//methods
	
	public boolean add(E key) {
		
		int priority = priorityGenerator.nextInt();
		return add(key, priority);
		
	}
	
	public boolean add(E key, int priority) {
		
		if (root == null) {
			root = new Node<E>(key, priority);
			return true;
		}
		
		if(find(key)) {
			return false;
		}
		
		Stack<Node<E>> path = new Stack<Node<E>>();
		Node<E> current = root;
				
		while(current != null) {//find the position that the node should be in
			path.push(current);
			
			if((int)key > (int)current.data) {//only works on ints
				current = current.right;
			}else {
				current = current.left;
			}
		}
		
		//System.out.println("Inserting at Path:" + path);
		
		current = path.peek();//set current to parent of inserted node
		Node<E> newNode = new Node<E>(key, priority);
		path.push(newNode);//create and add new node to stack
		
		if((int)key > (int)current.data) {//insert new node
			current.right = newNode;
		}else {
			current.left = newNode;
		}
		path.pop();
		
		reheap(path);
		
		return true;
	}
	
	public void reheap(Stack<Node<E>> path){

		Node<E> current;
		Node<E> newParent;
		//System.out.println("Reheaping path: " + path);
		
		while(path.size() != 0) {
			current = path.pop();

			if(current.left != null && current.priority < current.left.priority) {
				newParent = current.rotateRight();
				
				if(current == root) {
					root = newParent;
					
				}else {
				
					if(path.peek().left == current) {//if prev parent node was on the left of its parent node
						path.peek().left = newParent;//complete rotation by fixing the oldParent parent node pointer
					}else {//else its on the right
						path.peek().right = newParent;
					}
				}
				
			}else{ 
				
				if (current.right != null && current.priority < current.right.priority) {
					newParent = current.rotateLeft();
				
					if(current == root) {
						root = newParent;
					
					}else {
						if(path.peek().left == current) {//if prev parent node was on the left of its parent node
							path.peek().left = newParent;//complete rotation by fixing the oldParent parent node pointer
						}else {//else its on the right
							path.peek().right = newParent;
						}
					}
				}
			}
		}
	}
	
	
	public boolean delete(E key) {
		if(!find(key)){
			return false;
		}
		
		//navigate to node object
		Node<E> current = root;
		Node<E> prev = null;
		Node<E> temp = null;
		
		while (current.data != key) { 

	            if (current.data.compareTo(key) < 0) {
	            	prev = current;
	                current = current.right; 
	           
	            }else if (current.data.compareTo(key) > 0) {
	            	prev = current;
	                current = current.left; 
	            }
		} 
		//System.out.println(current);
		//System.out.println(currentTrailing);
		
		
		//move the node to leaf
		
        while (current.left != null || current.right != null) {
            
        	if (prev == null) { 
                root = current.moveDown();
                prev = root;
           
        	} else if (prev.left != null && prev.left == current) {
             
                prev.left = current.moveDown();
                prev = prev.left;
            
        	} else if (prev.right != null && prev.right == current) {
               
                prev.right = current.moveDown();
                prev = prev.right;
            }
        }
        
		//System.out.println(prev);
			
			
		//remove node
			if(prev.left == current) {
				prev.left =null;
			}else {
				prev.right = null;
			}
			
		return true;
	}
	
	
	private boolean find(Node<E> n, E key) {
		Node<E> current = root;
		
		while (current != null) { 
	           
			//System.out.println(current);
	            if (current.data.compareTo(key) < 0) {
	            	
	               current = current.right; 
	           
	            }else if (current.data.compareTo(key) > 0) {
	                current = current.left; 
	            }else {
	                return true; 
	            }
		} 
	        return false; 
	        
	}
	
	
	public boolean find(E key) {
		return find(this.root, key);
	}
	
	
	private StringBuilder toString(Node<E> current, int level) { 
		StringBuilder r = new StringBuilder(); 
		
		for (int i=0; i<level; i++) { 
			r.append("--"); 
			} 
		if (current==null) { 
			r.append("null\n"); 
			return r; 
			} 
		
		r.append(current.toString()+"\n"); 
		r.append(toString(current.left,level+1)); 
		r.append(toString(current.right,level+1)); 
	return r;
	}
	
	public String toString() {
		return toString(root,0).toString();
	}
	
	
	public static void main(String[] args) {
		Treap<Integer> t = new Treap<Integer>();
//		t.add(4, 19);
//		//System.out.print(t);
//		//System.out.println("~~~~~~~~~~~~~~");
		//
		t.add(2, 31);
		//System.out.print(t);
		//System.out.println("~~~~~~~~~~~~~~");
		
//		t.add(6, 70);
//		//System.out.print(t);
//		//System.out.println("~~~~~~~~~~~~~~");
		
		t.add(1, 84);
		//System.out.print(t);
		//System.out.println("~~~~~~~~~~~~~~");
		
//		t.add(3, 12);
//		//System.out.print(t);
//		//System.out.println("~~~~~~~~~~~~~~");
		
		t.add(5, 83);//error with 5,83, error reheaping when the number comes right after the root
		//System.out.print(t);
		//System.out.println("~~~~~~~~~~~~~~");
		
//		t.add(7, 26);
//		System.out.print(t);
//		System.out.println("~~~~~~~~~~~~~~");
		
//		t.add(8, 56);
//		System.out.print(t);
//		System.out.println("~~~~~~~~~~~~~~");

		System.out.print(t);
		System.out.println("~~~~~~~~~~~~~~");
//		System.out.println(t.find(7));
//		System.out.println(t.find(2));
		
		t.delete(1);
		System.out.print(t);

	}

}
