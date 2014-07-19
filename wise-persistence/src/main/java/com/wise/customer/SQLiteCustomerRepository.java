package com.wise.customer;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;

import com.wise.category.Category;
import com.wise.category.CategoryType;
import com.wise.core.Query;
import com.wise.suport.db.SQLiteDatabaseManager;
import com.wise.suport.db.SQLiteSupport;

public class SQLiteCustomerRepository extends SQLiteSupport implements CustomerRepository {

	public SQLiteCustomerRepository(SQLiteDatabaseManager manager) {
		super(manager);
	}

	@Override
	protected void init() {
		System.out.println("crate customer table.");
		
		StringBuilder customer = new StringBuilder();
		customer.append("CREATE TABLE IF NOT EXISTS customer (")
				.append("customer_id VARCHAR(40) PRIMARY KEY NOT NULL,")
				.append("serial varchar(30)  NULL,")
				.append("name VARCHAR(50)  NOT NULL,")
				.append("gender VARCHAR(10)  NULL,")
				.append("recommend INTEGER  NULL )");
		
		getSession().executeQuery(customer.toString());
		
		StringBuilder category = new StringBuilder();
		category.append("create table if not exists customer_category (")
				.append(" customer_id varchar(40) not null,")
				.append(" category_id varchar(10) not null,")
				.append(" primary key (customer_id, category_id) )");
		
		getSession().executeQuery(category.toString());
	}

	@Override
	public Customer find(String key) {
		Cursor c = getSession().executeSelect("select customer_id, serial, name, gender, recommend from customer where customer_id = ?", key);
		if (c.getCount() == 0) {			
			return null;
		} else if (c.getCount() > 1) {
			throw new IllegalStateException("too many results!!");
		} else {
			c.moveToNext();
			String id = c.getString(0);
			String serial = c.getString(1);
			String name = c.getString(2);
			String gender = c.getString(3);
			String rcId = c.getString(4);
			c.close();
			
			CustomerBuilder builder = new CustomerBuilder().id(id).serial(serial).name(name).gender(Gender.valueOf(gender));
			
			c = getSession().executeSelect("select c.id, c.name, c.type from category c, customer_category i where c.id = i.category_id and i.customer_id = ?", id);
			for (int i=0; i<c.getCount(); i++) {
				c.moveToNext();
				String cid = c.getString(0);
				String cname = c.getString(1);
				String type = c.getString(2);
				builder.category(new Category(cid, cname, CategoryType.valueOf(type)));
			}
			c.close();
			
			Customer recommend = find(rcId);
			builder.recommend(recommend);
			
			return builder.build();
		}
	}

	@Override
	public List<Customer> search(Query query) {
		List<Customer> list = new ArrayList<>();
		Cursor c = getSession().executeSelect("select customer_id, serial, name, gender from customer");
		for (int i=0; i<c.getCount(); i++) {
			c.moveToNext();
			
			String id = c.getString(0);
			String serial = c.getString(1);
			String name = c.getString(2);
			String gender = c.getString(3);
			
			Customer item = new CustomerBuilder().id(id).name(name).serial(serial).gender(Gender.valueOf(gender)).build();
			list.add(item);
		}
		c.close();
		
		return list;
	}

	@Override
	public void save(Customer domain) {
		String recid = domain.getRecommend() == null ? null : domain.getRecommend().getCustomerId();
		
		getSession().executeQuery("insert or replace into customer (customer_id, serial, name, gender, recommend) values (?, ?, ?, ?, ?)",
				        domain.getCustomerId(),
						domain.getSerial(), 
						domain.getName(), 
						domain.getGender().name(), 
						recid);
		
		for (Category ca : domain.getCategories()) {
			getSession().executeQuery("insert or replace into customer_category (customer_id, category_id) values (?, ?)", domain.getCustomerId(), ca.getId());
		}
	}

	@Override
	public void remove(Customer domain) {
		getSession().executeQuery("delete from customer_category where customer_id = ?", domain.getCustomerId());
		getSession().executeQuery("delete from customer where customer_id = ?", domain.getCustomerId());
	}
}
