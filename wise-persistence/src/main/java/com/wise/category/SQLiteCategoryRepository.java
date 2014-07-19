package com.wise.category;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

import com.wise.core.Query;
import com.wise.suport.db.SQLiteDatabaseManager;
import com.wise.suport.db.SQLiteSupport;

public class SQLiteCategoryRepository extends SQLiteSupport implements
		CategoryRepository {

	public SQLiteCategoryRepository(SQLiteDatabaseManager manager) {
		super(manager);
	}

	@Override
	protected void init() {
		System.out.println("create table category.");
		StringBuilder category = new StringBuilder();
		category.append("CREATE TABLE IF NOT EXISTS category (")
				.append(" id VARCHAR(10) UNIQUE NOT NULL PRIMARY KEY,")
				.append(" name VARCHAR(10)  NOT NULL,")
				.append(" type VARCHAR(7)  NOT NULL )");
		
		getSession().executeQuery(category.toString());
	}

	@Override
	public Category find(String key) {
		Cursor c = getSession().executeSelect("select id, name, type from category where id = ?",  key);
		if (c.getCount() == 0 ) {
			return null;
		} else if (c.getCount() > 1) {
			throw new IllegalStateException("too many result!!.");
		} else {
			c.moveToNext();
			String id = c.getString(0);
			String name = c.getString(1);
			String type = c.getString(2);
			c.close();
			
			return new Category(id, name, CategoryType.valueOf(type));
		}
	}

	@Override
	public List<Category> search(Query query) {
		List<Category> list = new ArrayList<>();
		
		Cursor c = getSession().executeSelect("select id, name, type from category");
		for (int i=0; i<c.getCount(); i++) {
			c.moveToNext();
			String id = c.getString(0);
			String name = c.getString(1);
			String type = c.getString(2);
			
			list.add(new Category(id, name, CategoryType.valueOf(type)));
		}
		
		c.close();
		return list;
	}

	@Override
	public void save(Category domain) {
		getSession().executeQuery("insert or replace into category (id, name, type) values (?, ?, ?)", domain.getId(), domain.getName(), domain.getType());
	}

	@Override
	public void remove(Category domain) {

	}

}
