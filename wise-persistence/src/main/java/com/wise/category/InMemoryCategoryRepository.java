package com.wise.category;

import java.util.List;

import com.wise.core.Query;

public class InMemoryCategoryRepository implements CategoryRepository {

	@Override
	public Category find(String key) {
		return null;
	}

	@Override
	public List<Category> search(Query query) {
		return null;
	}

	@Override
	public void save(Category domain) {

	}

}
