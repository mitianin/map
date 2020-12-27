package com.company;

import java.util.Objects;

public class MyMap<K, V> {

    private MySet<Pair<K, V>> pairSet = new MySet<>();


    public static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return key.equals(pair.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }


    void put(K key, V value) {
        pairSet.add(new Pair<>(key, value));
    }


    public int size() {
        return pairSet.size();
    }


    public boolean containsKey(K key) {
        Pair<K, V> pair = new Pair<>(key, null);
        return pairSet.contains(pair);
    }


    public V get(K key) {
        Pair<K, V> pair = new Pair<>(key, null);

        for (Pair<K, V> temp : pairSet) {
            if (temp.equals(pair)) {
                return temp.value;
            }
        }

        return null;
    }

    public MySet<Pair<K, V>> pairSet() {
        return pairSet;
    }


    @Override
    public String toString() {
        return pairSet.toString();
    }
}
