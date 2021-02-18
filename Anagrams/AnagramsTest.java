package anagrams;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

class AnagramsTest {

	
	@Test
	void test() {
		Anagrams a = new Anagrams();

		
		//
		ArrayList<String> words = new ArrayList<String>();
		
	//	ArrayList<Map.Entry<Long,ArrayList<String>>> testResult = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		
		String[] strs = {"alerts", "alters", "artels", "estral", "laster", "lastre", 
				"rastle", "ratels", "relast", "resalt", "salter", "slater", "staler", "stelar", "talers"};
		
		words.addAll(Arrays.asList(strs));
		
	//	Map.Entry<Long,ArrayList<String>> entry = entry((long) 236204078, words);
		
	
	//	testResult.add(entry);
		
		//System.out.println(testResult);
	//	System.out.println(testMap);
		   
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.publicgetMaxEntries();
		
		assertEquals(maxEntries.get(0).getKey(), (long) 236204078);
		assertEquals(maxEntries.get(0).getValue(), words);
	}
	
//	public void buildLetterTable() {
//		fail("Not yet implemented");
//		//Anagrams a = new Anagrams();
//		//assertEquals({a=2, b=3, c=5, d=7, e=11, f=13, g=17, h=19, i=23, j=29, k=31, l=37, m=41, n=43, o=47, p=53, q=59, r=61, s=67, t=71, u=73, v=79, w=83, x=89, y=97, z=101}, a.letterTable);
//				
//	}
	
	//myhashcode
//	public void myhashcode() {
//		Anagrams a = new Anagrams();
//		assertEquals(a.myhashcode("alters"), 22);
//	}
	//addWord
	
	//getMaxEntries
	

}
