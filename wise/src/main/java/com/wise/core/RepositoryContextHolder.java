package com.wise.core;


public class RepositoryContextHolder {

	private static RepositoryContext context = new RepositoryContext();
	
	public static void register(Class<?> key, Repository<?> repository) {
		context.register(key, repository);
	}

	@SuppressWarnings("unchecked")
	public static <T> Repository<T> repository(Class<T> type) {
		return  (Repository<T>) context.repository(type);
	}
}
