package specialassignment1;

public class SLL<E> {

	public static class Node<F>{
		// data fields
		F data;
		Node<F> next;
		char letter;
			
		public Node(F data, Node<F> next, char letter) {
			super();
			this.data = data;
			this.next = next;
			this.letter = letter;
		}

		public Node(F data, char letter) {
			super();
			this.data = data;
			this.next = null;
			this.letter = letter;
		}
		
	}
	
	// data fields
	Node<E> head;
	int size;
	
	public SLL() {
		head=null;
		size=0;
	}
	
	public void addFirst(E item, char letter) {
		head = new Node<>(item,head,letter);
		size++;
	}
	
	
	public E getAt(int index) {
		if (index<0 || index>size) {
			throw new IllegalArgumentException("getAt: index out of bounds");
		}
		Node<E> current = head;
		for (int i=0; i<index; i++) {
			current=current.next;
		}
		
		return current.data;
	}	
	
	public void removeFirst() {
		if (head==null) {
			throw new IllegalStateException("removeFirst: list is empty");
		}
		head = head.next;
		size--;
	}
	
	public void remove(int index) {
		if (index<0 || index>size) {
			throw new IllegalArgumentException("remove: index out of bounds");
		}
		if (index==0) {
			removeFirst();
		} else {
			Node<E> current = head;
			for (int i=0; i<index-1; i++) {
				current=current.next;
			}

			current.next = current.next.next;
			size--;
		}
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		
		Node<E> current = head;
		//s.append("[");
		while (current!=null) {
			s.append(current.data.toString()+";");
			current = current.next;
		}
		//s.append("]");
		return s.toString();
		
	}
}
