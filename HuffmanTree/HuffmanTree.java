package Homework7;

import java.util.PriorityQueue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Instructions: 
 * First: Read through the assignment specification, make sure you understand what the assignment is asking for.
 * Second: There are number of "TODO" instructions within this code, make sure to complete all of them fully.
 * Third: Test you code.
 */


// TODO: Name and Pledge

// Pledge: Honor!
// Name: Leonid Maksymenko


/**
 * HW4 CS284 Fall 2020
 * Implements a Huffman encoding tree.
 * The included code has been commented for the student's benefit, feel free to read through it.
 */
public class HuffmanTree {

	// ******************** Start of Stub Code ******************** //
	// ************************************************************ //
    /** Node<E> is an inner class and it is abstract.
     * There will be two kinds
     * of Node, one for leaves and one for internal nodes. */
    abstract static class Node implements Comparable<Node>{
        /** The frequency of all the items below this node */
        protected int frequency;
        
        public Node(int freq) {
        	this.frequency = freq;
        }
        
		/** Needed for the Minimum Heap used later in this stub. */
		public int compareTo(Node other) {
			return this.frequency - other.frequency;
		}
    }
    /** Leaves of a Huffman tree contain the data items */
    protected static class LeafNode extends Node {
        // Data Fields
        /** The data in the node */
        protected char data;
        /** Constructor to create a leaf node (i.e. no children) */
        public LeafNode(char data, int freq) {
            super(freq);
            this.data = data;
        }
        /** toString method */
        public String toString() {
            return "[value= "+this.data + ",freq= "+frequency+"]";
        }
    }
    /** Internal nodes contain no data,
     * just references to left and right subtrees */
    protected static class InternalNode extends Node {
        /** A reference to the left child */
        protected Node left;
        /** A reference to the right child */
        protected Node right;

        /** Constructor to create an internal node */
        public InternalNode(Node leftC, Node rightC) {
            super(leftC.frequency + rightC.frequency);
            left = leftC; right = rightC;
        }
        public String toString() {
            return "(freq= "+frequency+")";
        }
    }
	
	// Enough space to encode all "extended ascii" values
	// This size is probably overkill (since many of the values are not "printable" in the usual sense)
	private static final int codex_size = 256;
	
	/* Data Fields for Huffman Tree */
	private Node root;
	private static String endcodingString;
	
	public HuffmanTree(String s) {
		root = buildHuffmanTree(s);
	}
	
	/**
	 * Returns the frequencies of all characters in s.
	 * @param s
	 * @return
	 */
	public static int[] frequency(String s) {
		int[] freq = new int[codex_size];
		for (char c: s.toCharArray()) {
			freq[c]++;
		}
		return freq;
	}
	
	/**
	 * Builds the actual Huffman tree for that particular string.
	 * @param s
	 * @return
	 */
	private static Node buildHuffmanTree(String s) {
		endcodingString = s;
		int[] freq = frequency(s);
		
		// Create a minimum heap for creating the Huffman Tree
		// Note to students: You probably won't know what this data structure
		// is yet, and that is okay.
		PriorityQueue<Node> min_heap = new PriorityQueue<Node>();
		
		// Go through and create all the nodes we need
		// as in, all the nodes that actually appear in our string (have a frequency greater then 0)
		for(int i = 0; i < codex_size; i++) {
			if (freq[i] > 0) {
				// Add a new node (for that character) to the min_heap, notice we have to cast our int i into a char.
				min_heap.add(new LeafNode((char) i, freq[i]));
			}
		}
		
		// Edge case (string was empty)
		if (min_heap.isEmpty()) {
			throw new NullPointerException("Cannot encode an empty String");
		}
		
		// Now to create the actual Huffman Tree 
		// NOTE: this algorithm is a bit beyond what we cover in cs284, 
		// you'll see this in depth in cs385
		
		// Merge smallest subtrees together
		while (min_heap.size() > 1) {
			Node left = min_heap.poll();
			Node right = min_heap.poll();
			Node merged_tree = new InternalNode(left, right);
			min_heap.add(merged_tree);
		}
		
		// Return our structured Huffman Tree
		return min_heap.poll();
	}
	
	// ******************** End of Stub Code ******************** //
	// ********************************************************** //
	
	
	public String bitsToString(Boolean[] encoding) {
		//turn a boolean list into string of bits
		String binary = "";
		for(int i = 0; i<encoding.length; i++) {
			
			if (encoding[i]) {
				binary += "1";
			}else {
				binary += "0";
			}
		}
		return binary;
	}
	
	public String multiply(int x, String chr) {
		//creates a string with a repeated char
	    String repeated = "";
		for (int i = 0; i < x; i++) {
			repeated += chr;
		}
	        
	    return repeated;
	}
	
	public String preOrderTraversal(Node cur, int indent) {
		String str = "";
		
		str += multiply(indent, "-") + cur.toString() + "\n";
		indent += 2;
		
		if(cur instanceof InternalNode ) {
			InternalNode cur1 = (InternalNode) cur;
			str += "{t}" + preOrderTraversal(cur1.right, indent);
			str += "{f}" + preOrderTraversal(cur1.left, indent);
			
		}
		return str;
	}
	
	public String toString() {
		return "Top path is true, lower path is false:" + "\n" + preOrderTraversal(this.root, 0);
    }
	
	public String boolArrayToString(Boolean[] array) {
		String output = "[";
		
		for(int i = 0; i<array.length; i++) {
			output += array[i] +", ";
		}
		output += "]";
		return output;
	}
	
	public String decode(Boolean[] coding) {
		//turns a boolean array into a letter
		Node current = root;
		String message = "";
		
		for(int i = 0; i<coding.length; i++) {
			
			if(coding[i]) {
					
					if( ((InternalNode)current).right != null) {
						current = ((InternalNode)current).right;
					}
			}else {
					
					if( ((InternalNode)current).left != null) { 
						current = ((InternalNode)current).left;
					}
			}
		
			if(current instanceof LeafNode) {
				message += ((LeafNode)current).data;
				current = root;
			}else if (i == coding.length - 1 && !(current instanceof LeafNode)) {
				throw new IllegalArgumentException("Encoding doesn't line up with the tree.");
			}
		}
		
		return message;
	}
	
	
	public void encodePath(Node n, char s, ArrayList<Boolean> path, ArrayList<Boolean> result) {
		//Recursively searches the tree until the desired char is found
		Node current = n; 
		
		if(current instanceof LeafNode) {
			
			if( ((LeafNode)current).data == s) { 
				result.addAll(path);
				
			}
			return;
		}
		
		if(current instanceof InternalNode) {
			//explore the two paths of the tree
			if( ((InternalNode)current).left != null) {
				path.add(false);
				encodePath( ((InternalNode)current).left, s, path , result);
				path.remove(path.size()-1);
			}
			
			if( ((InternalNode)current).right != null){
				path.add(true);
				encodePath( ((InternalNode)current).right, s, path, result);
				path.remove(path.size()-1);
			}
			
		}
		
	}
	
	public Boolean encodeValid(char[] inputTextChars) {
		//method checks if the string can be encoded using the tree
		for(int i = 0; i<inputTextChars.length; i++) {
			if(endcodingString.indexOf(inputTextChars[i]) == -1) { 
				return true;
			}
		}
		return false;
	}

	
	public Boolean[] encode(String inputText) {//change to Boolean[]
		//left in the tree is false, right in the tree is true
		
		ArrayList<Boolean> result = new ArrayList<>();
		ArrayList<Boolean> path = new ArrayList<>();
		ArrayList<Boolean> encodedCharsList = new ArrayList<>();
		char[] charArray = inputText.toCharArray();
		 
		 
		if(encodeValid(charArray)) {//Checking if the string can be encoded
			 throw new IllegalArgumentException("This tree cannot encode this string.");
		}
		     
		  
		for(int i = 0; i<charArray.length; i++) {
			//for every char in the string clear previous values, encode, add encoded list
			 result.clear();
			 path.clear();
			 
			 encodePath(this.root, charArray[i], path, result);
			 encodedCharsList.addAll(result);
		    	 
		}
		 
		Boolean[] encodedCharsArray = new Boolean[encodedCharsList.size()];
		     
		for(int i = 0; i<encodedCharsList.size(); i++) {
			//turns array list into boolean array
			 encodedCharsArray[i] = encodedCharsList.get(i);
		}
		     
		return encodedCharsArray;
	     
	}
	
	public Boolean[] efficientEncode(String inputText) {
		
		ArrayList<Boolean> result = new ArrayList<>();
	    ArrayList<Boolean> path = new ArrayList<>();
	    ArrayList<Boolean> encodedCharsList = new ArrayList<>();
	    char[] charArray = inputText.toCharArray();
		HashMap<Character,ArrayList<Boolean>> pastLookups = new HashMap<>();
		
		if(encodeValid(charArray)) {//Checking if the string can be encoded
			 throw new IllegalArgumentException("This tree cannot encode this string.");
		}
		
		 for(int i = 0; i<charArray.length; i++) {
			 
			 if(pastLookups.containsKey(charArray[i])) {
				 //if value has been looked up then grab prev value
				 encodedCharsList.addAll(pastLookups.get(charArray[i]));
			 }else {
				 //else look up val and add it to the map
				 result.clear();
				 path.clear();
				 
				 encodePath(this.root, charArray[i], path, result);
				 pastLookups.put(charArray[i], result);
				 encodedCharsList.addAll(result);
			 }
			 
		 }
		 Boolean[] encodedCharsArray = new Boolean[encodedCharsList.size()];
		     
		 for(int i = 0; i<encodedCharsList.size(); i++) 
			 //turn the ArrayList of bools into a normal array
			 encodedCharsArray[i] = encodedCharsList.get(i);
		     
		 pastLookups.clear();
		 return encodedCharsArray;
	}
	
	
	
	public static void main(String[] args) {
		// Code to see if stuff works...
		String s = "Some string you want to encode";
		HuffmanTree t = new HuffmanTree(s); // Creates specific Huffman Tree for "s"
		// Now you can use encode, decode, and toString to interact with your specific Huffman Tree
		
		//test bitToString
//		Boolean[] bool = {true, false, true,true, false, true};
//		System.out.println(t.bitsToString(bool));
		
		//System.out.println(t);
		//Boolean[] word = {false,true,true,false,false,false,false,false,true,true,false,true,false,true,false,false,true,true,false,false,true,true,true,false,true,true};
		Boolean[] testS = {false,true,true,false,false, true};
		//Boolean[] testT = {false,false,false};
		Boolean[] testS1 = {false, true, true, false, false};
		
		//System.out.println(t.decode(word));
		//System.out.println(t.decode(testS));
	//	System.out.println(t.decode(testT));
		ArrayList<Boolean> path = new ArrayList<Boolean>();
		//System.out.println(testS);
		//System.out.println(t.decode(testS1));
		//System.out.println(t.decode(t.encode("o yea mr krabs")));
		//t.decode(t.encode("b"));
		
		System.out.println(t.decode(t.encode("string")));
		
		System.out.println(t.decode(t.efficientEncode("string")));
		
		System.out.println(t.decode(testS));
		
		
	}
}

