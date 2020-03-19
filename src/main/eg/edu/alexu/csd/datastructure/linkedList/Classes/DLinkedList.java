package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.ILinkedList;

import java.util.Iterator;

public class DLinkedList<T> implements ILinkedList, Iterable<T> { //a DLL is an iterable for which iterators could be used, it's not an iterator itself

	private int size = 0;
	private Node head;
	private Node tail;
	final Class<T> typeParameterClass;
	
	private class Node{
		T data;
		Node prev;
		Node next;
	}
	
	private class Itr implements Iterator<T>{
		
		private Node currentNode;
		private int arg;
		Itr(){ 
			this(0);
		}
		
		Itr(int arg){
			this.arg = arg;
			switch(arg) {
			case 0:
				currentNode = head.next;
				break;
			case 1:
				currentNode = tail.prev;
				break;
			default:
				throw new IllegalArgumentException();
			}
		}
		@Override
		public boolean hasNext() {
			if(arg == 0)
				return currentNode != tail;
			else 
				return currentNode != head;
		}

		@Override
		public T next() {
			T data = currentNode.data;
			if(arg == 0)
				currentNode = currentNode.next;
			else
				currentNode = currentNode.prev;
			return data;
		}
		
		@Override
		public void remove() {
			Node pastNode;
			if(arg == 0) {//currentNode becomes the next node
				pastNode = currentNode.prev;
			}
			else//currentNode becomes the previous one
			{
				pastNode = currentNode.next;
			}
			pastNode.prev.next = pastNode.next;
			pastNode.next.prev = pastNode.prev;
			size--;
		}

		//the above functions are defined in the interface so they can be used when returning an Itr object that is caster to Iterator
		//but the next ones can't so this is a failed idea.
		/*public boolean hasPrev() {
			return currentNode != head.next;
		}
		
		public T prev() {
			T data = currentNode.data;
			currentNode = currentNode.prev;
			return data;
		}*/
		
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
			currentNode = tail;
			currentIndex = size;
			while(index < currentIndex) {
				currentNode = currentNode.prev;
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
		DLinkedList<T> newList = new DLinkedList<>(typeParameterClass);
		int currentIndex;
		Node currentNode;
		if(fromIndex <= toIndex) {
			currentIndex=0;
			currentNode = head.next;
			while(currentIndex<fromIndex) { //find the starting point
				currentNode = currentNode.next;
				currentIndex++;
			}
			while(currentIndex <= toIndex) {//creating a "deep" copy of each node
				newList.add(currentNode.data);
				currentNode = currentNode.next;
				currentIndex++;
			}
		}
		else {
			currentIndex=size-1;
			currentNode = tail.prev;
			while(currentIndex > fromIndex) {
				currentNode = currentNode.prev;
				currentIndex--;
			}
			while(currentIndex >= toIndex) {//creating a "deep" copy of each node
				newList.add(currentNode.data);				
				currentNode = currentNode.prev;
				currentIndex--;
			}
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
	
	/*
	 * @param arg 
	 * 	0 --> start from head
	 *  1 --> start from tail */
	public Iterator<T> iterator(int arg) {//iterator for custom use, whether up or down
		return new Itr(arg);
	}
	
	@Override
	public Iterator<T> iterator(){ //iterator for for-each loop
		return new Itr();
	}

	
}
