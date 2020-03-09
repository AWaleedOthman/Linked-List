import eg.edu.alexu.csd.datastructure.linkedList.Classes.SLinkedList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SLinkedListTest {

    @Test
    void add() {
        SLinkedList<String> ll = new SLinkedList<>();
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
    @SuppressWarnings("unchecked")
    void subList() {
        SLinkedList<Integer> ll = new SLinkedList<>();
        for (int i = 0; i < 10; i++) {
            ll.add(i);
        }
        SLinkedList<Integer> testLL = (SLinkedList<Integer>) ll.sublist(3, 7);
        assertEquals(testLL.size(), 5);
        assertEquals(testLL.get(0), 3);
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> ll.sublist(0, 20));
    }

}
