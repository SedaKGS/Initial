/**
 * 
 */
package it.seda.sem.domain;

/**
 * @author f.ricci
 *
 */
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 * The factory class that creates a Transfer Object for a
 * given Bean or vice versa.
 */
public class ObjectCopier {
	/**
	 * Use a HashMap to cache class information for Transfer Object classes
	 */
	private static Map<String, ClassData> classDataInfo = Collections.synchronizedMap(new HashMap<String, ClassData>());

	/**
	 * Create an Object from the given class name. Only the commons fields are copied between objects.
	 */
	public static <T> T createObject(Object fromObject, Class<T> toClass) {
		try {
			// Get the class data for the complete 
			// Transfer Object type 
			ClassData fromData = getClassDataFromObject (fromObject); 
			// Get class data for the requested TO type
			ClassData toData = getClassDataFromClass (toClass);
			// Create the Object of the requested type
			Object toObject=toData.getObject();
			// get the fields for the requested object
			Field[] toFields = toData.arrFields;
			// get all fields for provided object
			Field[] fromFields = fromData.arrFields;
			// copy the common fields from the object  
			// to the fields of the requested bean class
			for (int i = 0; i < toFields.length; i++) {
				String toFieldName = toFields[i].getName();				
				if (toFieldName.equals("serialVersionUID") || 
						toFieldName.equals("class") || toFieldName.startsWith("$")) {
					continue;							
				}				
				try {
					for (int j=0; j < fromFields.length; j++) {
						// if the field names are same, copy value
						if (toFieldName.equals(fromFields[j].getName())) {
							// Copy value from matching field into the new Object
							if (toFields[i].getType().isAssignableFrom(fromFields[j].getType())) {
								toFields[i].set(toObject, fromFields[j].get(fromObject));								
							}
							break;
						}
					}
				} catch (Throwable t) {
					throw new RuntimeException(t.getMessage(),t);					
				}
			}

			// return the requested Object
			return (T)toObject;
		} catch (Throwable t) { 
			throw new RuntimeException(t.getMessage(),t);
		}
	}

	/**
	 * Return a ClassData object that contains the 
	 * information needed to create
	 * a Transfer Object for the given class. This information
	 * is only obtained from the
	 * class using reflection once, after that it will be 
	 * obtained from the classDataInfo HashMap.
	 */
	private static ClassData getClassDataFromObject(Object o){
		return getClassData(o.getClass().getName());
	}

	private static ClassData getClassDataFromClass(Class<?> clazz){
		return getClassData(clazz.getName());
	}	
	
	private static ClassData getClassData(String className){
		ClassData cData = (ClassData)classDataInfo.get(className);
		if (cData==null) {
			cData=resolveClassData(className);
		}
		return cData;
	}
	
	private static ClassData resolveClassData(String className){
		try {
			Class<?> clazz = Class.forName(className);
			return resolveClassData(clazz); 
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(),t);
		}
	}
	
	/**
	 * Return a ClassData object that contains the 
	 * information needed to create
	 * a Transfer Object for the given class. This information
	 * is only obtained from the
	 * class using reflection once, after that it will be 
	 * obtained from the classDataInfo HashMap.
	 */
	private static ClassData resolveClassData(Class<?> clazz){
		ClassData cData = null;
		try {
			// Get the class of the given object and the 
			// Transfer Object to be created
			Field[] arrFields ;
			// Determine the fields that must be copied
			arrFields = clazz.getDeclaredFields();
			for (int i = 0; i < arrFields.length; i++) {
				if (!arrFields[i].isAccessible()) 
					arrFields[i].setAccessible(true);
			}
			cData = new ClassData(clazz, arrFields);
			classDataInfo.put(clazz.getName(), cData);
		} catch (Throwable t) {
			throw new RuntimeException(t.getMessage(),t);
		}
		return cData;
	}	
	
}

/**
 * Inner Class that contains class data for the
 * Beans Object classes
 */
class ClassData {
	// Bean Object Class
	public Class<?>    beanClass;
	// Transfer Object fields
	public Field[] arrFields;
	
	private Constructor<?> emptyConstructor;
	// Constructor
	public ClassData(Class<?> beanClass, Field[] fields) {
		this.beanClass = beanClass;
		arrFields = fields;
		emptyConstructor=findEmptyConstructor(beanClass);
		if (emptyConstructor==null) {
			throw new IllegalArgumentException("The provided to class '" + beanClass +"' has not default constructor or has private modifier");			
		}		
	}
	public Object getObject() {
		if (emptyConstructor!=null) {
			try {
				return emptyConstructor.newInstance();
			} catch (Exception e) {
				throw new IllegalArgumentException("The provided to class '" + beanClass +"' has not default constructor or has private modifier");
			}
		}
		return null;
	}
	private Constructor<?> findEmptyConstructor(Class<?> type) {
		Constructor<?> emptyConstructor=null;
		Constructor<?>[] constructors = type.getConstructors();
		for (Constructor<?> constructor : constructors) {
			if (constructor.getParameterTypes().length==0) {
				try {
					emptyConstructor = type.getDeclaredConstructor();					
				} catch (NoSuchMethodException  e) {
					throw new IllegalArgumentException(e.getMessage(),e);
				}
				if (!emptyConstructor.isAccessible())
					emptyConstructor.setAccessible(true);
				break;
			}
		}
		return emptyConstructor;
	}

} 

