package ru.academit.tortochakov.mylist;

import java.util.*;

public class MyList<T> implements List<T> {
    private int length;
    private Object[] items;

    public MyList(int capacity) {
        items = new Object[capacity];
    }

    @Override
    public Iterator<T> iterator() {
        return null;
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
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return null;
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
    public boolean add(T o) {
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
    public boolean addAll(Collection<? extends T> collection) {
        ensureCapacity(length + collection.size());
        System.arraycopy(collection.toArray(), 0, items, length, collection.size());
        length += collection.size();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
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
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        return (T) items[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T o) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        T temp = (T) items[index];
        items[index] = o;
        return temp;
    }

    @Override
    public void add(int index, T t) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        ensureCapacity(items.length * 2);
        System.arraycopy(items, index, items, index + 1, length - index);
        items[index] = t;
        length++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index >= length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        Object o = items[index];
        if (index < length - 1) {
            System.arraycopy(items, index + 1, items, index, length - index - 1);
        }
        length--;
        return (T) o;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i++) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > length) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("Индекс начала отрезка должен быть меньше конца");
        }
        Object[] list = new Object[toIndex - fromIndex];
        System.arraycopy(items, fromIndex, list, 0, toIndex - fromIndex);
        return Arrays.asList((T[]) list);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int k = 0;
        for (int i = 0; i < length + k; i++) {
            if (!collection.contains(items[i - k])) {
                remove(i - k);
                k++;
            }
        }
        return k != 0;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        int k = 0;
        for (int i = 0; i < length + k; i++) {
            if (collection.contains(items[i - k])) {
                remove(i - k);
                k++;
            }
        }
        return k != 0;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object o : collection) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> E[] toArray(E[] objects) {
        if (objects == null || objects.length < length) {
            objects = (E[]) new Object[length];
        } else if (objects.length > length) {
            objects[length] = null;
        }
        System.arraycopy(items, 0, objects, 0, length);
        return objects;
    }
}

