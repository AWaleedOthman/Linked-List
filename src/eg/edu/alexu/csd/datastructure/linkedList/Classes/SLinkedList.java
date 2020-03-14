package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.ILinkedList;

public class SLinkedList <T> implements ILinkedList {

    private Node start;
    private int size = 0;
    private Node current; //used in getNext() and hasNext()

    /*
    Dummy Start Node
     */
    public SLinkedList() {
        start = new Node();
        start.next = start; //circular
        current = start; //used in getNext() and hasNext()
    }

    private class Node {
        private T content;
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
    @SuppressWarnings("unchecked")
    public void add(int index, Object element) {
        if (index > size || index < 0) throw new ArrayIndexOutOfBoundsException();//index = size+1 should not be allowed, maximum should be size (in which case a new element is appended 

        Node prev = getNode(index-1);//OR MAYBE I'M WRONG..:/
        Node newNode = new Node();
        newNode.content = (T)element;
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    @Override
    public void add(Object element) {
        add(size, element);
    }
    
    @Override
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        if (index > size -1 || index < 0) throw new ArrayIndexOutOfBoundsException();
        Node current = start.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        this.current = current;
        return current.content;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void set(int index, Object element) {
        if (index > size -1 || index < 0) throw new ArrayIndexOutOfBoundsException();
        Node myNode = getNode(index);
        myNode.content = (T)element;
    }

    @Override
    public void clear() {
        start.next = start;
        current = start;
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

    @Override
    public SLinkedList<T> sublist(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size -1 || fromIndex > toIndex) throw new ArrayIndexOutOfBoundsException();
        SLinkedList<T> newList = new SLinkedList<>();
        for (int i = fromIndex; i <= toIndex; i++) {
            newList.add(get(i));
        }
        return newList;
    }

    @Override
    public boolean contains(Object o) {
        Node current = start.next;
        for (int i = 0; i < size; i++) {
            if (current.content.equals(o)) return true;
            current = current.next;
        }
        return false;
    }

    public boolean hasNext() {
        return !(current.next == start);
    }

    public T getNext() {
        if (!this.hasNext()) throw new ArrayIndexOutOfBoundsException();
        current = current.next;
        return current.content;
    }

    /**
     * same as getNext() but does not change current node, i.e. will return same Object if called twice
     * without changing current node(calling getNext(), resetNext() or clear()) between the two times.
     * @return next node's content
     */
    public T next() {
        if (!this.hasNext()) throw new ArrayIndexOutOfBoundsException();
        return current.next.content;
    }

    public void resetNext() {
        current = start;
    }
}
