package ru.academit.tortochakov.linkedlist;

import java.util.*;

public class LinkedList<T> implements List<T>, Deque<T> {
    private LinkedList<T> head;
    private LinkedList<T> tail;
    private T data;
    private LinkedList<T> next;
    private LinkedList<T> prev;

    public LinkedList() {
    }

    public LinkedList(Collection<? extends T> collection) {
        for (T l : collection) {
            addLast(l);
        }
    }

    @Override
    public void addFirst(T t) {
        LinkedList<T> temp = new LinkedList<T>();
        temp.data = t;
        if (head == null) {
            head = temp;
            tail = temp;
            head.next = tail;
            tail.prev = head;
            return;
        }
        head.prev = temp;
        temp.next = head;
        head = temp;
    }

    @Override
    public void addLast(T t) {
        LinkedList<T> temp = new LinkedList<>();
        temp.data = t;
        if (head == null) {
            head = temp;
            tail = temp;
            return;
        }
        if (head == tail) {
            tail = temp;
           link(head, tail);
            return;
        }
        link(tail,temp);
        tail = temp;
    }

    public String toString() {
        if (head == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(head.data).append(", ");
        LinkedList<T> temp = head;
        while (temp.next != null) {
            builder.append(temp.next.data).append(", ");
            temp = temp.next;
        }
        return builder.substring(0, builder.length() - 2);
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
        T temp = head.data;
        remove(head);
        return temp;
    }

    @Override
    public T removeLast() {
        if (tail == null) {
            return null;
        }
        T temp = tail.data;
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
        return head.data;
    }

    @Override
    public T getLast() {
        if (head == null) {
            throw new NoSuchElementException("Список пустой");
        }
        return tail.data;
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
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        int index = lastIndexOf(o);
        if (index == -1) {
            return false;
        }
        remove(index);
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
        int count = 0;
        LinkedList<T> temp = head;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        if (indexOf(o) == -1) {
            return false;
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        LinkedList<T> temp = head;
        int i = 0;
        while (temp != null) {
            array[i++] = temp.data;
            temp = temp.next;
        }
        return array;
    }

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
        addFirst(t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        LinkedList<T> temp = head;
        while (temp != null) {
            if (!c.contains(temp)) {
                return false;
            }
            temp = temp.next;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T l : c) {
            addLast(l);
        }
        return true;
    }

    private void link(LinkedList<T> first, LinkedList<T> second) {
        first.next = second;
        second.prev = first;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        LinkedList<T> newQueue = new LinkedList<T>(c);
        LinkedList<T> temp = getByIndex(index);
        if (temp == head) {
            link(newQueue.tail, head);
            head = newQueue.head;
            return true;
        }
        if (temp == tail) {
            link(tail, newQueue.head);
            tail = newQueue.tail;
            return true;
        }
        link(temp.prev, newQueue.head);
        link(newQueue.tail, temp);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changeFlag = false;
        for (Object l : c) {
            boolean wasRemoved = true;
            while (wasRemoved) {
                wasRemoved = removeFirstOccurrence(l);
                changeFlag = wasRemoved || changeFlag;
            }
        }
        return changeFlag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int initSize = size();
        LinkedList<T> temp = head;
        while (temp != null) {
            if (!c.contains(temp.data)) {
                temp = remove(temp);
            } else {
                temp = temp.next;
            }
        }
        return initSize != size();
    }

    private LinkedList<T> remove(LinkedList<T> temp) {
        if (temp == null) {
            return null;
        }
        if (head.next == null) {
            clear();
            return null;
        }
        if (temp == head) {
            head.next.prev = null;
            head = head.next;
            temp.next = null;
            return head;
        } else if (temp == tail) {
            tail.prev.next = null;
            tail = tail.prev;
            temp.prev = null;
            return null;
        }
        link(temp.prev, temp.next);
        return temp.next;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    private LinkedList<T> getByIndex(int index) {
        int count = 0;
        LinkedList<T> temp = head;
        while (count != index) {
            count++;
            temp = temp.next;
        }
        return temp;
    }

    @Override
    public T get(int index) {
        return getByIndex(index).data;
    }

    @Override
    public T set(int index, T element) {
        LinkedList<T> temp = getByIndex(index);
        T temp2 = temp.data;
        temp.data = element;
        return temp2;
    }

    @Override
    public void add(int index, T element) {
        if (index == 0) {
            addFirst(element);
            return;
        } else if (index == size()) {
            addLast(element);
            return;
        }
        LinkedList<T> temp = getByIndex(index);
        LinkedList<T> temp2 = new LinkedList<>();
        temp2.data = element;
        link(temp.prev, temp2);
        link(temp2, temp);
    }

    @Override
    public T remove(int index) {
        if (index >= size() && index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы коллекции");
        }
        LinkedList<T> temp = getByIndex(index);
        T data = temp.data;
        remove(temp);
        return data;
    }

    @Override
    public int indexOf(Object o) {
        int count = 0;
        LinkedList<T> temp = head;
        while (temp != null) {
            if (o.equals(temp.data)) {
                return count;
            }
            count++;
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int count = size() - 1;
        LinkedList<T> temp = tail;
        while (temp != null) {
            if (o.equals(temp.data)) {
                return count;
            }
            count--;
            temp = temp.prev;
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
    public List<T> subList(int fromIndex, int toIndex) {
        LinkedList<T> temp = head;
        Object[] list = new Object[toIndex - fromIndex];
        for (int i = 0; i < fromIndex; i++) {
            temp = temp.next;
        }
        for (int i = 0; i < list.length; i++) {
            list[i] = temp.data;
            temp = temp.next;
        }
        return Arrays.asList((T[]) list);
    }
}
