package it.seda.generator.domain;


public class Attribute {

	private String name;
	private Class<?> type;
	private Class<?> generic;
	private String columnName;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameUcase() {
		return capitalize(name);
	}	
	public Class<?> getType() {
		return type;
	}
	public void setType(Class<?> type) {
		this.type = type;
	}
	
	public String printType() {
		StringBuilder stringBuilder = new StringBuilder(type.getName());
		if (generic!=null) {
			stringBuilder.append("<").append(generic.getName()).append(">");
		}
		return stringBuilder.toString();
	}
	
	public Class<?> getGeneric() {
		return generic;
	}
	public void setGeneric(Class<?> generic) {
		this.generic = generic;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Attribute() {}
	
	public Attribute(String name, Class<?> type, String columnName) {
		super();
		this.name = name;
		this.type = type;
		this.columnName = columnName;
	}
	
	public Attribute(String name, Class<?> type, Class<?> generic, String columnName) {
		super();
		this.name = name;
		this.type = type;
		this.generic=generic;
		this.columnName = columnName;
	}	
	
	@Override
	public String toString() {
		return "Attribute [name=" + name + ", type=" + type + ", columnName="
				+ columnName + "]";
	}
	

	protected String capitalize(String s) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (i==0) {
				stringBuilder.append(Character.toUpperCase(s.charAt(0)));
			} else {
				stringBuilder.append(s.charAt(i));
			}
		}
		return stringBuilder.toString();
	}
	
}
