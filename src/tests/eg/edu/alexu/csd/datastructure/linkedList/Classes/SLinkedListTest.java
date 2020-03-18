package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SLinkedListTest {

    @Test
    void next() {
        SLinkedList<String> ll = new SLinkedList<>();
        ll.add("first"); //{"first"}
        ll.add("second"); //{"first", "second"}
        ll.add(1, "new Entry"); //{"first", "new Entry", "second"}
        ll.add(2, "another One"); //{"first", "new Entry", "another One","second"}
        assertEquals(ll.next(), "first");
        assertEquals(ll.next(), "first");
        assertEquals(ll.getNext(), "first");
        assertEquals(ll.next(), "new Entry");
        ll.getNext(); ll.getNext(); ll.getNext();
        assertThrows(ArrayIndexOutOfBoundsException.class, ll::next);
        assertThrows(ArrayIndexOutOfBoundsException.class, ll::getNext);
        assertThrows(ArrayIndexOutOfBoundsException.class, ll::next);
    }

    @Test
    void resetNext() {
        SLinkedList<String> ll = new SLinkedList<>();
        ll.add("first"); //{"first"}
        ll.add("second"); //{"first", "second"}
        ll.add(1, "new Entry"); //{"first", "new Entry", "second"}
        ll.add(2, "another One"); //{"first", "new Entry", "another One","second"}
        assertEquals(ll.next(), "first");
        ll.resetNext();
        assertEquals(ll.next(), "first");
        assertEquals(ll.getNext(), "first");
        assertEquals(ll.getNext(), "new Entry");
        assertEquals(ll.getNext(), "another One");
        ll.resetNext();
        assertEquals(ll.next(), "first");
        assertEquals(ll.getNext(), "first");
        ll.remove(0);
        ll.resetNext();
        assertEquals(ll.next(), "new Entry");
        assertEquals(ll.getNext(), "new Entry");
        ll.clear();
        ll.resetNext();
        assertThrows(ArrayIndexOutOfBoundsException.class, ll::next);
        assertThrows(ArrayIndexOutOfBoundsException.class, ll::getNext);
    }

    @Test
    void add() {
        SLinkedList<String> ll = new SLinkedList<>();
        ll.add("first"); //{"first"}
        ll.add("second"); //{"first", "second"}
        ll.add(1, "new Entry"); //{"first", "new Entry", "second"}
        ll.add(2, "another One"); //{"first", "new Entry", "another One","second"}
        assertEquals(ll.get(0), "first");
        assertEquals(ll.get(1), "new Entry");
        assertEquals(ll.get(2), "another One");
        assertEquals(ll.get(3), "second");
        ll.add(1, "Add At 1"); //{"first", "Add At 1", "new Entry", "another One","second"}
        ll.add(4, "Last"); //{"first", "Add At 1", "new Entry", "another One", "Last", "second"}
        assertEquals(ll.get(4), "Last");
        assertEquals(ll.size(), 6);
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.add(10, "Error"));
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.add(-1, "Error"));
    }

    @Test
    void getNext() {
        SLinkedList<Integer> ll = new SLinkedList<>();
        for (int i = 0; i < 5; i++) {
            ll.add(i);
        }
        assertTrue(ll.hasNext());
        assertEquals(ll.getNext(), 0);
        assertTrue(ll.hasNext());
        assertEquals(ll.getNext(), 1);
        assertEquals(ll.get(3), 3);
        assertTrue(ll.hasNext());
        assertEquals(ll.getNext(), 4);
        assertFalse(ll.hasNext());
        assertThrows(ArrayIndexOutOfBoundsException.class, ll::getNext);
    }

    @Test
    void get() {
        SLinkedList<String> ll = new SLinkedList<>();
        ll.add("first"); //{"first"}
        ll.add("second"); //{"first", "second"}
        ll.add(1, "new Entry"); //{"first", "new Entry", "second"}
        ll.add(2, "another One"); //{"first", "new Entry", "another One","second"}
        assertEquals(ll.get(0), "first");
        assertEquals(ll.get(1), "new Entry");
        assertEquals(ll.get(2), "another One");
        assertEquals(ll.get(3), "second");
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.get(4));
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.get(7));
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.get(-1));
    }

    @Test
    void set() {
        SLinkedList<String> ll = new SLinkedList<>();
        ll.add("first"); //{"first"}
        ll.add("second"); //{"first", "second"}
        ll.add(1, "new Entry"); //{"first", "new Entry", "second"}
        ll.add(2, "another One"); //{"first", "new Entry", "another One","second"}
        assertEquals(ll.get(0), "first");
        assertEquals(ll.get(1), "new Entry");
        assertEquals(ll.get(2), "another One");
        assertEquals(ll.get(3), "second");
        ll.set(2, "test set"); //{"first", "new Entry", "test set","second"}
        assertEquals(ll.get(2), "test set");
        assertEquals(ll.get(3), "second");
        assertEquals(ll.get(1), "new Entry");
        assertEquals(ll.size(), 4);
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.set(-1, "Error"));
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.set(4, "Error"));
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.set(7, "Error"));

    }

    @Test
    void clear() {
        SLinkedList<String> ll = new SLinkedList<>();
        ll.clear();
        assertEquals(ll.size(),0);
        ll.add("first"); //{"first"}
        ll.add("second"); //{"first", "second"}
        ll.clear();
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.add(1, "new Entry"));
        assertEquals(ll.size(), 0);
        ll.add(0, "new Entry");
        assertEquals(ll.get(0), "new Entry");
        assertEquals(ll.size(), 1);
        ll.clear();
        assertEquals(ll.size(), 0);
    }

    @Test
    void isEmpty() {
        SLinkedList<String> ll = new SLinkedList<>();
        assertTrue(ll.isEmpty());
        ll.add("first"); //{"first"}
        assertFalse(ll.isEmpty());
        ll.clear();
        assertTrue(ll.isEmpty());
        ll.add("second"); //{"first", "second"}
        assertFalse(ll.isEmpty());
    }

    @Test
    void remove() {
        SLinkedList<String> ll = new SLinkedList<>();
        ll.add("first"); //{"first"}
        ll.add("second"); //{"first", "second"}
        ll.add(1, "new Entry"); //{"first", "new Entry", "second"}
        ll.add(2, "another One"); //{"first", "new Entry", "another One","second"}
        assertEquals(ll.size(), 4);
        assertEquals(ll.get(1), "new Entry");
        ll.remove(1);
        assertEquals(ll.size(), 3);
        assertEquals(ll.get(1), "another One");
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.remove(-1));
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.remove(6));
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.remove(-1));

    }

    @Test
    void size() {
        SLinkedList<String> ll = new SLinkedList<>();
        assertEquals(ll.size(), 0);
        ll.add("first");
        assertEquals(ll.size(), 1);
        ll.add("second");
        assertEquals(ll.size(), 2);
        ll.remove(0);
        assertEquals(ll.size(), 1);
        ll.add("new Entry");
        assertEquals(ll.size(), 2);
        ll.clear();
        assertEquals(ll.size(), 0);
        ll.add("another One");
        assertEquals(ll.size(), 1);
    }

    @Test
    void subList() {
        SLinkedList<Integer> ll = new SLinkedList<>();
        for (int i = 0; i < 10; i++) {
            ll.add(i);
        }
        SLinkedList<Integer> testLL = ll.sublist(3, 7);
        assertEquals(testLL.size(), 5);
        assertEquals(testLL.get(0), 3);
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.sublist(0, 20));
    }

    @Test
    void contains() {
        SLinkedList<String> ll = new SLinkedList<>();
        ll.add("first"); //{"first"}
        ll.add("second"); //{"first", "second"}
        ll.add(1, "new Entry"); //{"first", "new Entry", "second"}
        ll.add(2, "another One"); //{"first", "new Entry", "another One","second"}
        assertTrue(ll.contains("second"));
        assertFalse(ll.contains("Second"));
        assertTrue(ll.contains("new Entry"));
        assertTrue(ll.contains("first"));
    }
}
