package net.entity.android;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

public class EntityReader {

	public EntityMetadata read(Class<?> type) {
				
		if(!type.isAnnotationPresent(Entity.class)) {
			throw new RuntimeException("엔티티 클래스가 아니다."); 
		}
		
		EntityMetadata meta = new EntityMetadata();
		meta.setName(type.getSimpleName().toLowerCase());
		
		findColumns(type, meta);
		
		return meta;
	}
	
	private void findColumns(Class<?> type, EntityMetadata container) {
		if (type == Object.class) {
			return;
		}
		
		Class<?> parent = type.getSuperclass();
		findColumns(parent, container);
		
		Field[] fields = type.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Id.class)) {
				container.addAttribute(field.getName(), field.getType());
			} else if (field.isAnnotationPresent(Column.class)) {
				container.addAttribute(field.getName(), field.getType());
			}
		}
	}
}
