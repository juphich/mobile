package com.wise.product;

import java.util.List;

import android.database.Cursor;

import com.wise.category.Category;
import com.wise.core.Query;
import com.wise.suport.db.SQLiteDatabaseManager;
import com.wise.suport.db.SQLiteSupport;

public class SQLiteProductRepository extends SQLiteSupport implements ProductRepository {

	public SQLiteProductRepository(SQLiteDatabaseManager manager) {
		super(manager);
	}

	@Override
	protected void init() {
		StringBuilder product = new StringBuilder();
		product.append("create table if not exists product (")
			   .append("  id integer not null primary key,")
			   .append("  name varchar(200) not null,")
			   .append("  serial varchar(20) null,")
			   .append("  price numeric(20) not null,")
			   .append("  discount numeric(20) null,")
			   .append("  origin varchar(100) null,")
			   .append("  maker varchar(100) null,")
			   .append("  seller varchar(100) null,")
			   .append("  contact varchar(14) null,")
			   .append("  description text null )");
		getSession().executeQuery(product.toString());
		
		StringBuilder category = new StringBuilder();
		category.append("create table if not exists product_category (")
		        .append("  product_id integer not null,")
		        .append("  category_id varchar(10) not null,")
		        .append("  primary key(product_id, category_id) )");
		getSession().executeQuery(category.toString());
	}

	@Override
	public Product find(String key) {
		return null;
	}

	@Override
	public List<Product> search(Query query) {
		return null;
	}

	@Override
	public void save(Product domain) {
		StringBuilder query = new StringBuilder("insert or replace into product ")
			.append("(name, serial, price, discount, origin, maker, seller, contact, description) ")
			.append("values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		getSession().executeQuery(query.toString(), 
				domain.getName(), domain.getSerial(), domain.getPrice(), domain.getDiscount(),
				domain.getOrigin(), domain.getMaker(), domain.getSeller(), domain.getContact(), domain.getDescription());
		
		Cursor c = getSession().executeSelect("select last_insert_rowid()");
		c.moveToNext();
		int productId = c.getInt(0);
		c.close();
		
		if (domain.getCategories() != null && !domain.getCategories().isEmpty()) {
			for (Category agg : domain.getCategories()) {
				getSession().executeQuery("insert or replace into product_category (product_id, category_id) values (?, ?)",
							productId, agg.getId());
			}
		}
		
		new ProductBuilder().id(productId).bindTo(domain);
	}

	@Override
	public void remove(Product domain) {

	}

}
