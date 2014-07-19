package com.wise.category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wise.core.Query;

public class InMemoryCategoryRepository implements CategoryRepository {

	private Map<String, Category> categories = new HashMap<>();
			
	@Override
	public Category find(String key) {
		return categories.get(key);
	}

	@Override
	public List<Category> search(Query query) {
		return new ArrayList<>(categories.values());
	}

	@Override
	public void save(Category domain) {
		categories.put(domain.getId(), domain);
	}

	@Override
	public void remove(Category domain) {
		categories.remove(domain);
	}
}
