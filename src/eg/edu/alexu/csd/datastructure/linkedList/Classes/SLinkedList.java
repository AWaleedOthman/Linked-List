package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.ILinkedList;

public class SLinkedList implements ILinkedList {

    private Node start;
    private int size = 0;

    /*
    Dummy Start Node
     */
    public SLinkedList() {
        start = new Node();
    }

    private static class Node {
        private Object content;
        private Node next;
    }

    private Node getNode(int index) {
        Node current = start;
        for (int i = -1; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public void add(int index, Object element) {
        if (index > size+1 || index < 0) throw new ArrayIndexOutOfBoundsException();
        Node prev = getNode(index-1);
        Node newNode = new Node();
        newNode.content = element;
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    @Override
    public void add(Object element) {
        add(size, element);
    }

    @Override
    public Object get(int index) throws ArrayIndexOutOfBoundsException {
        if (index > size -1 || index < 0) throw new ArrayIndexOutOfBoundsException();
        Node current = start.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.content;
    }

    @Override
    public void set(int index, Object element) {
        if (index > size -1 || index < 0) throw new ArrayIndexOutOfBoundsException();
        Node myNode = getNode(index);
        myNode.content = element;
    }

    @Override
    public void clear() {
        start = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void remove(int index) {
        Node prev = getNode(index -1);
        prev.next = prev.next.next;
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * TODO
     *
     */
    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        return null;
    }

    /**
     * TODO
     *
     */
    @Override
    public boolean contains(Object o) {
        return false;
    }
}
