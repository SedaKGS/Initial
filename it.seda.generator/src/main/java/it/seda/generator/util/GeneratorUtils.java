package it.seda.generator.util;

public class GeneratorUtils {

	private GeneratorUtils() {
		// TODO Auto-generated constructor stub
	}

	
	static public String resolveClassName(String clazz) {
		String name;
		int lastdot = clazz.lastIndexOf('.');
		if (lastdot>0) {
			name=clazz.substring(lastdot+1);
		} else {
			name=clazz;
		}
		return name;
	}
	
	static public String resolveClassNamespace(String clazz) {
		String namespace;
		int lastdot = clazz.lastIndexOf('.');
		if (lastdot>0) {
			namespace=clazz.substring(0,lastdot);
		} else {
			namespace="";
		}
		return namespace;
	}
	
	static public String capitalize(String s) {
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
	
	static public String lower(String s) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (i==0) {
				stringBuilder.append(Character.toLowerCase(s.charAt(0)));
			} else {
				stringBuilder.append(s.charAt(i));
			}
		}
		return stringBuilder.toString();
	}
}
