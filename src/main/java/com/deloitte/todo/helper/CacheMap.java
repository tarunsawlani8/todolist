package com.deloitte.todo.helper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheMap {

	private static Map<String, Object> cacheMap = new ConcurrentHashMap<>();

	public static void addEntry(String key, Object value) {
		cacheMap.put(key, value);
	}

	public static synchronized void deleteEntry(String key) {
		if (cacheMap.containsKey(key)) {
			cacheMap.remove(key);

		}
	}

	public static Object readEntry(String key) {
		if (cacheMap.containsKey(key)) {
			return cacheMap.get(key);
		}
		return null;
	}

}
