package com.company;

public class Pair<K extends Comparable,V> implements Comparable{
    public K Key;
    public V Value;

    public Pair(K key, V value){
        this.Key = key;
        this.Value = value;
    }

    @Override
    public int compareTo(Object o) {
        Pair<K, V> other = (Pair<K, V>)o;
        return this.Key.compareTo(other.Key);
    }
}
