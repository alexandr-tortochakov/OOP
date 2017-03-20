package ru.academit.tortochakov.mylist;

import java.util.*;

public class MyList implements List {
    private int length;
    private Object[] items;

    public MyList(int size) {
        length = size;
        items = new Object[size];
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
        for (int i = 0; i < length; i++) {
            if (o == null ? get(i) == null : o.equals(get(i))) {
                return true;
            }
        }
        return false;
    }

    public void ensureCapacity(int minCapacity) {
        while (items.length < minCapacity) {
            increaseCapacity();
        }
    }

    public void trimToSize() {
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
        builder.append(items[length - 1]).append("}");
        return builder.toString();
    }

    @Override
    public Object[] toArray() {
        Object[] temp = new Object[length];
        return toArray(temp);
    }

    @Override
    public boolean add(Object o) {
        if (length >= items.length) {
            increaseCapacity();
        }
        items[length] = o;
        length++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < length; i++) {
            if (o == null ? get(i) == null : o.equals(get(i))) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection collection) {
        while (items.length < collection.size() + length) {
            increaseCapacity();
        }
        System.arraycopy(collection.toArray(), 0, items, length, collection.size());
        length += collection.size();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection collection) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы массива");
        }
        while (items.length < collection.size() + length) {
            increaseCapacity();
        }
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
            throw new IndexOutOfBoundsException("Выход за пределы массива");
        }
        return items[index];
    }

    @Override
    public Object set(int index, Object o) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы массива");
        }
        return items[index] = o;
    }

    @Override
    public void add(int index, Object o) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы массива");
        }
        if (length >= items.length) {
            increaseCapacity();
        }
        System.arraycopy(items, index, items, index + 1, length - index);
        items[index] = o;
        length++;
    }

    private void increaseCapacity() {
        Object[] old = items;
        items = new Object[old.length * 2];
        System.arraycopy(old, 0, items, 0, old.length);
    }

    @Override
    public Object remove(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за пределы массива");
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
            if (o == null ? get(i) == null : o.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        for (int i = 0; i < length; i++) {
            if (o == null ? get(i) == null : o.equals(get(i))) {
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
            throw new IndexOutOfBoundsException("Выход за пределы массива");
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
        for (Object o: collection) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] objects) {
        if (objects.length < length) {
            objects = new Object[length];
        }
        System.arraycopy(items, 0, objects, 0, length);
        return objects;
    }
}