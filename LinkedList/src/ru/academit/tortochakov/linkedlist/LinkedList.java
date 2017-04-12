package ru.academit.tortochakov.linkedlist;

import java.util.*;

public class LinkedList<T> implements List<T>, Deque<T> {
    private class Node {
        private T data;
        private Node next;
        private Node prev;

        public Node(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public T getData() {
            return data;
        }

        public T setData(T newData) {
            T temp = data;
            data = newData;
            return temp;
        }

        public boolean hasNext() {
            return next != null;
        }

        public boolean hasPrev() {
            return prev != null;
        }

        public void link(Node next) {
            this.next = next;
            next.prev = this;
        }
    }

    private class Pair {
        private Node node;
        private int index;

        public Pair(Node node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    private Node head;
    private Node tail;
    private int size;


    public LinkedList(Collection<? extends T> collection) {
        for (T l : collection) {
            addLast(l);
        }
    }

    @Override
    public void addFirst(T t) {
        Node temp = new Node(t);
        if (head == null) {
            head = temp;
            tail = temp;
        } else {
            temp.link(head);
            head = temp;
        }
        size++;
    }

    @Override
    public void addLast(T t) {
        Node temp = new Node(t);
        if (head == null) {
            head = temp;
            tail = temp;

        } else {
            tail.link(temp);
            tail = temp;
        }
        size++;
    }

    public String toString() {
        if (head == null) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        Node temp = head;
        while (temp != null) {
            builder.append(temp.getData()).append(", ");
            temp = temp.getNext();
        }
        builder.setCharAt(builder.length() - 2, ']');
        return builder.substring(0, builder.length() - 1);
    }

    @Override
    public boolean offerFirst(T t) {
        addFirst(t);
        return true;
    }

    @Override
    public boolean offerLast(T t) {
        addLast(t);
        return true;
    }

    @Override
    public T removeFirst() {
        if (head == null) {
            return null;
        }
        T temp = head.getData();
        remove(head);
        return temp;
    }

    @Override
    public T removeLast() {
        if (tail == null) {
            return null;
        }
        T temp = tail.getData();
        remove(tail);
        return temp;
    }

    @Override
    public T pollFirst() {
        return removeFirst();
    }

    @Override
    public T pollLast() {
        return removeLast();
    }

    @Override
    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пустой");
        }
        return head.getData();
    }

    @Override
    public T getLast() {
        if (head == null) {
            throw new NoSuchElementException("Список пустой");
        }
        return tail.getData();
    }

    @Override
    public T peekFirst() {
        return getFirst();
    }

    @Override
    public T peekLast() {
        return getLast();
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        Pair nodeOfInterest = nodeWith(o);
        if (nodeOfInterest == null) {
            return false;
        }
        remove(nodeOfInterest.node);
        return true;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        Pair nodeOfInterest = lastNodeWith(o);
        if (nodeOfInterest == null) {
            return false;
        }
        remove(nodeOfInterest.node);
        return true;
    }

    @Override
    public boolean offer(T t) {
        addLast(t);
        return true;
    }

    @Override
    public T remove() {
        return removeFirst();
    }

    @Override
    public T poll() {
        return pollFirst();
    }

    @Override
    public T element() {
        return getFirst();
    }

    @Override
    public T peek() {
        return peekFirst();
    }

    @Override
    public void push(T t) {
        addFirst(t);
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    @Override
    public Iterator<T> descendingIterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node temp = head;
        int i = 0;
        while (temp != null) {
            array[i++] = temp.getData();
            temp = temp.getNext();
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    public <E> E[] toArray(E[] a) {
        int length = size();
        if (a.length < length) {
            return (E[]) Arrays.copyOf(toArray(), length, a.getClass());
        } else if (a.length > length) {
            a[length] = null;
        }
        System.arraycopy(toArray(), 0, a, 0, length);
        return a;
    }

    @Override
    public boolean add(T t) {
        addLast(t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Node temp = head;
        while (temp != null) {
            if (!c.contains(temp)) {
                return false;
            }
            temp = temp.getNext();
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }
        for (T l : c) {
            addLast(l);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (c.size() == 0) {
            return false;
        }

        LinkedList<T> newQueue = new LinkedList<T>(c);
        Node temp = getNodeByIndex(index);
        if (temp == head) {
            newQueue.tail.link(head);
            head = newQueue.head;
        } else if (temp == tail) {
            tail.link(newQueue.head);
            tail = newQueue.tail;
        } else {
            temp.getPrev().link(newQueue.head);
            newQueue.tail.link(temp);
        }
        size += c.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int currentSize = size;
        for (Object l : c) {
            Node temp = head;
            while (temp != null) {
                if (temp.getData().equals(l)) {
                    temp = remove(temp);
                } else {
                    temp = temp.getNext();
                }
            }
        }
        return currentSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int initSize = size();
        Node temp = head;
        while (temp != null) {
            if (!c.contains(temp.data)) {
                temp = remove(temp);
            } else {
                temp = temp.next;
            }
        }
        return initSize != size();
    }

    private Node remove(Node temp) {
        if (temp == null) {
            return null;
        }
        if (!head.hasNext()) {
            clear();
            return null;
        }
        if (temp == head) {
            head = head.getNext();
            head.setPrev(null);
            temp.setNext(null);
            temp.setPrev(null);
            size--;
            return head;
        } else if (temp == tail) {
            tail = tail.getPrev();
            tail.setNext(null);
            temp.setPrev(null);
            temp.setNext(null);
            size--;
            return null;
        }
        temp.getPrev().link(temp.getNext());
        size--;
        return temp.getNext();
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    private Node getNodeByIndex(int index) {
        Node temp;
        int count;
        if (size / 2 > index) {
            count = 0;
            temp = head;
            while (count != index) {
                count++;
                temp = temp.getNext();
            }
        } else {
            count = size - 1;
            temp = tail;
            while (count != index) {
                count--;
                temp = temp.getPrev();
            }
        }
        return temp;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return getNodeByIndex(index).getData();
    }

    @Override
    public T set(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        return getNodeByIndex(index).setData(element);
    }

    @Override
    public void add(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(element);
            return;
        } else if (index == size) {
            addLast(element);
            return;
        }
        Node temp = getNodeByIndex(index);
        Node temp2 = new Node(element);
        temp.getPrev().link(temp2);
        temp2.link(temp);
    }

    @Override
    public T remove(int index) {
        if (index >= size && index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы коллекции");
        }
        Node temp = getNodeByIndex(index);
        T data = temp.getData();
        remove(temp);
        return data;
    }

    @Override
    public int indexOf(Object o) {
        Pair nodeOfInterest = nodeWith(o);
        if (nodeOfInterest != null) {
            return nodeOfInterest.index;
        }
        return -1;
    }

    private Pair nodeWith(Object o) {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            if (o.equals(temp.getData())) {
                return new Pair(temp, count);
            }
            count++;
            temp = temp.getNext();
        }
        return null;
    }

    private Pair lastNodeWith(Object o) {
        int count = size - 1;
        Node temp = tail;
        while (temp != null) {
            if (o.equals(temp.getData())) {
                return new Pair(temp, count);
            }
            count--;
            temp = temp.getPrev();
        }
        return null;
    }

    @Override
    public int lastIndexOf(Object o) {
        Pair nodeOfInterest = lastNodeWith(o);
        if (nodeOfInterest != null) {
            return nodeOfInterest.index;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> subList(int fromIndex, int toIndex) {
        Node temp = head;
        Object[] list = new Object[toIndex - fromIndex];
        for (int i = 0; i < fromIndex; i++) {
            temp = temp.getNext();
        }
        for (int i = 0; i < list.length; i++) {
            list[i] = temp.getData();
            temp = temp.getNext();
        }
        return Arrays.asList((T[]) list);
    }
}
