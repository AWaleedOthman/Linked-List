package eg.edu.alexu.csd.datastructure.linkedList.Tests;

import eg.edu.alexu.csd.datastructure.linkedList.Classes.SLinkedList;

import javax.crypto.spec.PSource;

import static org.junit.jupiter.api.Assertions.*;

class SLinkedListTest {

    @org.junit.jupiter.api.Test
    void add() {
        SLinkedList ll = new SLinkedList();
        Integer i = 6;
        ll.add(i);
        ll.add("11");
        ll.add("22");
        ll.add("33");

        for (int j = 0; j < ll.size(); j++) {
            System.out.println(j + " " + ll.get(j));
        }

        ll.set(0, "beginning");
        ll.add(2, "TestM");
        ll.remove(1);
        System.out.println(ll.isEmpty());
        System.out.println(ll.size());
        for (int j = 0; j < ll.size(); j++) {
            System.out.println(j + " " + ll.get(j));
        }
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
