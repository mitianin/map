package com.company;

import java.util.Arrays;
import java.util.Iterator;

public class MySet<T> implements Iterable<T> {
    private Node[] array;
    private int size = 0;

    private static class Node {
        Object value;
        Node next;

        public Node(Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return (String) value;
        }
    }

    public MySet(int size) {
        array = new Node[size];
    }

    public MySet() {
        this(16);
    }

    void add(T value) {
        int index = getIndex(value);

        Node toAdd = new Node(value);

        if (array[index] == null) {
            array[index] = toAdd;
        } else {
            Node prev = null;

            for (Node cur = array[index]; cur != null; cur = cur.next) {
                if (cur.value.equals(value)) {
                    cur.value = value;
                    return;
                }
                prev = cur;
            }

            prev.next = toAdd;
        }
        ++size;

    }

    private int getIndex(T value) {
        int hash = Math.abs(value.hashCode());
        return hash % array.length;
    }


//    public T get(T value) {
//        for (Node cur = array[getIndex(value)]; cur != null; cur = cur.next) {
//            if (cur.value.equals(value)) return (T) cur.value;
//        }
//        return null;
//    }

    public int size() {
        return size;
    }

    public boolean contains(T value) {
        for (Node cur = array[getIndex(value)]; cur != null; cur = cur.next) {
            if (cur.value.equals(value)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int bucketIndex = -1;
            private Node cur = null;

            @Override
            public boolean hasNext() {
                if (cur != null && cur.next != null) {
                    cur = cur.next;
                    return true;
                }

                for (bucketIndex++; bucketIndex < array.length; bucketIndex++) {
                    if (array[bucketIndex] != null) {
                        cur = array[bucketIndex];
                        return true;
                    }
                }

                return false;
            }

            @Override
            public T next() {
                return (T) cur.value;
            }
        };

    }

    public Object[] toArray() {
        Object[] ar = new Object[size];
        int i = 0;

        for (T t : this) {
            ar[i] = t;
            i++;
        }

        return ar;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
