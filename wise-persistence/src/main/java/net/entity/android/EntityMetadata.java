package net.entity.android;

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

	public class Attribute {
		private String name;
		private Class<?> type;
		private String key;
		
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
