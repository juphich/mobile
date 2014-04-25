package com.wise.core;

import java.util.HashMap;
import java.util.Map;

public class GenericQuery implements Query {

	private Map<String, Object> keyMap = new HashMap<>();
	
	@Override
	public Object key(String name) {
		return keyMap.get(name);
	}

	@Override
	public void q(String key, Object value) {
		keyMap.put(key, value);
	}
}
