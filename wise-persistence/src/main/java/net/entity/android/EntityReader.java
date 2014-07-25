package net.entity.android;

import java.lang.annotation.Annotation;

import javax.persistence.Entity;

public class EntityReader {

	public EntityMetadata read(Class<?> type) {
		
		EntityMetadata meta = new EntityMetadata();
		
		Annotation typeAnn = type.getAnnotation(Entity.class);

		
		return meta;
	}

}
