package it.seda.sem.mvc.utils;
import it.seda.sem.security.domain.Group;
import it.seda.sem.security.domain.GroupMember;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OptionsUtil {

	private static Map<String, Field> cache = Collections.synchronizedMap(new HashMap<String, Field>());
	
	private OptionsUtil() {}


	protected static Field getField(Object o, String name) throws NoSuchFieldException, SecurityException {
		String fieldName = o.getClass().getName()+"."+name;
		Field kf = cache.get(fieldName);
		if (kf==null) {
			kf = o.getClass().getDeclaredField(name);
			if (!kf.isAccessible()) {
				kf.setAccessible(true);
			}
			cache.put(fieldName, kf);
		}
		return kf;
	}
	
	protected static Object getFieldValue(Object o, Field field) throws IllegalAccessException {
		Object value = null;
		if (field!=null) {
			value = field.get(o);
		}
		
		if (value==null) {
			throw new IllegalArgumentException(field+" is null");
		}

		return value;
	}
	
	public static LinkedHashMap<String, String> buildOptionList(List<?> items, String key, String value) {
		LinkedHashMap<String,String> groupsMap = new LinkedHashMap<String,String>(items.size());
		try {
			if (items.size()>0) {
				Field kf = getField(items.get(0), key);
				Field nf = getField(items.get(0), value);
				for (Object o : items) {
					Object k=getFieldValue(o, kf);
					Object v=getFieldValue(o, nf);

					if (k!=null && v!=null) {
						groupsMap.put(k.toString(), v.toString());
					} 
				}				
			}
		} catch (Throwable x) {
			throw new RuntimeException(x);
		}
		return groupsMap;
	}
	
	
	

}
