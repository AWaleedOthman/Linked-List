package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.ILinkedList;

public class SLinkedList implements ILinkedList {

    private Node start;
    private Node end;
    private int size = 0;

    public SLinkedList() {
        /*
        Dummy Start Node
         */
        start = new Node();
        start.content = null;
        end = start;
    }

    private class Node {
        private Object content;
        private Node next;

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public void add(Object element) {
        end.next = new Node();
        end = end.next;
        end.content = element;
        size++;
    }

    @Override
    public Object get(int index) throws ArrayIndexOutOfBoundsException {
        if (index > size -1 || index < 0) throw new ArrayIndexOutOfBoundsException();   //Out of boundary
        Node current = start.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.content;
    }

    @Override
    public void set(int index, Object element) {

    }

    @Override
    public void clear() {
        start = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }
}
