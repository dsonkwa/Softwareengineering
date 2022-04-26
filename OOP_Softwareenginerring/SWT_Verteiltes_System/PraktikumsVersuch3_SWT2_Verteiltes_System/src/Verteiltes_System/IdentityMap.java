package Verteiltes_System;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 
 * @author Christian Weidauer, Hochschule Bochum
 *
 * @param <K> type of the unique key
 * @param <T> type of the object
 */
public class IdentityMap<K, T> {

	protected Map<K,T> mapper = Collections.synchronizedMap(new HashMap<>());
	//protected Map<K,T> map = new HashMap<>();
	protected Function<T, K> identity;

	/**
	 * Constructor for IdentityMap to define the identity function
	 * @param identity function to get the unique key of the object
	 */
	protected IdentityMap(Function<T, K> identity)  {
		Objects.requireNonNull(identity);
		this.identity = identity;
	}
	
	public void setIdentity(Function<T, K> identity) {
		this.identity= identity;
	}

	public void open() {}

	public void close() {}


	public T get(K key) {
		return mapper.get(key);
	}

	public void add(T element) {
		Objects.requireNonNull(element, "no element to add available");
		if (mapper.containsValue(element)) throw new IllegalStateException("element " + element + " already persisted");
		K key = identity.apply(element);
		if (mapper.containsKey(key)) throw new IllegalStateException("element key \""+ key + "\" not unique");;
		mapper.put(key, element);
	}

	public T update(T element) {
		Objects.requireNonNull(element, "no element to update available");
		K key = identity.apply(element);
		return mapper.put(key, element);
	}

	public T remove(K key) {
		Objects.requireNonNull(key, "no key for element to remove available");
		return mapper.remove(key);
	}

	public boolean removeByObject(T element) {
		Objects.requireNonNull(element, "no element to remove available");
		K key = identity.apply(element);
		return mapper.remove(key, element);
	}

	public int size() {
		return mapper.size();
	}

	public List<T> getAll() {
		return mapper.values().stream().collect(Collectors.toCollection(ArrayList::new));
	}

}
