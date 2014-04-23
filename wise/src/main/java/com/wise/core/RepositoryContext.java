package com.wise.core;

import java.util.concurrent.ConcurrentHashMap;

public class RepositoryContext {
	
	private ConcurrentHashMap<Class<?>, Repository<?>> repositories;
	
	RepositoryContext() {
		repositories = new ConcurrentHashMap<>();
	}
	
	public Repository<?> repository(Class<?> type) {
		return repositories.get(type);
	}
	
	public void register(Class<?> key, Repository<?> repository) {
		repositories.put(key, repository);
	}
}