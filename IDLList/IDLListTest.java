import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class IDLListTest {
	IDLList<String> dll = new IDLList<String>();
	
	@Test
	void testAddIntE() {
		dll.add("a");
		dll.add("b");
		dll.add(1,"c");
		System.out.println(dll.get(1).getData());
		System.out.println(dll.toString());
		assertEquals(dll.toString(), "[b,c,a]");
		assertEquals(dll.size(), 3);
		assertEquals(dll.get(1).getData(), "c");
	}

	@Test
	void testAddE() {
		dll.add("a");
		dll.add("b");
		assertEquals(dll.toString(), "[b,a]");
		assertEquals(dll.size(), 2);
		assertEquals(dll.get(1).getData(), "a");
	}

	@Test
	void testAppend() {//fix
		dll.append("c");
		assertEquals(dll.size(), 1);
		assertEquals(dll.get(0).getData(), "c");
		
		dll.add("a");
		dll.add("b");
		assertEquals(dll.toString(), "[b,a,c]");
		assertEquals(dll.size(), 3);
		assertEquals(dll.get(2).getData(), "c");
	}

	@Test
	void testRemove() {
		dll.add("a");
		dll.add("b");
		dll.remove();
		assertEquals(dll.toString(), "[a]");
		assertEquals(dll.size(), 1);
		assertEquals(dll.get(0).getData(), "a");
	}
		

	@Test
	void testRemoveLast() {
		dll.add("a");
		dll.add("b");
		dll.add("c");
		assertEquals(dll.toString(), "[c,b,a]");
		dll.removeLast();
		assertEquals(dll.size(), 2);
		assertEquals(dll.get(1).getData(), "b");
		assertEquals(dll.toString(), "[c,b]");
	}

	@Test
	void testRemoveAt() {
		dll.add("a");
		dll.add("b");
		dll.add("c");
		dll.removeAt(1);
		assertEquals(dll.toString(), "[c,a]");
		assertEquals(dll.get(1).getData(), "a");
		assertEquals(dll.size(), 2);
	}

	@Test
	void testRemoveE() {
		dll.add("a");
		dll.add("b");
		dll.add("c");
		dll.remove("b");
		assertEquals(dll.toString(), "[c,a]");
		assertEquals(dll.get(1).getData(), "a");
		assertEquals(dll.size(), 2);
		
	}

}
