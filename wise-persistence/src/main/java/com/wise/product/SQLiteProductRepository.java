package com.wise.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;

import com.wise.category.Category;
import com.wise.category.CategoryType;
import com.wise.core.Query;
import com.wise.suport.db.SQLiteDatabaseManager;
import com.wise.suport.db.SQLiteSupport;

public class SQLiteProductRepository extends SQLiteSupport implements ProductRepository {

	public SQLiteProductRepository(SQLiteDatabaseManager manager) {
		super(manager);
	}

	@Override
	protected void init() {
		System.out.println("create table product.");
		
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
		
		StringBuilder sale = new StringBuilder();
		sale.append("create table if not exists product_sale (")
			.append("  id integer not null primary key,")
			.append("  sale integer null,")
			.append("  stock integer null )");
		getSession().executeQuery(sale.toString());
		
		StringBuilder category = new StringBuilder();
		category.append("create table if not exists product_category (")
		        .append("  product_id integer not null,")
		        .append("  category_id varchar(10) not null,")
		        .append("  primary key(product_id, category_id) )");
		getSession().executeQuery(category.toString());
	}

	@Override
	public Product find(String key) {
		
		Cursor c = getSession().executeSelect("select p.id, p.name, p.serial, p.price, p.discount, p.origin, p.maker, p.seller, s.stock, s.sale from product p, product_sale s where p.id = s.id and p.id = ?", key);
		if (c.getCount() == 0) {
			return null;
		} else if (c.getCount() > 1) {
			throw new IllegalStateException("Too many results!!");
		} else {
			c.moveToNext();
			int id = c.getInt(0);
			ProductBuilder b = new ProductBuilder()
				.id(id).name(c.getString(1)).serial(c.getString(2)).price(c.getLong(3)).discount(c.getLong(4))
				.origin(c.getString(5)).maker(c.getString(6)).seller(c.getString(7)).sale(new Sale(c.getInt(8), c.getInt(9)));
			c.close();
			
			c = getSession().executeSelect("select c.id, c.name, c.type from category c, product_category p where c.id = p.category_id and p.product_id = ?", key);
			for (int i=0; i<c.getCount(); i++) {
				c.moveToNext();
				b.category(new Category(c.getString(0), c.getString(1), CategoryType.valueOf(c.getString(2))));
			}
			c.close();
			
			return b.build();
		}
	}

	@Override
	public List<Product> search(Query query) {		
		Map<Integer, List<Category>> temp = new HashMap<>();
		Cursor c = getSession().executeSelect("select p.product_id, c.id, c.name, c.type from category c, product_category p where c.id = p.category_id");
		for (int i=0; i<c.getCount(); i++) {
			c.moveToNext();
			int id = c.getInt(0);
			List<Category> a = null;
			if (temp.containsKey(id)) {
				a = temp.get(id);
			} else {
				a = new ArrayList<>();
				temp.put(id, a);
			}
			a.add(new Category(c.getString(1), c.getString(2), CategoryType.valueOf(c.getString(3))));
		}
		c.close();
		
		List<Product> list = new ArrayList<>();
		c = getSession().executeSelect("select p.id, p.name, p.serial, p.price, p.discount, p.origin, p.maker, p.seller, s.stock, s.sale from product p, product_sale s where p.id = s.id");
		for (int i=0; i<c.getCount(); i++) {
			c.moveToNext();
			int id = c.getInt(0);
			Product p = new ProductBuilder()
				.id(id).name(c.getString(1)).serial(c.getString(2)).price(c.getLong(3)).discount(c.getLong(4))
				.origin(c.getString(5)).maker(c.getString(6)).seller(c.getString(7)).categories(temp.get(id))
				.sale(new Sale(c.getInt(8), c.getInt(9))).build();
			list.add(p);
		}
		c.close();
		
		return list;
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
		
		getSession().executeQuery("insert or replace into product_sale (id, stock, sale) values (?, ?, ?)", 
					productId, domain.getSale().getStock(), domain.getSale().getSale());
		
		new ProductBuilder().id(productId).bindTo(domain);
	}

	@Override
	public void remove(Product domain) {

	}

}
