package ru.academit.tortochakov.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class HashTable<T> implements Collection<T> {
    private ArrayList[] table;
    private int length;

    @SuppressWarnings("unchecked")
    public HashTable(int initialCapacity) {
        table = new ArrayList[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            table[i] = new ArrayList();
        }
    }

    private int hashFunc(Object t) {
        return t.hashCode() % table.length;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return table[hashFunc(o)].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] toArray() {
        Object[] array = new Object[length];
        int k = 0;
        for (ArrayList aTable : table) {
            for (Object anATable : aTable) {
                array[k++] = anATable;
            }
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E> E[] toArray(E[] objects) {
        if (objects.length < length) {
            return (E[]) Arrays.copyOf(toArray(), length, objects.getClass());
        } else if (objects.length > length) {
            objects[length] = null;
        }
        System.arraycopy(toArray(), 0, objects, 0, length);
        return objects;
    }


    @Override
    @SuppressWarnings("unchecked")
    public boolean add(T t) {
        table[hashFunc(t)].add(t);
        length++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (table[hashFunc(o)].remove(o)) {
            length--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends T> c) {
        int currSize = size();
        for (T obj : c) {
            add(obj);
        }
        return currSize != size();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int currSize = size();
        for (Object obj : c) {
            remove(obj);
        }
        return currSize != size();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int currSize = size();
        for (ArrayList list : table) {
            int oldSize = list.size();
            list.retainAll(c);
            length -= oldSize - list.size();
        }
        return currSize != size();
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    @Override
    public void clear() {
        for (ArrayList list : table) {
            list.clear();
        }
        length = 0;
    }
}
