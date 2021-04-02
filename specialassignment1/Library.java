package specialassignment1;
import java.util.*;

public class Library extends SLL<ArrayList<Book>>{ // do I use generic in SLL?
	//data fields TO-DO
	
	SLL<ArrayList<Book>> shelves = new SLL<ArrayList<Book>>();
	Node<ArrayList<Book>> head;
	/**
	 * Create library with empty shelves, but 26 shelves from A-Z
	 */
	public Library() {
		//TO-DO
		char[] alphabet = {'Z', 'Y', 'X', 'W', 'V', 'U', 'T', 'S', 'R', 'Q', 'P', 'O', 'N', 'M', 'L', 'K', 'J', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'};
		
		for (int i = 0; i < alphabet.length; i ++) { 
			shelves.addFirst(new ArrayList<Book>(), alphabet[i]);
		}
		head = shelves.head;
		//System.out.print(head);
		//System.out.print(shelves);
	}
	
	/**
	 * Add Book -- put in node with matching character of the first letter
	 * of author last name (ie. JK Rowling goes in R)
	 */
	public void addBook(Book aBook) {
		
		Node<ArrayList<Book>> current = head;
		//System.out.print(current.letter);
		while (current.letter != aBook.getAuthorLastName().charAt(0)) {
			current = current.next;
		}

		current.data.add(aBook);
		
	}
	
	/**
	 * Returns true if the book was successfully found and removed 
	 * @param title
	 * @param last
	 * @param first
	 * @return
	 */
	public boolean removeBook(String title, String last, String first) {
		Node<ArrayList<Book>> current = head;

		while (current.letter != last.charAt(0)) {
			current = current.next;
		}
		// search through array and see whether book is in array ----> if yes: remove it and return true
		for (int i = 0; i < current.data.size(); i++) {
			if (current.data.get(i).getTitle() == title) {
				current.data.remove(i);
				return true;
			}
		}
		return false;
	}
	/**
	 * Gets all the books from a particular author
	 * @param last
	 * @param first
	 */
	public ArrayList<Book> getAuthorsBook(String last, String first) {
		//TO DO
		Node<ArrayList<Book>> current = head;
			
		while (current.letter != last.charAt(0)) {
			current = current.next;
		}
	
		if (current.data != null) {
			return current.data;
		}else {
			throw new IllegalArgumentException("getAuthorsBook: author does not have any books");
		}
	}
	
	public ArrayList<String> getAllTitles() {
		//TO DO
		Node<ArrayList<Book>> current = head;
		ArrayList<String> all = new ArrayList<String>();
	
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < current.data.size(); j++) {
				all.add(current.data.get(j).getTitle().toString());
			}
			current = current.next;
		}
		return all;
	}
	
	/**
	 * Create toString method to show all of the inputs
	 */
	public String toString() {
		//TO DO
		StringBuilder s = new StringBuilder();
		
		Node<ArrayList<Book>> current = head;
		
		while (current != null) {
			//System.out.print(current.letter );
			
			s.append(current.letter + ":");
			for(int i = 0; i < current.data.size(); i++) {
				s.append(current.data.get(i).getTitle());
				if (i != current.data.size() -1 ) {
					s.append(", ");
				}
			}
			//s.append(current.data.toString());//current.data is an array list of books
			
			s.append(System.getProperty("line.separator"));
			current = current.next;
			
		}
		
		return s.toString();

	}	
	
	public static void main (String[] args) {
		//System.out.println("In Main");
		
		Library l1 = new Library();
		//Book b1 = new Book(null, null, null, 9);
		Book b1 = new Book("The Assignment", "Xu", "Connie", 195);
		Book b4 = new Book("The Assignment 2", "Xu", "Connie", 195);
		Book b2 = new Book("The Special Assignment", "McCreesh", "Michael", 210);
		Book b3 = new Book("CS 284", "Bonelli", "Eduardo", 202);
		l1.addBook(b1);
		l1.addBook(b2);
		l1.addBook(b3);
		l1.addBook(b4);
		
		System.out.println(l1.toString());
		
		//System.out.println("Out Main");
	}
}


