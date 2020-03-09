package eg.edu.alexu.csd.datastructure.linkedList.Tests;
import org.junit.jupiter.api.Test;

import eg.edu.alexu.csd.datastructure.linkedList.Classes.DLinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DLinkedListTest {
	
	DLinkedList<Integer> myDLL = new DLinkedList<Integer>(Integer.class);
	
    @Test
    void add() {
        DLinkedList<String> ll = new DLinkedList<String>(String.class);
        ll.add("first");
        ll.add("second");
        ll.add(1, "new Entry");
        ll.add(2, "another One");
        assertEquals(ll.get(0), "first");
        assertEquals(ll.get(1), "new Entry");
        assertEquals(ll.get(2), "another One");
        assertEquals(ll.get(3), "second");
        ll.set(2, "test set");
        assertEquals(ll.get(2), "test set");
        assertEquals(ll.size(), 4);
        assertThrows(ArrayIndexOutOfBoundsException.class, ()->ll.get(4));
        ll.remove(1);
        assertEquals(ll.get(2), "second");
        assertEquals(ll.size(), 3);
        assertFalse(ll.isEmpty());
        assertTrue(ll.contains("first"));
        assertFalse(ll.contains("another One"));
        assertTrue(ll.contains("test set"));
        assertFalse(ll.contains(5));
        ll.clear();
        assertTrue(ll.isEmpty());
    }
    @Test
    void subList() {
        DLinkedList<Integer> ll = new DLinkedList<>(Integer.class);
        for (int i = 0; i < 10; i++) {
            ll.add(i);
        }
        DLinkedList<Integer> testLL =  ll.sublist(3, 7);
        assertEquals(testLL.size(), 5);
        assertEquals(testLL.get(0), 3);
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.sublist(0, 20));
        assertThrows(IllegalArgumentException.class, () -> ll.sublist(2,0));
    }
    
    @Test
    void testClear() {
    	for(int i = 0; i < 10; i++) {
    		myDLL.add(Integer.valueOf(i));
    	}
    	myDLL.clear();
    	assertThrows(ArrayIndexOutOfBoundsException.class, () -> {myDLL.get(0);});
    }
    
    @Test
    void testTypeConversions() {
    	myDLL = new DLinkedList<Integer>(Integer.class);
    	myDLL.add(3);
    	assertThrows(IllegalArgumentException.class, ()-> myDLL.add("abc"));
    }
}