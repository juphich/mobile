package net.entity.android;

import java.util.HashMap;
import java.util.Map;


public class EntityMetadata {

	private String name;
	
	private Map<String, Attribute> attributes;
	
	public String getName() {
		return name;
	}

	public Map<String, Attribute> getAttributes() {
		return attributes;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addAttribute(String name, Class<?> type) {
		if (attributes == null) {
			attributes = new HashMap<>();
		}
		
		attributes.put(name, new Attribute(name, type));
	}

	public static class Attribute {
		private String name;
		private Class<?> type;
		private String key;
		
		Attribute(String name, Class<?> type) {
			this.name = name;
			this.type = type;
		}

		public String getName() {
			return name;
		}
		
		public Class<?> getType() {
			return type;
		}
		
		public String getKey() {
			return key;
		}
		
		@Override
		public String toString() {
			StringBuilder view = new StringBuilder();
			view.append(name).append(" ").append(type.getSimpleName());
			
			return view.toString();
		}
	}
}
