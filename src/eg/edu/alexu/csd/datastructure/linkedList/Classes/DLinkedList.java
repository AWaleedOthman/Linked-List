package eg.edu.alexu.csd.datastructure.linkedList.Classes;
import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.ILinkedList;

//implement iterable/iterator
//

public class DLinkedList<T> implements ILinkedList {

	private int size = 0;
	private Node head;
	private Node tail;
	final Class<T> typeParameterClass;
	
	private class Node{
		T data;
		Node prev;
		Node next;
	}
	
	private void validateIndex(int index) {
		if(index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException("index out of range");
		}
	}
	
	public DLinkedList(Class<T> typeParameterClass) { //initialise DLL with dummy nodes
		head = new Node();
		head.prev = null;
		head.data = null;
		tail = new Node();
		tail.prev = head;
		tail.next = null;
		tail.data = null;
		head.next = tail;
		this.typeParameterClass = typeParameterClass;
	}
	
	@Override
	public void add(int index, Object element){
		if(index < 0 || index > size) { //if index == size, it's fine because I'll be adding a new node
			throw new ArrayIndexOutOfBoundsException();
		}
		Node newNode = new Node();
		try {
			newNode.data = typeParameterClass.cast(element);
		}catch (ClassCastException e) {
			throw new IllegalArgumentException("Changed the type of DLL after declaration");
		}
		int currentIndex;
		Node currentNode;
		if(index > size/2) {
			currentNode = tail.prev;
			currentIndex = size-1;
			while(index < currentIndex) {
				currentNode = currentNode.next;
				currentIndex--;
			}
		}else {
			currentNode = head.next;
			currentIndex = 0;
			while(index > currentIndex) {
				currentNode = currentNode.next;
				currentIndex++;
			}
		}
		newNode.prev = currentNode.prev;
		newNode.next = currentNode;
		currentNode.prev = newNode;
		newNode.prev.next = newNode;
		size++;
	}
	
	
	@Override
	public void add(Object element) {
		Node newNode = new Node();
		if(typeParameterClass.isInstance(element)) {
			newNode.data = typeParameterClass.cast(element);
		}
		else {
			throw new IllegalArgumentException("Changed the type of DLL after declaration");
		}
		/*try { DOESNT WORK BECAUSE OF TYPE ERASURE
			newNode.data = (T) element;
		}catch(ClassCastException e) {
			throw new IllegalArgumentException("Changed the type of DLL after declaration");
		}*/
		newNode.next = tail;
		newNode.prev = tail.prev;
		tail.prev = newNode;
		newNode.prev.next = newNode;
		size++;
	}

	@Override
	public T get(int index) {
		validateIndex(index);
		//speeding up the get method a little
		int currentIndex;
		Node currentNode;
		if(index > size/2) {
			currentIndex = size-1;
			currentNode = tail.prev;
			while(currentIndex > index) {
				currentNode = currentNode.prev;
				currentIndex--;
			}
		}else {
			currentIndex = 0;
			currentNode = head.next;
			while(currentIndex < index) {
				currentNode = currentNode.next;
				currentIndex++;
			}
		}
		return currentNode.data;
	}

	@Override
	public void set(int index, Object element) {
		validateIndex(index);
		int currentIndex;
		Node currentNode;
		if(index > size/2) {//more towards the tail 
			currentIndex = size-1;
			currentNode = tail.prev;
			while(currentIndex > index) {
				currentIndex--;
				currentNode = currentNode.prev;
			}
		}
		else {
			currentIndex = 0;
			currentNode = head.next;
			while(index > currentIndex) {
				currentIndex++;
				currentNode = currentNode.next;
			}
		}
		try {
			currentNode.data = typeParameterClass.cast(element);
		}catch(ClassCastException e) {
			throw new IllegalArgumentException("Changed the type of DLL after declaration");
		}
	}

	@Override
	public void clear() {
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void remove(int index) {
		validateIndex(index);
		int currentIndex;
		Node currentNode;
		if(index > size/2) {
			currentNode = tail.prev;
			currentIndex = size-1;
			while(currentIndex > index) {
				currentNode = currentNode.prev;
				currentIndex--;
			}
		}else {
			currentNode = head.next;
			currentIndex = 0;
			while(index > currentIndex) {
				currentIndex++;
				currentNode = currentNode.next;
			}
		}
		currentNode.prev.next = currentNode.next;
		currentNode.next.prev = currentNode.prev;
		size--;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public DLinkedList<T> sublist(int fromIndex, int toIndex) {
		validateIndex(fromIndex);
		validateIndex(toIndex);
		if(fromIndex > toIndex) {
			throw new IllegalArgumentException("fromIndex is larger than toIndex");
		}
		DLinkedList<T> newList = new DLinkedList<T>(typeParameterClass);
		int currentIndex = 0;
		Node currentNode = head.next;
		while(currentIndex <= toIndex) {//creating a "deep" copy of each node
			if(currentIndex < fromIndex) {
				currentNode = currentNode.next;
				currentIndex++;
				continue;
			}
			newList.add(currentNode.data);
			currentNode = currentNode.next;
			currentIndex++;
		}
		return newList;
	}

	@Override
	public boolean contains(Object o) {
		Node currentNode = head.next;
		while(currentNode != tail) {
			if(currentNode.data.equals(o)) return true;
			currentNode = currentNode.next;
		}
		return false;
	}
	
}
