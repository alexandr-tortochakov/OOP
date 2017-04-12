package ru.academit.tortochakov.mylist;

import java.util.*;

public class MyList<T> implements List<T> {
    private int length;
    private Object[] items;
    private int modificationCount;

    public MyList(int capacity) {
        items = new Object[capacity];
    }

    private class MyIterator<T> implements Iterator<T> {
        private int index = -1;
        private int modCount = modificationCount;

        @Override
        public boolean hasNext() {
            return index < length;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (modificationCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) items[++index];
        }
    }

    private class MyListIterator implements ListIterator<T> {
        private int startIndex = -1;
        private int endIndex = length;
        private int lastReturned = -1;
        private int lastAccessType;  // 1 for next, 2 for previous
        private int modCount = modificationCount;

        @Override
        public boolean hasNext() {
            return startIndex < length - 1;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (modificationCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = ++startIndex;
            lastAccessType = 1;
            return (T) items[lastReturned];
        }

        @Override
        public boolean hasPrevious() {
            return endIndex > 0;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T previous() {
            if (modificationCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            lastReturned = --endIndex;
            lastAccessType = 2;
            return (T) items[lastReturned];
        }

        @Override
        public int nextIndex() {
            return ++startIndex;
        }

        @Override
        public int previousIndex() {
            return --endIndex;
        }

        @Override
        public void remove() {
            MyList.this.remove(lastReturned);
        }

        @Override
        public void set(T t) {
            items[lastReturned] = t;
        }

        @Override
        public void add(T t) {
            if (lastAccessType == 1) {
                MyList.this.add(lastReturned - 1, t);
            } else {
                MyList.this.add(lastReturned + 1, t);
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>();
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
        return new MyListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        if (i >= length || i < 0 ) {
            throw new IndexOutOfBoundsException();
        }
        ListIterator<T> iterator = new MyListIterator();
        for (int j = 0; j < i; j++) {
            iterator.next();
        }
        return iterator;
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
        modificationCount++;
    }

    public void trimToSize() {
        if (items.length == length) {
            return;
        }
        Object[] old = items;
        items = new Object[length];
        System.arraycopy(old, 0, items, 0, length);
        modificationCount++;
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
        ensureCapacity(length * 2);
        items[length] = o;
        length++;
        modificationCount++;
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
        modificationCount++;
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        ensureCapacity(length + collection.size());
        System.arraycopy(collection.toArray(), 0, items, length, collection.size());
        length += collection.size();
        modificationCount++;
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
        modificationCount++;
        return true;
    }

    @Override
    public void clear() {
        length = 0;
        modificationCount++;
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
        modificationCount++;
        return temp;
    }

    @Override
    public void add(int index, T t) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Выход за границы списка");
        }
        ensureCapacity(length * 2);
        System.arraycopy(items, index, items, index + 1, length - index);
        items[index] = t;
        length++;
        modificationCount++;
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
        modificationCount++;
        return (T) o;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; i--) {
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
        if (objects.length < length) {
            return (E[]) Arrays.copyOf(items, length, objects.getClass());
        } else if (objects.length > length) {
            objects[length] = null;
        }
        System.arraycopy(items, 0, objects, 0, length);
        return objects;
    }
}

