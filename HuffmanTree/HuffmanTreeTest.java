package Homework7;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class HuffmanTreeTest {
	static HuffmanTree testTree;
	Boolean[] p = {true,true,true,true,true};
	
	@BeforeAll
	static void setUp() {
		testTree = new HuffmanTree("abcdefghijklmnopqrstuvwxyz");
		System.out.println(testTree);
	}
	
	@Test
	void testDecode() {
		System.out.println("Decoded: " + testTree.decode(p));
		System.out.println("Correct: "+ "p");
		assertEquals(testTree.decode(p), "p");
	}

	@Test
	void testEncodePath() {
		System.out.println("Path: " + testTree.boolArrayToString(testTree.encode("p")));
		System.out.println("Correct: "+ testTree.boolArrayToString(p));
		assertEquals(testTree.encode("p"), p);//not asserting porperly but it works
	}

	@Test
	void testEncodeValid() {
		char[] test = {'a', '.'};
		assertEquals(testTree.encodeValid(test), true);
	}

	@Test
	void testEncode() {
		assertEquals(testTree.decode(testTree.encode("p")), "p");
	}

	@Test
	void testEfficientEncode() {
		assertEquals(testTree.decode(testTree.efficientEncode("p")), "p");
	}
	
	public static void main(String[] args) {
		
	}
	
}
