package com.wise.core;

import java.util.List;

public interface Repository<D> {

	D find(String key);
	
	List<D> search(Query query);
	
	void save(D domain);
	
	void remove(D domain);
}
