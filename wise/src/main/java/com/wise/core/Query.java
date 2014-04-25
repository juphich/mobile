package com.wise.core;

public interface Query {

	Object key(String name);

	void q(String key, Object value);
}
