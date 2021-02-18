import java.util.ArrayList;
//Leonid Maksymenko
//I pledge my honor that I have abided by Stevens Honor Code

public class IDLList<E>{

	public static class Node<E>{
		//data fields 
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		Node(E elem){
			this.data = elem;
			this.next = null;
			this.prev = null;
		}
		
		Node(E elem, Node<E> prev, Node<E> next){
			this.data = elem;
			this.next = next;
			this.prev = prev;
		}

		public E getData() {
			return data;
		}
		
	}
	//data fields
	private Node<E> head;
	private Node<E> tail;
	private int size;
	ArrayList<Node<E>> indices;
	
	public IDLList() {
		head = null;
		tail = null;
		size = 0;
		indices = new ArrayList<Node<E>>();
	}
	
	//methods
	public boolean add (int index, E elem) {
		//adds element at index
		if(index > size-1) {//index too large
			append(elem);
			size++;
			return true;
		}else if(index == 0) {//index is 0
			add(elem);
			return true;
		}
		Node<E> current = indices.get(index);
		Node<E> newNode = new Node<E>(elem);
		
		newNode.next = current;
		newNode.prev = current.prev;
		current.prev.next = newNode;
		current.prev = newNode;
		indices.add(index, newNode);
		size++;
		return true;
	}
	
	public boolean add (E elem) {
		//add element to head of the list
		if(head == null) {
			head = new Node<E>(elem);
			tail = head;
			size++;
			indices.add(0, head);
			return true;
		}
		Node<E> current = head;
		head = new Node<E>(elem);
		head.next = current;
		current.prev = head;
		indices.add(0, head);
		size++;
		return true;
	}
	
	public boolean append (E elem) {
		//add element to last index
		if(head == null) {
			head = new Node<E>(elem);
			size++;
			indices.add(head);
			return true;
		}
		Node<E> current = tail;
		tail = new Node<E>(elem);
		tail.prev = current;
		current.next = tail;
		indices.add(tail);
		size++;
		return true;
	}
	
	public Node<E> get (int index) {
		//gets element at a position
		if(index < 0 || index > size-1) {
			throw new IllegalArgumentException("Index out of bounds.");
		}
		return indices.get(index);
	}
	
	public Node<E> getHead () {
		return head;
	}
	
	public Node<E> getLast () {
		return tail;
	}
	
	public int size() {
		return size;
	}
	
	public Node<E> remove () {
		//removes head
		if(head == null) {
			throw new IllegalStateException("Head is null.");
		}
		
		Node<E> current = head;
		head = head.next;
		indices.remove(0);
		size--;
		return current;
	}
	
	public Node<E> removeLast () {
		//removes tail
		if(tail == null) {
			throw new IllegalStateException("Head is null.");
		}
		Node<E> current = tail;
		tail = current.prev;
		tail.next = null;
		indices.remove(size-1);
		size--;
		return current;
	}
	
	public Node<E> removeAt (int index) {
		//removes at index
		if(index < 0 || index > size-1) {
			throw new IllegalArgumentException("Index out of bounds.");
		}else if(index == 0) {
			remove();
		}
		
		Node<E> current = indices.get(index);
		current.prev.next = current.next;
		current.next.prev = current.prev;
		indices.remove(index);
		size--;
		return current;
		
	}
	
	public boolean remove (E elem) {
		//removes first occurrence of the element
		Node<E> current = head;
		int index = 0;
		
		while(current.next != null) {
			
			if(current.getData() == elem) {
				removeAt(index);
				return true;
			}
			current = current.next;
			index++;
		}
		throw new IllegalStateException("No such elements in list.");
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node<E> current=head;result.append("[");
		
		while (current!=null) {
			
			if (current.next != null){
				result.append(current.getData().toString()+",");
			}else {
				result.append(current.getData().toString());
			}
			current = current.next;
		}
		result.append("]");
		return result.toString();
	}
	
//	public String toString() {
//		//return a to string of the DDL 
//		StringBuilder sb = new StringBuilder();
//		
//		for (Node<E> s : indices){
//		    sb.append(s.data);
//		    sb.append("\t");
//		}
//
//		return (sb.toString());
//	}
	
	//test cases
	public static void main(String args[]) {
		/* ~~Checklist~~
public IDLList () 						|good
public boolean add (int index, E elem)	|good
public boolean add (E elem)				|good
public boolean append (E elem)			|good
public E get (int index)				|good
public E getHead ()						|good
public E getLast ()						|good
public int size()						|good
public E remove ()						|good
public E removeLast ()					|good
public E removeAt (int index)			|good
public boolean remove (E elem)			|good
public String toString()				|good
		 */
//		IDLList<String> dll = new IDLList<String>();
//		dll.add("one");
//		dll.add("two");
//		System.out.println(dll);
//		System.out.println();
//		
//		System.out.println("getHead(): " + dll.getHead().getData());
//		System.out.println("getLast(): " + dll.getLast().getData());
//		System.out.println();
//		
//		System.out.println(dll);
//		dll.add(1,"middle");
//		System.out.println("add(1,\"middle\"): " + dll);
//		System.out.println("size(): " + dll.size());
//		System.out.println("getHead(): " + dll.getHead().getData());
//		System.out.println();
//		
//		System.out.println(dll);
//		dll.remove();
//		System.out.println("remove(): "+ dll);
//		System.out.println("getHead(): " + dll.getHead().getData());
//		System.out.println("getLast(): " + dll.getLast().getData());
//		System.out.println();
//		
//		System.out.println(dll);
//		dll.append("last");
//		System.out.println("append(\"last\"): "+ dll);
//		dll.removeLast();
//		System.out.println("removeLast(): " + dll);
//		System.out.println("getLast(): " + dll.getLast().getData());
//		dll.add(10, "outofbounds");
//		System.out.println("add(10,\"outofbounds\"): " + dll);
//		System.out.println("getHead(): " + dll.getHead().getData());
//		System.out.println("getLast(): " + dll.getLast().getData());
//		System.out.println();
//		
//		System.out.println(dll);
//		dll.add("valone");
//		dll.add("valtwo");
//		System.out.println("add(): "+ dll);
//		dll.removeAt(2);
//		System.out.println("removeAt(1): " + dll);
//		System.out.println();
//		
//		System.out.println(dll);
//		dll.remove("middle");
//		System.out.println("dll.remove(\"middle\"): " + dll);
//		System.out.println();
//		
//		System.out.println(dll);
//		System.out.println("dll.get(1): " + dll.get(1).getData());
//		System.out.println();
		
		
//		IDLList<String> dll = new IDLList<String>();
//		dll.add("one");
//		dll.add("two");
//		System.out.println(dll);
//		System.out.println();
//		dll.removeLast();
//		//System.out.print(dll.get(1));
//		System.out.print(dll);
		
//		dll.add("b");
//		dll.append("a");
//		System.out.print(dll);
		
		
		
	}

	
	
}

