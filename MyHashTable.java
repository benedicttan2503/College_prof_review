package finalproject;
//Benedict Tan 261102994
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MyHashTable<K,V> implements Iterable<MyPair<K,V>>{
	// num of entries to the table
	private int size;
	// num of buckets 
	private int capacity = 16;
	// load factor needed to check for rehashing 
	private static final double MAX_LOAD_FACTOR = 0.75;
	// ArrayList of buckets. Each bucket is a LinkedList of HashPair
	private ArrayList<LinkedList<MyPair<K,V>>> buckets; 


	// constructors
	public MyHashTable() {
		// ADD YOUR CODE BELOW THIS
		this.size = 0;
		this.capacity = 16;
		buckets = new ArrayList<LinkedList<MyPair<K,V>>>(capacity);
		int i = 0;
		while (i < capacity){
			buckets.add(new LinkedList<MyPair<K,V>>());
			i++;
		//ADD YOUR CODE ABOVE THIS
	}}

	public MyHashTable(int initialCapacity) {
		// ADD YOUR CODE BELOW THIS
		this.size = 0;
		this.capacity = initialCapacity;
		buckets = new ArrayList<LinkedList<MyPair<K,V>>>(capacity);
		int i = 0;
		while (i < capacity){
			buckets.add(new LinkedList<MyPair<K,V>>());
			i++;
		}
		//ADD YOUR CODE ABOVE THIS
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int numBuckets() {
		return this.capacity;
	}

	/**
	 * Returns the buckets variable. Useful for testing  purposes.
	 */
	public ArrayList<LinkedList< MyPair<K,V> > > getBuckets(){
		return this.buckets;
	}

	/**
	 * Given a key, return the bucket position for the key. 
	 */
	public int hashFunction(K key) {
		int hashValue = Math.abs(key.hashCode())%this.capacity;
		return hashValue;
	}

	/**
	 * Takes a key and a value as input and adds the corresponding HashPair
	 * to this HashTable. Expected average run time  O(1)
	 */
	public V put(K key, V value) {
		//  ADD YOUR CODE BELOW HERE
		LinkedList<MyPair<K,V>> bucketAtIndex = buckets.get(hashFunction(key));
		for (MyPair<K,V> myPair : bucketAtIndex) {
				if (key.equals(myPair.getKey())) {
					if (myPair.getValue() != null) {
						V old = myPair.getValue();
						myPair.setValue(value);
						return old;
					}
					myPair.setValue(value);
				}}
		bucketAtIndex.add(new MyPair<K,V>(key, value));
		buckets.set(hashFunction(key), bucketAtIndex);
		this.size ++;
		if ((size/capacity) > MAX_LOAD_FACTOR){
			rehash();
		}
		return null;

		//  ADD YOUR CODE ABOVE HERE
	}


	/**
	 * Get the value corresponding to key. Expected average runtime O(1)
	 */

	public V get(K key) {
		//ADD YOUR CODE BELOW HERE
		LinkedList<MyPair<K,V>> bucketAtIndex = buckets.get(hashFunction(key));
		for (MyPair<K,V> myPair : bucketAtIndex) {
				if (key.equals(myPair.getKey())) {
					return myPair.getValue();
				}}
		return null;

		//ADD YOUR CODE ABOVE HERE
	}

	/**
	 * Remove the HashPair corresponding to key . Expected average runtime O(1) 
	 */
	public V remove(K key) {
		LinkedList<MyPair<K, V>> bucketAtIndex = buckets.get(hashFunction(key));
		Iterator<MyPair<K, V>> ite = bucketAtIndex.iterator();
		while (ite.hasNext()) {
			MyPair<K, V> myPair = ite.next();
			if (key.equals(myPair.getKey())) {
				V getValue = myPair.getValue();
				ite.remove();
				this.size = size - 1;
				return getValue;
			}
		}
		return null;
	}


	/** 
	 * Method to double the size of the hashtable if load factor increases
	 * beyond MAX_LOAD_FACTOR.
	 * Made public for ease of testing.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */
	public void rehash() {
		//ADD YOUR CODE BELOW HERE
		this.capacity *= 2;
		ArrayList<LinkedList<MyPair<K,V>>> anotherBuckets = new ArrayList<>(capacity);
		int i = 0;
		while (i < capacity){
			anotherBuckets.add(new LinkedList<MyPair<K,V>>());
			i++;}
		for (LinkedList<MyPair<K,V>> linkedList : buckets){
			for(MyPair<K,V> myPair : linkedList){
			anotherBuckets.get(hashFunction(myPair.getKey())).add(myPair);
		}} buckets = anotherBuckets;
		//ADD YOUR CODE ABOVE HERE
	}


	/**
	 * Return a list of all the keys present in this hashtable.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */

	public ArrayList<K> getKeySet() {
		//ADD YOUR CODE BELOW HERE
		ArrayList<K> allKeys = new ArrayList<>(size);
		for (LinkedList<MyPair<K,V>> linkedList : buckets){
			for(MyPair<K,V> myPair : linkedList){
				allKeys.add(myPair.getKey());
			}}
		return allKeys;

		//ADD YOUR CODE ABOVE HERE
	}

	/**
	 * Returns an ArrayList of unique values present in this hashtable.
	 * Expected average runtime is O(m) where m is the number of buckets
	 */
	public ArrayList<V> getValueSet() {
		//ADD CODE BELOW HERE
		HashSet<V> uniqueKeys = new HashSet<>(size);
		for (LinkedList<MyPair<K,V>> linkedList : buckets){
			for(MyPair<K,V> myPair : linkedList){
				uniqueKeys.add(myPair.getValue());}
			}
		ArrayList<V> uniqueArray = new ArrayList<>(uniqueKeys);
		return uniqueArray;
		//ADD CODE ABOVE HERE
	}


	/**
	 * Returns an ArrayList of all the key-value pairs present in this hashtable.
	 * Expected average runtime is O(m) where m is the number of buckets
	 */
	public ArrayList<MyPair<K, V>> getEntries() {
		//ADD CODE BELOW HERE
		ArrayList<MyPair<K, V>> allEntries = new ArrayList<MyPair<K, V>>(size);
		for (LinkedList<MyPair<K,V>> linkedList : buckets){
			for(MyPair<K,V> myPair : linkedList){
				allEntries.add(myPair);
			}}
		return allEntries;
		//ADD CODE ABOVE HERE
	}

	
	
	@Override
	public MyHashIterator iterator() {
		return new MyHashIterator();
	}


	private class MyHashIterator implements Iterator<MyPair<K,V>> {
		private int index = 0;
		private Iterator<MyPair<K, V>> iterator = null;

		// Constructor
		private MyHashIterator() {
			while (index < capacity && buckets.get(index) == null) {
				index++;
			}
			if (index > 0) {
				this.iterator = buckets.get(index).iterator();
			}
		}

		// hasNext() method
		@Override
		public boolean hasNext() {
			if (index > 0 && iterator.hasNext()) {
				return true;
			} else {
				while (++index < capacity) {
					if (buckets.get(index).size() > 0) {
						iterator = buckets.get(index).iterator();
						return true;}}
				return false;}
		}

		@Override
		public MyPair<K,V> next() {
			if (hasNext()) {
				return iterator.next();
	}
		return null;}


}}
