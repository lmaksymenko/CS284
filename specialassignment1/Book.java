package specialassignment1;
import java.util.Queue;
import java.util.LinkedList;

public class Book {
    //data fields here
	
	private String title;
	private String authorLastName;
	private String authorFirstName;
	private int pageCount;
	private Queue<String> checkout;

    /**
     * Create a book object
     * Create a randomly generated id
     */
	
	//Book book = new Book(title, authorLastName, authorFirstName, pageCount);
	
    public Book(String title, String authorLastName, String authorFirstName, int pageCount) {
    	
    	if (title == "") {
    		throw new IllegalArgumentException("title: title can not be empty");
    	}
    	
    	else if (authorLastName == "") {
    		throw new IllegalArgumentException("authorLastName: author last name can not be empty");
    	}
    	
    	else if (authorFirstName == "") {
    		throw new IllegalArgumentException("authorFirstName: author first name can not be empty");
    	}
    	
    	else if (pageCount < 1) {
    		throw new IllegalArgumentException("pageCount: page count must be > 0");
    	}
    	
    	else {
    		
    		this.title = title;
    		this.authorLastName = authorLastName;
    		this.authorFirstName = authorFirstName;
    		this.pageCount = pageCount;
    		
    		checkout = new LinkedList<String>();
    		
    	}
    }
    //GETTERS TO-DO
    public String getTitle() {
    	return title;
    }
    public int getPageCount() {
    	return pageCount;
    }
    public String getAuthorFirstName() {
    	return authorFirstName;
    }
    public String getAuthorLastName() {
    	return authorLastName;
    }

    public boolean checkoutBook(String name) {
        //TO-DO

    	for (String n: checkout) {
    		if (n == name) {
    			return false;
    		}
    	}
    	
    	checkout.add(name);
    	return true;
    	
    }
    
    public boolean returnBook() {
    	//TO-DO
    	
    	if (checkout.peek() == null) {
    		return false;
    	}
    	else {
    		checkout.remove();
    		return true;
    	}
    }
    
    public String toString() {	
    	return getTitle() + " by " + getAuthorFirstName() + getAuthorLastName();
    	
    }
}
