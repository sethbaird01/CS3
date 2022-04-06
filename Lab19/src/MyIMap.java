public interface MyIMap<K, V> 

{

	/**
	 * Add the key/value pair to the table
	 * @return the value previously associated with key, or null
	 */
	V put(K key, V value);

	/**
	 * perform a table lookup
	 * @return value mapped to this key
	 */
	V get(K key);

	/** return the current number of key/value pairs in the map */
	int size();
	
	/**
	 * Remove supplied key
	 * @return value mapped to supplied key, or null
	 */
	V remove(K key);

}
