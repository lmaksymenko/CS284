package anagrams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	
	final Character[] alphabet = 
			{'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 
			'o', 'p', 'q', 'r', 's', 't', 'u', 
			'v', 'w', 'x', 'y', 'z'};
	
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;

	private void buildLetterTable() {
		letterTable = new HashMap<Character,Integer>();
		
		//insert all of the letters and their corresponding primes into the letter table
		for(int i = 0; i < primes.length; i++) {
			letterTable.put(alphabet[i], primes[i]);
		}
		
		//System.out.println("Table: " + letterTable);
		
	}

	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}

	
	private void addWord(String s) {
	    
	    if(s.isEmpty()) {
	    	throw new IllegalArgumentException("Empty String");
	    }
	    
	    long code = myhashcode(s);
	    //Calculate the hash code of the word
	    
	    ArrayList<String> words = anagramTable.get(code); 
	    //get the ArrayList associated with the hash
	    
	    if(anagramTable.get(code) == null ) {
	    	//if the list doesnt exist we create it
	    	words = new ArrayList<String>();
	    	words.add(s);
	    	anagramTable.put(code,words);
	    }else {
	    	//if the list exists we add the word to it
	    	words.add(s);
	    }
		
	}
	
	
	private long myhashcode(String s) {
		long code = 1;
		
		if(s.isEmpty()) {
		   throw new IllegalArgumentException("Empty String");
		}
		 
		for(char ch: s.toCharArray()) {
			//multiply the values of each char in the word together
			code *= letterTable.get(ch);
		}
		 
		return code;

	}
	
	
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		
		br.close();
	}
	
	
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		ArrayList<Map.Entry<Long,ArrayList<String>>> max = 
						new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		
		int curLen = 0;
		//Iterate the entries in the HashMap
		for(Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
			
			if(entry.getValue().size() > curLen) {
				//if we have a new max we wipe any values added before, add new value
				curLen = entry.getValue().size();
				max.clear();
				max.add(entry);
				
			}else if (entry.getValue().size() == curLen) {
				//if the size is the same add the words to the list
				max.add(entry);
			}
		}
	
		return max;
	
	}
	
	public ArrayList<Map.Entry<Long,ArrayList<String>>> publicgetMaxEntries(){
		return getMaxEntries();
	}
	
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams();

		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}
