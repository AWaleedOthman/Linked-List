package eg.edu.alexu.csd.datastructure.linkedList.Tests;

import eg.edu.alexu.csd.datastructure.linkedList.Classes.SLinkedList;

import static org.junit.jupiter.api.Assertions.*;

class SLinkedListTest {

    @org.junit.jupiter.api.Test
    void add() {
        SLinkedList ll = new SLinkedList();
        ll.add("first element index zero");
        ll.add("second element index one");
        System.out.println(ll.get(0));
        System.out.println(ll.get(1));
        System.out.println(ll.isEmpty());
        System.out.println(ll.size());
        ll.clear();
        System.out.println(ll.isEmpty());
        System.out.println(ll.size());
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
    }

    @org.junit.jupiter.api.Test
    void size() {
    }
}
