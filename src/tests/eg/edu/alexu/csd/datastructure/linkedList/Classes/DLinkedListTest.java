package eg.edu.alexu.csd.datastructure.linkedList.Classes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

class DLinkedListTest {
	
	DLinkedList<Integer> myDLL = new DLinkedList<>(Integer.class);

    @Test
    void add() {
        DLinkedList<String> ll = new DLinkedList<>(String.class);
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.get(0));
        ll.add("first");
        ll.add("second");
        ll.add(1, "new Entry");
        ll.add(2, "another One");
        ll.add(ll.size(),"end");
        assertEquals(ll.get(0), "first");
        assertEquals(ll.get(0).charAt(0), 'f');
        assertEquals(ll.get(1), "new Entry");
        assertEquals(ll.get(2), "another One");
        assertEquals(ll.get(3), "second");
        assertEquals(ll.get(ll.size()-1), "end");
        ll.set(2, "test set");
        assertEquals(ll.get(2), "test set");
        assertEquals(ll.size(), 5);
        assertThrows(ArrayIndexOutOfBoundsException.class, ()->ll.get(5));
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.add(-1, "none"));
        ll.remove(1);
        assertEquals(ll.get(2), "second");
        assertEquals(ll.size(), 4);
        assertFalse(ll.isEmpty());
        assertTrue(ll.contains("first"));
        assertFalse(ll.contains("another One"));
        assertTrue(ll.contains("test set"));
        assertFalse(ll.contains(5));
        ll.add(0,"before first");
        ll.remove(ll.size()-2);
        ll.clear();
        assertTrue(ll.isEmpty());
    }
    @Test
    void subList() {
    	myDLL.clear();
        DLinkedList<Integer> testLL = new DLinkedList<>(Integer.class);
        for (int i = 0; i < 10; i++) {
            myDLL.add(i);
        }
        for(int i = 3; i < 8; i++) {
        	testLL.add(i);
        }
        assertIterableEquals(testLL, myDLL.sublist(3, 7));
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> myDLL.sublist(0, 20));
        for(int i = 3; i < 8; i++) {
        	testLL.set(i-3, 10-i);
        }
        assertThrows(IllegalArgumentException.class, ()-> testLL.set(0,"hello"));
        assertIterableEquals(testLL, myDLL.sublist(7, 3));
    }
    
    @Test
    void testClear() {
    	for(int i = 0; i < 10; i++) {
    		myDLL.add(i);
    	}
    	myDLL.clear();
    	assertThrows(ArrayIndexOutOfBoundsException.class, () -> myDLL.get(0));
    }
    
    @Test
    void testTypeConversions() {
    	myDLL = new DLinkedList<>(Integer.class);
    	myDLL.add(3);
    	assertThrows(IllegalArgumentException.class, ()-> myDLL.add("abc"));
    }
    @Test
    void testIterator() {
    	DLinkedList<Integer> testDLL = new DLinkedList<Integer>(Integer.class);
    	for(int i = 0; i < 10; i++) {
    		myDLL.add(i);
    		if(i == 5 || i == 4)
    			continue;
    		testDLL.add(i);
    	}
    	for(Integer i : myDLL) {
    		System.out.println(i);
    	}
    	for(Iterator<Integer> i = myDLL.iterator(); i.hasNext(); ) {
    		Integer current = i.next();
    		if(current == 4)
    			i.remove();
    	}
    	for(Iterator <Integer> i = myDLL.iterator(1); i.hasNext();) {
    		Integer current = i.next();
    		if(current == 5)
    			i.remove();
    		System.out.println(current);
    	}
    	assertIterableEquals(myDLL, testDLL);
    }
}
