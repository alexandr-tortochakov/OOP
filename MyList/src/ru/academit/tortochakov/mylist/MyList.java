package ru.academit.tortochakov.mylist;

import java.util.*;

public class MyList implements List {
    private int length;
    private Object[] items;


    public MyList(int capacity) {
        items = new Object[capacity];
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
        return indexOf(o) >= 0;
    }

    public void ensureCapacity(int minCapacity) {
        if (items.length >= minCapacity) {
            return;
        }
        Object[] old = items;
        items = new Object[minCapacity];
        System.arraycopy(old, 0, items, 0, old.length);
    }

    public void trimToSize() {
        if (items.length == length) {
            return;
        }
        Object[] old = items;
        items = new Object[length];
        System.arraycopy(old, 0, items, 0, length);
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        for (int i = 0; i < length - 1; i++) {
            builder.append(items[i]).append(", ");
        }
        if (length - 1 >= 0) {
            builder.append(items[length - 1]);
        }
        builder.append("}");
        return builder.toString();
    }

    @Override
    public Object[] toArray() {
        Object[] temp = new Object[length];
        return toArray(temp);
    }

    @Override
    public boolean add(Object o) {
        ensureCapacity(items.length * 2);
        items[length] = o;
        length++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(o, items[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection collection) {
        ensureCapacity(length + collection.size());
        System.arraycopy(collection.toArray(), 0, items, length, collection.size());
        length += collection.size();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection collection) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        ensureCapacity(length + collection.size());
        System.arraycopy(items, index, items, index + collection.size(), length - index);
        System.arraycopy(collection.toArray(), 0, items, index, collection.size());
        length += collection.size();
        return true;
    }

    @Override
    public void clear() {
        length = 0;
    }

    @Override
    public Object get(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        return items[index];
    }

    @Override
    public Object set(int index, Object o) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        return items[index] = o;
    }

    @Override
    public void add(int index, Object o) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        ensureCapacity(items.length * 2);
        System.arraycopy(items, index, items, index + 1, length - index);
        items[index] = o;
        length++;
    }

    @Override
    public Object remove(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        Object o = items[index];
        if (index < length - 1) {
            System.arraycopy(items, index + 1, items, index, length - index - 1);
        }
        length--;
        return o;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; i++) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        for (int i = 0; i < length; i++) {
            if (Objects.equals(o, items[i])) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int i) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > length) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("Индекс начала отрезка должен быть меньше конца");
        }
        Object[] list = new Object[toIndex - fromIndex];
        System.arraycopy(items, fromIndex, list, 0, toIndex - fromIndex);
        return Arrays.asList(list);
    }

    @Override
    public boolean retainAll(Collection collection) {
        int k = 0;
        for (int i = 0; i < length + k; i++) {
            if (!collection.contains(items[i - k])) {
                remove(i - k);
                k++;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection collection) {
        int k = 0;
        for (int i = 0; i < length + k; i++) {
            if (collection.contains(items[i - k])) {
                remove(i - k);
                k++;
            }
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection collection) {
        for (Object o : collection) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        if (objects == null || objects.length < length) {
            objects = new Object[length];
        }
        System.arraycopy(items, 0, objects, 0, length);
        return objects;
    }
}
